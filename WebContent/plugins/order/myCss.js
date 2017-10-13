$(function(){
	/*********链接跳转*********/
	$(".sidebar-menu>li").click(function(){
		$(this).addClass("choosed").siblings("li").removeClass("choosed");
		loadHtml();
	});
	
	/*********Dashboard添加链接**********/
	$(document).on("click",".nav_list>li",function(){
		var num = $(this).index();
		$(".sidebar-menu>li").eq(num+1).click();
	});
	
	
	function loadHtml(){
		var telId = $(".sidebar-menu>li.choosed").attr("id");
		$(".content-wrapper").load("subViews/"+telId+".html")
	}
	loadHtml();
	
	/*************点击选中样式*************/
	$(document).on('click',".child_line>li",function(){
		$(this).addClass("current").siblings("li").removeClass("current");
	});
	
	/****************点击下拉 得到的值赋给input*************/
	$(document).on('change',".overlap",function(){
		var choosed_val=$(this).val();
		$(this).siblings(".time_input").val(choosed_val);
	})
	
	
});


