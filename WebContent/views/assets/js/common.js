/**
 * Created by user on 2016/6/6.
 */
/* 内容高度自适应，content的高度会自动在body的高度基础上减去header和footer的，
 * 这段代码主要是为了解决calc在移动端支持不好的问题 */
var contentHeight;
;(function()
{
    function resize()
    {
        var docEl = document.documentElement;
        var clientWidth = docEl.clientWidth < 720 ? docEl.clientWidth : 720;
        if (!clientWidth) return;
        docEl.style.fontSize = Math.floor(100 * (clientWidth / 720)) + 'px';

        var content = $('section');
        var windowHeight = $(window).height();
        var headerHeight = $('header').height() || 0;
        var footerHeight = $('footer').height() || 0;
        var sliderHeight = $('#sliderSegmentedControl').height() || 0;
        contentHeight = windowHeight - headerHeight - footerHeight;
        content.css('height', contentHeight);
        var otherHeight=$('.other').height();
        var sliderHeight=contentHeight - otherHeight - sliderHeight;
        /*var elem = document.createElement('style');
        elem.innerHTML = '#slider-group{height:'+sliderHeight+'px!important';
        document.head.appendChild(elem);*/
        $('#slider-group').height(sliderHeight);
    }

   // if('onorientationchange' in window) window.addEventListener('onorientationchange', resize, false);   //防止全屏和小视频切换时会触发
    //window.addEventListener('resize', resize, false);     //IOS在播放视频全屏和小视频切换时会触发resize,导致计算高度有误
    document.addEventListener('DOMContentLoaded', resize, false);
})();

/*图片懒加载*/
;(function($)
{
    $(window).on('scroll resize load', function(e)
    {
        var count = 0, screenHeight = $(window).height();
        $('[data-lazy-src]').each(function()
        {
            var pos = this.getBoundingClientRect();
            if(pos.bottom <= 0) return true; // 如果当前图片在视野上方，继续往下查找
            if(pos.top >= screenHeight) return (count++)<2; // 如果超过2张图片在视野下方，停止查找，只有图片布局从上到下才能这样判断
            var src = this.dataset.lazySrc;
            if(!src) return;
            if(this.nodeName === 'IMG') this.src = src;
            else this.style.backgroundImage = 'url(' + src + ')';
            this.removeAttribute('data-lazy-src');
        });
    });
})(jQuery);


/* 解决移动端click事件300ms延迟问题 */
document.addEventListener('DOMContentLoaded', function()
{
    if(window.FastClick) FastClick.attach(document.body);
}, false);


//var preURL="http://192.168.56.1:8080/cqeye-api/";   //数据地址
 // preURL="http://172.16.8.166:8080/cqeye-api/";   //本地建群数据地址
  var preURL="http://localhost:8080/ResidentMap/";//现网


$('#back').on('click',function()
{
    mobile.goBack();
});
/*mui(document.body).on('tap', '#back', function()   //返回键
{
    mobile.goBack();
});*/

var mobile=
{

    appVersion:'1.0.0',
    deceleration:0.0005,   /*flick 减速系数，系数越大，滚动速度越慢，滚动距离越小，默认值0.0006*/
    visitorId:'123456',
    /*ajax请求后台数据*/
    myAjax:function(api,data,success,error)
    {
        $.ajax({
            type:'POST',
            url:preURL+api,
            data:(typeof data==='object')?JSON.stringify(data):data,
            contentType:'application/json;charset=UTF-8',
            success:success,
            error:error || function()
            {
                alert("系统繁忙，请求数据失败！");
            }
        });
    },

    /*从URL中获取参数的值,a为参数名称*/
    getParam:function(a)
    {
        var str=location.search.substr(1);
        var strArr=str.split("&");
        var name;
        $.each(strArr,function(idx,ele){
            if(ele.indexOf(a)!=(-1))
            {
                var start= a.length+1;
                name=ele.substring(start);
            }
        });
        return name;
    },
    getKey:function()
    {
        var path=location.pathname;
        var pathArr=path.split("/");
        var key=pathArr[pathArr.length-1].split(".")[0];
        return "mobile_"+key;
    },

    /**
     * 检测是不是手机号
     * @param {Object} number
     */
    checkIsTelephone: function(number)
    {
        return /^1[34578]\d{9}$/g.test(number);
    },
    setStorage:function(name,value)
    {
        localStorage[name]=value;
    },
    getStorage:function(name)
    {
        return localStorage[name];
    },
    deleteStorage:function(name)
    {
        localStorage.removeItem(name);
    },
    go:function(url)/*将用户id存储到缓存*/
    {
        var backURI = location.href;
        var backUrlList = JSON.parse(localStorage['back_url_list'] || '[]');
        var len=backUrlList.length;
        if(backUrlList[len-1]!=backURI)
        {
            backUrlList.push(backURI);
        }
        localStorage['back_url_list'] = JSON.stringify(backUrlList);
        mobile.setStorage("source",mobile.getKey());
        location.href = url;
    },
    goBack:function()   /* 页面返回时从缓存中获取返回地址，获取不到返回首页*/
    {
        var backUrlList = JSON.parse(localStorage['back_url_list'] || '[]');
        var backURL = backUrlList.pop() || ('index.html');
        localStorage['back_url_list'] = JSON.stringify(backUrlList);
        var html=location.pathname;
        location.href = backURL;
    },
    /*点击页面导航切换页面*/
    changeNavigate:function()
    {
        $('#nav-wrap .mui-control-item').on('tap',function(){
            var url=$(this).attr('data-page');

            if(url!=undefined&&url!='')
            {
                if(url==='hot-circle.html'&&!isLogin)
                {
                    mobile.go('login.html?isLogin=true');
                }
                else
                {
                    mobile.go(url);
                }

            }
        });
    },
    getPlayType:function(type)
    {
        var playType='play-tv';
        switch(type)
        {
            case 'vod_tj':
                playType='play-movie';
                break;
            case 'vod_dy':
                playType='play-movie';
                break;
            case 'vod_qp':
                playType='play-movie';
                break;
            case 'vod_ms':
                playType='play-movie';
                break;
        }
        return playType;
    }
};

