/**
 * 铭飞通用js框架,
 * 删除方法：
 * 选择器：.del-btn 类样式
 * 删除地址：data-url属性
 */
ms = {
	init : function() {
		var target = this;
		// 初始化删除
		// 删除文章
		$("body").delegate(".del-btn", "click", function() {
			var url = $(this).attr("data-url");
			warnModel("确定要删除该记录？", "删除数据", "ms.ajax('" + url + "')");
		});
	},
	ajax : function(url,func) {
		$.ajax({
			type : "POST",
			url : url,
			dataType : "Json",
			success : function(msg) {
				if (msg.result) {
					if (func !=undefined) {
						
					} else {
						location.reload();
					}
					
				} else {
					
				}
			},
		});
	}
};
$(function() {
	ms.init();
})