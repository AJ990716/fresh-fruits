$(function() {

	/**
	 * 功能说明：右侧导航条开始
	 * ==========================================================================================
	 */
	// 当屏幕小于1300px的时候不再显示
	$(window).resize(function() {
		var pageWidth = $(window).width();
		if(pageWidth < 1300) {
			$('.sidebar-fixed-wrapper').fadeOut(500);
		}
	});

	// 出现隐藏
	$(window).scroll(function() {
		var scroTop = $(window).scrollTop();
		if(scroTop > 900) {
			$('.sidebar-fixed-wrapper').fadeIn(500);
		} else {
			$('.sidebar-fixed-wrapper').fadeOut(500);
		}
	});

	// 返回顶部
	$("#shortcut_totop").click(function() {
		$('body,html').animate({
			scrollTop: 0
		}, 1000);
	});

	// 分享网站到QQ空间
	qqZone = function() {
		var p = {
			url: 'http://127.0.0.1:8020/fresh/index.html',
			showcount: '1',
			/*是否显示分享总数,显示：'1'，不显示：'0' */
			desc: '',
			/*默认分享理由(可选)*/
			summary: '你想要的，在这里都能找到！',
			/*分享摘要(可选)*/
			title: '果然新鲜 - 鲜人一步，想你所想！',
			/*分享标题(可选)*/
			site: '',
			/*分享来源 如：腾讯网(可选)*/
			pics: '',
			style: '203',
			width: 98,
			height: 22
		};
		var s = [];
		for(var i in p) {
			s.push(i + '=' + encodeURIComponent(p[i] || ''));
		}
		url = "http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?" + s.join('&');
		console.log(url)
		window.open(url);
	};
	/**
	 * 功能说明：右侧导航条结束
	 * ==========================================================================================
	 */

});