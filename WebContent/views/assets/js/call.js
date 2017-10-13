/**
 * Created by user on 2016/8/8.
 */

/**
 * 用户对象
 * @param {Object} telephone 手机号
 * @param {Object} name 姓名
 * @param {Object} avatar 头像
 */
var People = function(telephone, name, avatar)
{
    this.telephone = telephone;
    this.name = name || '未保存号码';
    this.avatar = avatar || People.DEFAULT_AVATAR;
};
People.DEFAULT_AVATAR = 'assets/img/head1.jpg';

People.STORE_PREFIX = 'friend_';
/**
 * 从缓存中获取所有联系人信息
 */
People.getAll = function(callback)
{
    var friends = [];
    $.each(window.localStorage,function(key,value)
    {
        if(key.indexOf(People.STORE_PREFIX)===0)
        {
            friends.push(JSON.parse(value));
        }
    });
    if(callback) callback(friends);
};

/**
 * 从缓存中获取某个联系人的信息，如果找不到，返回一个空的people对象
 * @param {Object} telephone 手机号
 */
People.get = function(telephone, callback)
{
    var people = new People();
    if(telephone) people = lt.getStorage(People.STORE_PREFIX+telephone, new People());
    if(callback) callback(people);
    return people; //这里的return仅供Record.getAll()内部使用，其它地方切勿使用，避免不统一
};

/**
 * 将某个联系人信息放入缓存
 * @param {Object} people 联系人对象
 */
People.set = function(people, callback)
{
    if(!people || !people.telephone)
    {
        console.error('People.set(people)中参数不能为空！');
        if(callback) callback(false);
        return;
    }
    lt.setStorage(People.STORE_PREFIX+people.telephone, people);
    if(callback) callback(true);
};

/**
 * 通话记录对象
 * @param {Object} telephone 手机号
 * @param {Object} time 时间戳
 * @param {Object} dir 通话方向，call,answer,noanswer
 * @param {Object} type 通话类型，video,audio
 * @param {Object} duration 通话时长，单位毫秒
 */
var Record = function(telephone, time, dir, type, duration)
{
    this.telephone = telephone;
    this.time = time;
    this.dir = dir;
    this.type = type;
    this.duration = duration || 0;
};
Record.DIR_CALL = 'call';
Record.DIR_ANSWER = 'answer';
Record.DIR_NOANSWER = 'noanswer';
Record.TYPE_VIDEO = 'video';
Record.TYPE_AUDIO = 'audio';


/**
 * 删除联系人
 * @param {Object} telephone
 */
People.del = function(telephone, callback)
{
    lt.delStorage(People.STORE_PREFIX+telephone);
    if(callback) callback();
};


/**
 * 添加某个号码到黑名单，如果已经存在于黑名单中，返回false
 * @param {Object} telephone
 * @param {Object} callback
 */
People.addToBlacklist = function(telephone, callback)
{
    var blacklist = lt.getPreference('blacklist', []);
    if(blacklist.indexOf(telephone) >= 0)
    {
        if(callback) callback(false);
        return;
    }
    blacklist.push(telephone);
    lt.setPreference('blacklist', blacklist);
    if(callback) callback(true);
};

/**
 * 检查某个号码是否被加进黑名单
 */
People.checkIsInBlacklist = function(telephone, callback)
{
    var blacklist = lt.getPreference('blacklist', []);
    var result = blacklist.indexOf(telephone) >= 0;
    if(callback) callback(result);
};

/**
 * 获取所有黑名单用户
 * @param {Object} callback
 */
People.getAllBlacklist = function(callback)
{
    var blacklist = lt.getPreference('blacklist', []);
    if(callback) callback(blacklist);
};

/**
 * 将某个号码从黑名单中移除
 * @param {Object} telephone
 * @param {Object} callback
 */
People.delFromBlacklist = function(telephone, callback)
{
    var blacklist = lt.getPreference('blacklist', []);
    var idx = blacklist.indexOf(telephone);
    if(idx >= 0) blacklist.splice(idx, 1);
    lt.setPreference('blacklist', blacklist);
    if(callback) callback();
};

