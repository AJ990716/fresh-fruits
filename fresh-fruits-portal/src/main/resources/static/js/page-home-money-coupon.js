$(function() {

	/**
	 * 功能说明：选项卡切换开始
	 * ==========================================================================================
	 */
	$(".tab>li").eq(0).click(function() {
		$(".display>li").eq(0).css({
			"display": "block"
		}).siblings().css({
			"display": "none"
		})
		$(this).css({
			"border-bottom": "solid 2px #c61722",
			"color": "#c61722"
		}).siblings().css({
			"border-bottom": "solid 0px #00c3f5",
			"color": "black"
		})
	})
	$(".tab>li").eq(1).click(function() {
		$(".display>li").eq(1).css({
			"display": "block"
		}).siblings().css({
			"display": "none"
		})
		$(this).css({
			"border-bottom": "solid 2px #c61722",
			"color": "#c61722"
		}).siblings().css({
			"border-bottom": "solid 0px #00c3f5",
			"color": "black"
		})
	})
	$(".tab>li").eq(2).click(function() {
		$(".display>li").eq(2).css({
			"display": "block"
		}).siblings().css({
			"display": "none"
		})
		$(this).css({
			"border-bottom": "solid 2px #c61722",
			"color": "#c61722"
		}).siblings().css({
			"border-bottom": "solid 0px #00c3f5",
			"color": "black"
		})
	})
	/**
	 * 功能说明：选项卡切换结束
	 * ==========================================================================================
	 */

})