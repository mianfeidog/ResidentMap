/* -----------H-ui前端框架-------------
* H-ui.admin.page.js v3.1
* http://www.h-ui.net/
* Created & Modified by guojunhui
* Date modified 2017.07.04
* Copyright 2013-2017 北京颖杰联创科技有限公司 All rights reserved.
* Licensed under MIT license.
* http://opensource.org/licenses/MIT
*/
/*左侧菜单响应式*/
function Huiasidedisplay(){
	if($(window).width()>=768){
		$(".Hui-aside").show()
	} 
}
/*获取皮肤cookie*/
function getskincookie(){
	var v = $.cookie("Huiskin");
	var hrefStr=$("#skin").attr("href");
	if(v==null||v==""){
		v="default";
	}
	if(hrefStr!=undefined){
		var hrefRes=hrefStr.substring(0,hrefStr.lastIndexOf('skin/'))+'skin/'+v+'/skin.css';
		$("#skin").attr("href",hrefRes);
	}
}
/*弹出层*/
/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/
function layer_show(title,url,w,h){
	if (title == null || title == '') {
		title=false;
	};
	if (url == null || url == '') {
		url="404.html";
	};
	if (w == null || w == '') {
		w=800;
	};
	if (h == null || h == '') {
		h=($(window).height() - 50);
	};
	layer.open({
		type: 2,
		area: [w+'px', h +'px'],
		fix: false, //不固定
		maxmin: true,
		shade:0.4,
		title: title,
		content: url
	});
}

/*关闭弹出框口*/
function layer_close(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}

/*时间*/
function getHTMLDate(obj) {
    var d = new Date();
    var weekday = new Array(7);
    var _mm = "";
    var _dd = "";
    var _ww = "";
    weekday[0] = "星期日";
    weekday[1] = "星期一";
    weekday[2] = "星期二";
    weekday[3] = "星期三";
    weekday[4] = "星期四";
    weekday[5] = "星期五";
    weekday[6] = "星期六";
    _yy = d.getFullYear();
    _mm = d.getMonth() + 1;
    _dd = d.getDate();
    _ww = weekday[d.getDay()];
    obj.html(_yy + "年" + _mm + "月" + _dd + "日 " + _ww);
};

/*个人信息*/
function myselfinfo(){
	layer.open({
		type: 1,
		area: ['300px','200px'],
		fix: false, //不固定
		maxmin: true,
		shade:0.4,
		title: '查看信息',
		content: '<div>管理员信息</div>'
	});
}
$(function(){
	getHTMLDate($("#top_time"));
	getskincookie();
	Huiasidedisplay();
	var resizeID;
	$(window).resize(function(){
		clearTimeout(resizeID);
		resizeID = setTimeout(function(){
			Huiasidedisplay();
		},500);
	});
	
	$(".nav-toggle").click(function(){
		$(".Hui-aside").slideToggle();
	});
	$(".Hui-aside").on("click",".menu_dropdown dd li a",function(){
		if($(window).width()<768){
			$(".Hui-aside").slideToggle();
		}
	});
	
	/*左侧菜单*/
	$(".Hui-aside").Huifold({
		titCell:'.menu_dropdown dl dt',
		mainCell:'.menu_dropdown dl dd',
	});	
		
	/*换肤*/
	$("#Hui-skin .dropDown-menu a").click(function(){
		var v = $(this).attr("data-val");
		$.cookie("Huiskin", v);
		var hrefStr=$("#skin").attr("href");
		var hrefRes=hrefStr.substring(0,hrefStr.lastIndexOf('skin/'))+'skin/'+v+'/skin.css';
		$(window.frames.document).contents().find("#skin").attr("href",hrefRes);
	});
});


/**
*time:2017-09-07
*disc:添加的公共方法
*/
function article_edit(title,url,id,w,h){
	layer.prompt({
		title:"请输入修改后的类型名称",
		formType:0 //0-文本、1-密码、2-文本区域
	},function(val, index){
		layer.msg('类型修改为 '+val+" 成功");
		layer.close(index);
	});
}
/*资讯-删除*/
function article_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '',
			dataType: 'json',
			success: function(data){
				$(obj).parents("tr").remove();
				layer.msg('已删除!',{icon:1,time:1000});
			},
			error:function(data) {
				console.log(data.msg);
			},
		});
	});
}

/**
*time:2017-09-08
*disc:公共的api接口前缀
*/
var apiUrl="http://localhost:8080/ResidentMap/";