Record.STORE_PREFIX = 'record_';
Record.add = function(record, callback)
{
    if(!record || !record.telephone)
    {
        if(callback) callback(false);
        return;
    }
    // 默认未读
    if(record.type === Record.DIR_NOANSWER) record.unread = true;
    var records = lt.getStorage(Record.STORE_PREFIX+record.telephone, []);
    //records.push(record);
    records.unshift(record); // 注意这里由于需要按时间降序输出，所以用unshift而不是push
    lt.setStorage(Record.STORE_PREFIX+record.telephone, records);
    if(callback) callback(true);
};

Record.getAll = function(callback)
{
    var records = [];
    for(var i in window.localStorage)
    {
        if(new RegExp('^'+Record.STORE_PREFIX+'.+$', 'g').test(i))
        {
            var array = JSON.parse(localStorage[i]);
            var people = People.get(i.replace(Record.STORE_PREFIX, ''));
            for(var j=0; j<array.length; j++)
            {
                var record = array[j];
                record.name = people.name;
                record.avatar = people.avatar;
                records.push(record);
            }
        }
    }
    // 按时间降序输出
    records.sort(function(a, b){return a.time===b.time?0:(a.time<b.time?1:-1)});
    if(callback) callback(records);
};

/**
 * 获取所有未接来电
 * @param {Object} records 所有通话记录
 */
Record.getNoanswer = function(records, callback)
{
    var result = [];
    for(var i=0; i<records.length; i++)
    {
        if(records[i].dir === Record.DIR_NOANSWER)
        {
            result.push(records[i]);
        }
    }
    if(callback) callback(result);
};

/**
 * 获取所有未读未接来电个数
 * @param {Object} records 所有通话记录
 */
Record.getUnreadNoanswer = function(records, callback)
{
    var result = [];
    for(var i=0; i<records.length; i++)
    {
        if(records[i].dir === Record.DIR_NOANSWER && !records[i].unread)
        {
            result.push(records[i]);
        }
    }
    if(callback) callback(result.length);
};

/**
 * 根据手机号码获取与某个用户的通话记录，返回结果不需要name和avatar
 * @param {Object} telephone
 * @param {Object} callback
 */
Record.getByTelephone = function(telephone, callback)
{
    if(!telephone)
    {
        if(callback) callback([]);
        return;
    }
    var records = lt.getStorage(Record.STORE_PREFIX+telephone, []);
    if(callback) callback(records);
};

Record.deleteAll = function(callback)
{
    for(var i in window.localStorage) if(new RegExp('^'+Record.STORE_PREFIX+'.+$', 'g').test(i)) lt.delStorage(i);
    if(callback) callback();
};

Record.deleteByTelephone = function(telephone, callback)
{
    lt.delStorage(Record.STORE_PREFIX+telephone);
    if(callback) callback();
};

Record.deleteByTelephoneAndTime = function(telephone, time, callback)
{
    var records = lt.getStorage(Record.STORE_PREFIX + telephone, []);
    if(records.length <= 0)
    {
        if(callback) callback();
    }
    else
    {
        for(var i =0; i<records.length; i++)
        {
            if(records[i].time === time)
            {
                records.splice(i, 1);
                break;
            }
        }
        lt.setStorage(Record.STORE_PREFIX + telephone, records);
        if(callback) callback();
    }
};


/**
 * 所有自定义方法命名空间，lt表示路通
 */
var lt = lt || {};

/**
 * 从URL中获取参数
 * @param {Object} 参数名
 * @param {Object} 默认值
 */
lt.getParam = function(name, defaultValue)
{
    defaultValue = defaultValue === undefined ? '' : defaultValue;
    var result = new RegExp('(\\?|&)'+name+'=(.*?)(&|$)', 'g').exec(location.search);
    return result ? result[2] : defaultValue;
};


/**
 * 将数据放入缓存，如果不是字符串，自动转字符串
 * @param {Object} name
 * @param {Object} value
 */
lt.setStorage = function(name, value)
{
    value = typeof value === 'string' ? value : JSON.stringify(value);
    localStorage[name] = value;
};

/**
 * 从缓存中获取数据，如果检测到是字符串转换成的对象，会自动parse
 * @param {Object} name
 * @param {Object} defaultValue
 */
