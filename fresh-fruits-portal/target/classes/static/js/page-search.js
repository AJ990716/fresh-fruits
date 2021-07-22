$(function() {

	/**
	 * 功能：checked切换开始
	 * ==========================================================================================
	 */
	$(".information li").hover(function() {
		$(this).children("div.checkbox").children().css({
			"display": "block"
		})
	}).mouseleave(function() {
		if($(this).children("div.checkbox").children().prop("checked")) {
			return;
		} else {
			$(this).children("div.checkbox").children().css({
				"display": "none"
			});
		}
	});
	/**
	 * 功能：checked切换结束
	 * ==========================================================================================
	 */

});