/**
*time:2017-09-08
*disc:下拉的公共方法，对民族进行转换
*/
/*function changeNation(nationNumber){
    var nation;
            $.ajax({
                type:"get",
                url:apiUrl+"data_dictionaries/type/5",
                data :JSON.stringify(),   //提交的对象，删除不用提交
                async:false,
                contentType:'application/json',
                success : function(data){
                    if (data.code == "0"){
                    	var data=data.data;
                         console.log(data);
                       for(var j=0;j<data.length;j++){
                           if(data[j].value==nationNumber){
                               nation=data[j].name;
                           }
                       }
                    } else if (data.code == "99"){
                        console.log(data.detail);
                    }
                },
                error : function() {
                    console.log("请求出错");
                }
            });
      return nation;
}*/
var nationData;
function changeNation(nationNumber){
    if(nationData==undefined){
        $.ajax({
            type:"get",
            url:apiUrl+"data_dictionaries/type/5",
            data :JSON.stringify(),   //提交的对象，删除不用提交
            async:false,
            contentType:'application/json',
            success : function(data){
                console.log(data);
                if (data.code == "0"){
                     nationData=data.data;
                } else if (data.code == "99"){
                    console.log(data.detail);
                }
            },
            error : function() {
                console.log("请求出错");
            }
        });
    }else{

        var nation;
        for(var j=0;j<nationData.length;j++){
            if(nationData[j].value==nationNumber){
                console.log(nationData[j]);
                nation=nationData[j].name;
                console.log(nation);
            }
        }
    }
    return nation;
}
/**
*time:2017-10-09
*disc:对职务进行转化
*/
/*
function changePosition(positionNumber){
    var position;
    var data="";
    if(dataRoom==""){
        $.ajax({
            type:"get",
            url:apiUrl+"data_dictionaries/type/10",
            data :JSON.stringify(),   //提交的对象，删除不用提交
            async:false,
            contentType:'application/json',
            success : function(data){
                if (data.code == "0"){
                    var data=data.data;
                } else if (data.code == "99"){
                    console.log(data.detail);
                }
            },
            error : function() {
                console.log("请求出错");
            }
        });

    }else{
        for(var j=0;j<data.length;j++){
            if(data[j].value==positionNumber){
                position=data[j].name;
            }
        }
    }
    return position;
}*/

/**
*time:2017-10-09
*disc:渲染名族列表
*/
function renderNation(){
    var ajax_url=apiUrl+"data_dictionaries/type/5";
    var ajax_type = 'GET';
    $.ajax({
        type :ajax_type,
        url : ajax_url,
        data :JSON.stringify(),//提交的对象，删除不用提交
        contentType:'application/json',
        success : function(data) {
            if (data.code == "0") {
                var data=data.data;
                var html='';
                for(var i=0;i<data.length;i++)
                {
                    html+='<option value="'+data[i].value+'">'+data[i].name+'</option>';
                }
                $('#nation').append(html);
            } else if (data.code == "99") {
                console.log(data.detail);
            }
        },
        error : function() {
            console.log("请求出错");
        }
    });
}

/**
 *time:2017-10-09
 *disc:渲染文化程度列表
 */
function renderCultureLevel(){
    var ajax_url=apiUrl+"data_dictionaries/type/6";
    var ajax_type = 'GET';
    $.ajax({
        type :ajax_type,
        url : ajax_url,
        data :JSON.stringify(),//提交的对象，删除不用提交
        contentType:'application/json',
        success : function(data) {
            if (data.code == "0") {
                var data=data.data;
                var html='';
                for(var i=0;i<data.length;i++)
                {
                    html+='<option value="'+data[i].value+'">'+data[i].name+'</option>';
                }
                $('#culturelevel').append(html);
            } else if (data.code == "99") {
                console.log(data.detail);
            }
        },
        error : function() {
            console.log("请求出错");
        }
    });
}
/**
 *time:2017-10-09
 *disc:渲染岗位列表
 */
function renderJob(){
    var ajax_url=apiUrl+"data_dictionaries/type/7";
    var ajax_type = 'GET';
    $.ajax({
        type :ajax_type,
        url : ajax_url,
        data :JSON.stringify(),//提交的对象，删除不用提交
        contentType:'application/json',
        success : function(data) {
            if (data.code == "0") {
                console.log(data);
                var data=data.data;
                var html='';
                for(var i=0;i<data.length;i++)
                {
                    html+='<option value="'+data[i].value+'">'+data[i].name+'</option>';
                }
                $('#claimpost').append(html);
            } else if (data.code == "99") {
                console.log(data.detail);
            }
        },
        error : function() {
            console.log("请求出错");
        }
    });
}