$(function() {

	/**
	 * 功能说明：手风琴开始
	 * ==========================================================================================
	 */
	$(".orderbody")
		.eq(0)
		.css({
			display: "block"
		});
	$(".remove").click(function(e) {
		e.stopPropagation();
	});
	$(".ordertitle").click(function(e) {
		if(
			$(this)
			.next()
			.css("display") == "none"
		) {
			$(this)
				.next()
				.slideDown(500)
				.siblings(".orderbody")
				.slideUp(500);
		} else {
			$(this)
				.next()
				.slideUp(500)
				.siblings(".orderbody")
				.slideDown(500);
		}
	});
	/**
	 * 功能说明：手风琴结束
	 * ==========================================================================================
	 */

});