var userId=mobile.getStorage('userid');

var isLogin=mobile.getStorage("isLogin");

var isCallLogin;

$('#btnPersonPage').on('click',function()
{
    if(isLogin==='true')
    {
        mobile.go('person.html');
    }
    else
    {
        mobile.go('login.html?isLogin=true');
    }
});
$('#btnShowPage').on('click',function()
{
    if(isLogin==='true')
    {
        mobile.go('show.html');
    }
    else
    {
        mobile.go('login.html?isLogin=true');
    }
});
$('#btnCallPage').on('click',function()
{
    if(isLogin==='true')
    {
        mobile.go('call.html');
    }
    else
    {
        mobile.go('login.html?isLogin=true');
    }

});
function accessLog()
{
    var accessLogObj=
    {
        userid:mobile.getStorage('isLogin')==='true'?mobile.getStorage('userid'):mobile.visitorId,
        source:mobile.getStorage('source'),  //来源页
        sourceType:'',  //来源页类型
        target:mobile.getKey(),   //当前页
        targetType:'',     //当前页类型
        appVersion:mobile.appVersion

    }
    mobile.myAjax('cqeye/insertAccessLogInfo',accessLogObj,function(data)
    {

    });

}
setTimeout(function()
{
    accessLog();
},2000);

/*// 禁止文字选择
document.onselectstart = function(){ return false; };
// 禁止复制
document.oncopy = function(){ return false; };
// 禁止剪切
document.oncut = function(){ return false; };
// 禁止粘贴
document.onpaste = function(){ return false; };*/

var myScroll2;
function scrollLoaded() {
    myScroll2 = new iScroll('wrapper',{
        fadeScrollbar: true,
        hideScrollbar: true
    });

}
/*初始化iScroll控件*/
function loaded() {
    pullDownEl = document.getElementById('pullDown');
    pullDownOffset = pullDownEl.offsetHeight;
    pullUpEl = document.getElementById('pullUp');
    pullUpOffset = pullUpEl.offsetHeight;

    myScroll = new iScroll('wrapper', {
        fadeScrollbar: true,
        hideScrollbar: true,

        useTransition: false,
        topOffset: pullDownOffset,
        onRefresh: function () {
            if (pullDownEl.className.match('loading')) {
                pullDownEl.className = '';
                pullDownEl.querySelector('.pullDownLabel').innerHTML = '下拉刷新...';
            } else if (pullUpEl.className.match('loading')) {
                pullUpEl.className = '';
                pullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉加载更多...';
            }
        },
        onScrollMove: function () {
            if (this.y > 5 && !pullDownEl.className.match('flip')) {
                pullDownEl.className = 'flip';
                pullDownEl.querySelector('.pullDownLabel').innerHTML = '松手开始更新...';
                this.minScrollY = 0;
            } else if (this.y < 5 && pullDownEl.className.match('flip')) {
                pullDownEl.className = '';
                pullDownEl.querySelector('.pullDownLabel').innerHTML = '下拉刷新...';
                this.minScrollY = -pullDownOffset;
            } else if (this.y < (this.maxScrollY - 5) && !pullUpEl.className.match('flip')) {
                pullUpEl.className = 'flip';
                pullUpEl.querySelector('.pullUpLabel').innerHTML = '松手开始更新...';
                this.maxScrollY = this.maxScrollY;
            } else if (this.y > (this.maxScrollY + 5) && pullUpEl.className.match('flip')) {
                pullUpEl.className = '';
                pullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉加载更多...';
                this.maxScrollY = pullUpOffset;
            }
        },
        onScrollEnd: function () {
            if (pullDownEl.className.match('flip')) {
                pullDownEl.className = 'loading';
                pullDownEl.querySelector('.pullDownLabel').innerHTML = '加载中...';
                pullDownAction();	// Execute custom function (ajax call?)
            } else if (pullUpEl.className.match('flip')) {
                pullUpEl.className = 'loading';
                pullUpEl.querySelector('.pullUpLabel').innerHTML = '加载中...';
                pullUpAction();	// Execute custom function (ajax call?)
            }
        }
    });
}
/** 事件处理 */
document.onkeydown=function(e){
    e = e || window.event;
    var keyCode = e.which || e.keyCode;
    if(keyCode === 4) {    //手机物理返回键
        e.preventDefault();
        mobile.goBack();
    }
};