lt.getStorage = function(name, defaultValue)
{
    var value = localStorage[name] || defaultValue;
    return (typeof value === 'string' && /^[\{\[]/g.test(value)) ? JSON.parse(value) : value;
};

/**
 * 从缓存中删除某一个数据
 * @param {Object} name
 */
lt.delStorage = function(name)
{
    localStorage.removeItem(name);
};

/**
 * 保存数据到Android的Preference，之所以有这几个方法是因为有些数据（如黑名单、绑定列表）安卓也要获取
 * @param {Object} name
 * @param {Object} value
 */
lt.setPreference = function(name, value)
{
    value = typeof value === 'string' ? value : JSON.stringify(value);
    if(window.android && android.writePreference) android.writePreference('', name, value);
    else lt.setStorage(name, value);
};
lt.getPreference = function(name, defaultValue)
{
    var value = undefined;
    if(window.android && android.readPreference) value = android.readPreference('', name, '') || defaultValue;
    else value = lt.getStorage(name, defaultValue);
    return (typeof value === 'string' && /^[\{\[]/g.test(value)) ? JSON.parse(value) : value;
};
lt.delPreference = function(name)
{
    if(window.android && android.removePreference) android.removePreference('', name);
    else lt.delStorage(name);
};


/**
 * 检测手机号是否正确
 * @param {Object} telephone
 */
lt.checkTelephone = function(telephone)
{
    return /^1[34578]\d{9}$/g.test(telephone);
};

/**
 * 将日期格式化成指定格式的字符串
 * @param fmt 要格式化目标字符串，示例：yyyy-MM-dd www HH:mm:ss
 * @param date 要格式化的日期，不传时默认当前时间，可以是时间戳
 * @returns 返回格式化后的日期字符串
 */
lt.formatDate = function(fmt, date)
{
    fmt = fmt || 'yyyy-MM-dd www HH:mm:ss:SSS';
    date = date === undefined ? new Date() : date;
    if(typeof date === 'number') date = new Date(date);
    var obj =
    {
        'y': date.getFullYear(), // 年份，注意必须用getFullYear
        'M': date.getMonth()+1, // 月份，注意是从0-11
        'd': date.getDate(), // 日期
        "q": Math.floor((date.getMonth()+3)/3), // 季度
        'w': date.getDay(), // 星期，注意是0-6
        'H': date.getHours(), // 24小时制
        'h': date.getHours()%12==0?12:date.getHours()%12, // 12小时制
        'm': date.getMinutes(), // 分钟
        's': date.getSeconds(), // 秒
        'S': date.getMilliseconds() // 毫秒
    };
    var week = ['天', '一', '二', '三', '四', '五', '六'];
    for(var i in obj)
    {
        fmt = fmt.replace(eval('/'+i+'+/g'), function( m )
        {
            var val = obj[i] + '';
            if(i == 'w') return (m.length>2?'星期':'周') + week[val];
            for(var j=0, len=val.length; j<m.length-len; j++) val = '0' + val;
            return m.length==1 ? val : val.substring(val.length - m.length);
        });
    }
    return fmt;
};

/**
 * 将日期
 * @param {Object} date
 */
lt.formatDateToFriendly = function(date)
{
    date = date || new Date();
    date = typeof date === 'number' ? new Date(date) : date;
    var now = new Date();
    if((now.getTime() - date.getTime()) < 60*1000) return '刚刚';
    else if(lt.formatDate('yyyyMMdd', date) === lt.formatDate('yyyyMMdd', now)) return lt.formatDate('HH:mm', date);
    else if(date.getFullYear()===now.getFullYear()) return lt.formatDate('M月d日', date);
    else return lt.formatDate('yyyy年M月d', date);
};

/**
 * 将一段时长转换成友好格式，如：
 * 147->“2分27秒”
 * 1581->“26分21秒”
 * 15818->“4小时24分”
 * @param {Object} second
 */
lt.formatDurationToFriendly = function(second)
{
    if(second < 60) return second + '秒';
    else if(second < 60*60) return (second-second%60)/60+'分'+second%60+'秒';
    else if(second < 60*60*24) return (second-second%3600)/60/60+'小时'+Math.round(second%3600/60)+'分';
    return (second/60/60/24).toFixed(1)+'天';
};

/** 将时间转换成MM:SS形式，add by 20150606 */
lt.parseTimeInfo = function(second)
{
    var m = Math.floor(second / 60);
    m = m < 10 ? ( '0' + m ) : m;
    var s = second % 60;
    s = s < 10 ? ( '0' + s ) : s;
    return m + ':' + s;
};

/*发送短信*/
lt.sendSmsg = function(number, content, callback)
{

    $.ajax(
        {
            url: 'http://58.53.214.99:19090/homesick/api/smsg/send.do',
            data: JSON.stringify({phoneNo: number, content: content}),
            contentType: 'application/json; charset=utf-8',
            type: 'POST',
            success: function(data)
            {
                if(callback) callback(data.code==0);
            },
            error: function()
            {
                mui.toast('系统繁忙，发送短信失败！');
            }
        });
};

/**
 * 初始化联系人列表
 * @param {Object} [{telephone, name, avatar}]
 * @param type tv_bind,friends,phonebook,send_friend
 */
function initFriendList(users, type, addName)
{

   // var uid = mobile.getStorage('uid');
    var szm, py, sort, people, friends = [];
    for(var i=0; i<users.length; i++)
    {
        people = users[i];
       // if(people && people.telephone === uid) continue; // 本机号码不显示
        szm = pinyin.getFirstLetter(people.name, false); // 获取拼音首字母
        py = pinyin.getPinyin(people.name, ''); // 获取完整拼音

        sort = py;
        var first = sort[0].toUpperCase();   //将字符串转换成大写
        var charCode = first.charCodeAt();   //返回字符的 Unicode 编码
        if(charCode < 65 || charCode > 90) sort = '#'+sort;

        friends.push({szm: szm, py: py, sort: sort, hz: people.name, telephone: people.telephone, avatar: people.avatar || People.DEFAULT_AVATAR});
    }
    console.log('联系人总数：'+friends.length);
    if(friends.length <= 0)
    {
        if(type==='phonebook')
        {
            $('.mui-indexed-list-empty-alert').html('您的通讯录中暂无好友,可能你禁止了相关权限').show();
        }
        else
        {
            $('.mui-indexed-list-empty-alert').html('您的通讯录中暂无好友').show();
        }
        return;
    }
    //将联系人按中文升序排序，原生的localeCompare在安卓上有些问题，故采用拼音排序
    //friends.sort(function(a, b){return a.hz==b.hz?0:(a.hz.localeCompare(b.hz))});
    friends.sort(function(a, b){return a.sort==b.sort?0:(a.sort<b.sort?-1:1)});
    var array = []; // 添加字母分组
    var group = {}; // 临时标记某个字母是否已经添加到array里面去了
    for(var i=0; i<friends.length; i++)
    {
        var first = friends[i]['sort'][0].toUpperCase();
        if(!group[first])
        {
            group[first] = true;
            array.push({group: first});
        }
        array.push(friends[i]);
    }

    var html = '', obj, href;
    for(var i=0; i<array.length; i++)
    {
        obj = array[i];
        if(obj.group) html += '<li data-group="'+obj.group+'" class="mui-table-view-divider mui-indexed-list-group">'+obj.group+'</li>';
        else
        {
            html += '<li data-value="'+obj.szm+'" data-tags="'+obj.py+'" class="mui-table-view-cell mui-indexed-list-item">'+
                '<a href="javascript:;" data-phone="'+obj.telephone+'"><img src="'+obj.avatar+'"/>'+obj.hz;
            if(type==='phonebook'&& !lt.getStorage(People.STORE_PREFIX+obj.telephone))
            {
                html+='<span class="btn-add" data-phone="'+obj.telephone+'" data-name="'+obj.hz+'">添加</span>'
            }
            html+='</a></li>';
        }

    }
    $('#friends-ul').append(html);
    $('.mui-indexed-list-bar').show();
    window.indexedList = new mui.IndexedList(document.getElementById('friends_list'));
}

var vtalk={
    waitingTime: 60, // 最长等待时间
    countryCode: '+86',
    isMobile: window.mui !== undefined,
    /**
     * 发起呼叫初始化
     * @param {Object} status 0表示成功，1表示失败
     */
    onCallInit: function(status)
    {
        mui.toast('正在初始化');
        if(status == 1)
        {
            mui.toast('初始化失败！');
        }
    },
    //TODO 需要考虑正在通话时来电
    /**
     * 来电时触发
     * @param {Object} status
     * @param {Object} targetNumber 来电号码，不带+86
     */
    onIncomingCall: function(status, targetNumber)
    {
        /*if(status === 2 ) // 如果是TV协助
        {
            vtalk.onTvHelp(targetNumber);
            return;
        }*/
        if(vtalk.isMobile)
        {
            if(!mobileApi.isWifi() && !confirm('您当前不是wifi状态，是否继续？')) return;
            mobile.go('talk.html?number='+targetNumber+'&mode='+status+'&isCall=false');
        }
        else
        {
            // 接听处理全部交由安卓端处理
            //whenReceiveCall(targetNumber);
        }
    },
    /**
     * 检测是不是手机号
     * @param {Object} number
     */
    checkIsTelephone: function(number)
    {
        return /^1[34578]\d{9}$/g.test(number);
    },
    /**
     * 当拨打未接通时发送短信给用户
     * @param {Object} telephone
     * @param {Object} callType
     * @param {Object} talkDuration
     * @param {Object} waitingTime
     * @param {Object} userShutdown
     */
    sendSmsgNotice : function(telephone, callType, talkDuration, waitingTime, userShutdown)
    {
        if(callType == 'call' && talkDuration == 0 && waitingTime > 5 && !userShutdown)
        {
        // 不是手机不发短信
            if(!mobile.checkIsTelephone(telephone)) return;
            var tel = lt.getStorage('userid', '');
            var time = lt.formatDate('yyyy年MM月dd日HH时mm分');
            var content = '尊敬的亮画用户，'+tel+'于'+time+'拨打过您的号码，请及时联系处理！亮画APP下载：http://file.118328.com:19090/lianghua.apk（安卓版），https://itunes.apple.com/cn/app/liang-hua/id1115259427（ios版）';
            var sendFn = (window.Epg && Epg.sendSmsg) ? Epg.sendSmsg : lt.sendSmsg;
            sendFn(telephone, content, function(success)
            {
                if(success) mui.toast('已发送短信通知到对方！');
                else mui.toast('发送短信通知失败！');
            });
        }
    }


};
/*监听事件*/
var CallStatusListener =
{
    onStatusChange: function(data)
    {
        data = JSON.parse(data);
        var status = data.status;
        switch(data.type)
        {
            case 1: vtalk.onCallInit(status); break;      //初始化
            case 2: vtalk.onIncomingCall(status, CallApi.getPeerNumber()); break;    //来电
            case 3: vtalk.onCallStatusChange(status); break;   //呼叫的状态变更
            case 4: vtalk.onReceiveAddVideo(); break;      //音频呼叫中收到视频请求
            case 5: vtalk.onAddVideoIsRefused(); break;    //音频呼叫中发起的视频请求被对方拒绝
            case 6: vtalk.onToggleVideoAudioSuccess(status==0?'video':'audio'); break;         //呼叫中音视频切换成功
            case 8: vtalk.onQualityNotice(status); break;         //呼叫中视频质量上报
           // case 11: vtalk.onReceiveVideoShare(status); break;   //接收到视频分享请求
            default: break;
        }
    }
};

var LoginStatusListener =
{
    onStatusChange: function(data)
    {
        data = JSON.parse(data);
        console.log('loginStatus', data.status);
        if(data.status===3)
        {
            setTimeout(function(){mui.toast('可视通话与服务器断开连接');}, 800);
        }
        /*if(data.status === 3)
        {
            // 不能立即提示，因为退出时会触发这个，退出无需提示
            setTimeout(function(){mui.toast('与服务器断开连接');}, 800);
        }*/
    }
};


/**
 * 获取登录中文错误信息
 * @param {Object} code
 */
vtalk.getLoginErrorInfo = function(code)
{
    if(code === undefined) return;
    var infos =
    {
        c0: '鉴权失败',
        c1: '连接错误',
        c2: '服务繁忙',
        c5: '已被系统强制退出',
        c6: '未知错误',
        c10: '网络不可用',
        c12: '账户异常',
        c14: '用户未注册',
        c19: '网络鉴权失败',
        c26: 'accessToken无效',
        c27: 'accessToken过期',
        c41: '强制退出',
        c43: '登录过于频繁'
    }
    return infos['c'+code];
};