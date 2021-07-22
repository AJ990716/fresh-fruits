$(function() {

	/**
	 * 功能说明：页面图片懒加载开始
	 * ==========================================================================================
	 */
	$("img").lazyload({
		effect: "fadeIn",
		skip_invisible: false,
		failure_limit: 20
	});
	/**
	 * 功能说明：页面图片懒加载结束
	 * ==========================================================================================
	 */

	/**
	 * 功能说明：轮播区左侧菜单开始
	 * ==========================================================================================
	 */
	$(".category-item").hover(function() {
		$(".category-item").each(function() {
			$(this).removeClass("active");
		});
		$(this).addClass("active");
	});
	$(".category-list").hover(function() {
		$(this).addClass("active");
	});
	$(".category-list").mouseleave(function() {
		$(".category-item").each(function() {
			$(this).removeClass("active");
		});
		$(this).removeClass("active");
	});
	$(".category-panels").mouseenter(function() {
		$(this).prev().addClass("radius");
	});
	$(".category-panels").mouseleave(function() {
		$(this).prev().removeClass("radius");
	});
	/**
	 * 功能说明：轮播区左侧菜单结束
	 * ==========================================================================================
	 */

	/**
	 * 功能说明：轮播区轮播图开始
	 * ==========================================================================================
	 */
	var carousel_index = 0;
	var carousel_time = 6000;
	var carousel_counts = $(".banner .ec-silder ul li").length;

	$(".ec-silder-list>li").each(function(index) {
		$(".ec-slider-nav").append("<span title='" + index + "'></span>");
	});
	// 第一张显示
	$(".banner .ec-silder ul li").eq(0).fadeIn(750);
	// 插入小圆点
	$(".ec-slider-nav>span").eq(0).addClass("current");

	// prevPicFunction
	prevPicFunction = function() {
		// 上张图片索引
		carousel_index--;
		carousel_index = carousel_index <= -1 ? carousel_counts - 1 : carousel_index;
		// 所有图片隐藏
		$(".banner .ec-silder ul li").fadeOut();
		// 当前图片显示
		$(".banner .ec-silder ul li").eq(carousel_index).fadeIn(1000);

		// 移除所有选择
		$(".ec-slider-nav>span").removeClass("current");
		// 选择当前项目
		$(".ec-slider-nav>span").eq(carousel_index).addClass("current");
	}

	// nextPicFunction
	nextPicFunction = function() {
		// 下张图片索引
		carousel_index++;
		carousel_index = carousel_index >= carousel_counts ? 0 : carousel_index;
		// 所有图片隐藏
		$(".banner .ec-silder ul li").fadeOut();
		// 当前图片显示
		$(".banner .ec-silder ul li").eq(carousel_index).fadeIn(1000);

		// 移除所有选择
		$(".ec-slider-nav>span").removeClass("current");
		// 选择当前项目
		$(".ec-slider-nav>span").eq(carousel_index).addClass("current");
	};

	//开启定时任务
	carousel_timer_id = setInterval(nextPicFunction, carousel_time);

	// 上一张
	$(".button-slider-prev").click(function() {
		clearInterval(carousel_timer_id);
		prevPicFunction();
	});
	$(".button-slider-prev").mouseenter(function() {
		clearInterval(carousel_timer_id);
	});
	$(".button-slider-prev").mouseleave(function() {
		carousel_timer_id = setInterval(nextPicFunction, carousel_time);
	});

	// 下一张
	$(".button-slider-next").click(function() {
		clearInterval(carousel_timer_id);
		nextPicFunction();
	});
	$(".button-slider-next").mouseenter(function() {
		clearInterval(carousel_timer_id);
	});
	$(".button-slider-next").mouseleave(function() {
		carousel_timer_id = setInterval(nextPicFunction, carousel_time);
	});

	// 进入小圆点
	$(".ec-slider-nav>span").mouseenter(function() {
		// 立刻清除定时器
		clearInterval(carousel_timer_id);
		// 获取当前索引
		var now_index = $(this).attr("title");
		// 所有图片隐藏
		$(".banner .ec-silder ul li").hide();
		// 当前图片显示
		$(".banner .ec-silder ul li").eq(now_index).fadeIn(1000);
		// 清除所有current
		$(".ec-slider-nav>span").removeClass("current");
		//为当前导航点添加样式
		$(".ec-slider-nav>span").eq(now_index).addClass("current");
		// 重设index
		carousel_index = now_index;
	});

	// 离开小圆点
	$(".ec-slider-nav>span").mouseleave(function() {
		carousel_timer_id = setInterval(nextPicFunction, carousel_time);
	});

	// 进入banner
	$(".banner .ec-silder ul li img").mouseenter(function() {
		clearInterval(carousel_timer_id);
	});

	// 离开banner
	$(".banner .ec-silder ul li img").mouseleave(function() {
		carousel_timer_id = setInterval(nextPicFunction, carousel_time);
	});
	/**
	 * 功能说明：轮播区轮播图结束
	 * ==========================================================================================
	 */

	/**
	 * 功能说明：体验区脚本代码开始
	 * ==========================================================================================
	 */
	$(".experience .show-box>li").eq(0).css({
		"display": "block"
	})
	$(".experience .show-box>li").eq(1).css({
		"display": "none"
	})
	$(".experience .show-box>li").eq(2).css({
		"display": "none"
	})
	$(".experience .show-box>li").eq(3).css({
		"display": "none"
	})
	$(".experience .optionbox>li").eq(0).click(function() {
		$(".experience .show-box>li").eq(0).css({
			"display": "block"
		}).siblings().css({
			"display": "none"
		})
		$(".experience .firstimg>li").eq(0).css({
			"display": "block"
		}).siblings().css({
			"display": "none"
		})
		$(".experience .firsttext>li").eq(0).css({
			"display": "block"
		}).siblings().css({
			"display": "none"
		})
	})
	$(".experience .optionbox>li").eq(1).click(function() {
		$(".experience .show-box>li").eq(1).css({
			"display": "block"
		}).siblings().css({
			"display": "none"
		})
		$(".experience .firstimg>li").eq(1).css({
			"display": "block"
		}).siblings().css({
			"display": "none"
		})
		$(".experience .firsttext>li").eq(1).css({
			"display": "block"
		}).siblings().css({
			"display": "none"
		})
	})
	$(".experience .optionbox>li").eq(2).click(function() {
		$(".experience .show-box>li").eq(2).css({
			"display": "block"
		}).siblings().css({
			"display": "none"
		})
		$(".experience .firstimg>li").eq(2).css({
			"display": "block"
		}).siblings().css({
			"display": "none"
		})
		$(".experience .firsttext>li").eq(2).css({
			"display": "block"
		}).siblings().css({
			"display": "none"
		})
	})
	$(".experience .optionbox>li").eq(3).click(function() {
		$(".experience .show-box>li").eq(3).css({
			"display": "block"
		}).siblings().css({
			"display": "none"
		})
		$(".experience .firstimg>li").eq(3).css({
			"display": "block"
		}).siblings().css({
			"display": "none"
		})
		$(".experience .firsttext>li").eq(3).css({
			"display": "block"
		}).siblings().css({
			"display": "none"
		})
	})

	/*
	 * 内部轮播切换
	 */
	var xiindex = 0;
	$(".show-box-box>ul").eq(0).css({
		"display": "block"
	})
	$(".show-box>li").eq(0).find("div").eq(0).click(function() {
		xiindex++;
		if(xiindex == 3) {
			xiindex = 0;
		}
		$(".show-box>li").eq(0).find("ul").eq(xiindex).css({
			"display": "block"
		}).siblings("ul").css({
			"display": "none"
		})
	})
	$(".show-box>li").eq(0).find("div").eq(1).click(function() {
		xiindex--;
		if(xiindex == -1) {
			xiindex = 2;
		}
		$(".show-box>li").eq(0).find("ul").eq(xiindex).css({
			"display": "block"
		}).siblings("ul").css({
			"display": "none"
		})
	})

	$(".show-box-box>ul").eq(0).css({
		"display": "block"
	})
	$(".show-box>li").eq(1).find("div").eq(0).click(function() {
		xiindex++;
		if(xiindex == 3) {
			xiindex = 0;
		}
		$(".show-box>li").eq(1).find("ul").eq(xiindex).css({
			"display": "block"
		}).siblings("ul").css({
			"display": "none"
		})
	})
	$(".show-box>li").eq(1).find("div").eq(1).click(function() {
		xiindex--;
		if(xiindex == -1) {
			xiindex = 2;
		}
		$(".show-box>li").eq(1).find("ul").eq(xiindex).css({
			"display": "block"
		}).siblings("ul").css({
			"display": "none"
		})
	})
	$(".show-box-box>ul").eq(0).css({
		"display": "block"
	})
	$(".show-box>li").eq(2).find("div").eq(0).click(function() {
		xiindex++;
		if(xiindex == 3) {
			xiindex = 0;
		}
		$(".show-box>li").eq(2).find("ul").eq(xiindex).css({
			"display": "block"
		}).siblings("ul").css({
			"display": "none"
		})
	})
	$(".show-box>li").eq(2).find("div").eq(1).click(function() {
		xiindex--;
		if(xiindex == -1) {
			xiindex = 2;
		}
		$(".show-box>li").eq(2).find("ul").eq(xiindex).css({
			"display": "block"
		}).siblings("ul").css({
			"display": "none"
		})
	})
	$(".show-box-box>ul").eq(0).css({
		"display": "block"
	})
	$(".show-box>li").eq(3).find("div").eq(0).click(function() {
		xiindex++;
		if(xiindex == 3) {
			xiindex = 0;
		}
		$(".show-box>li").eq(3).find("ul").eq(xiindex).css({
			"display": "block"
		}).siblings("ul").css({
			"display": "none"
		})
	})
	$(".show-box>li").eq(3).find("div").eq(1).click(function() {
		xiindex--;
		if(xiindex == -1) {
			xiindex = 2;
		}
		$(".show-box>li").eq(3).find("ul").eq(xiindex).css({
			"display": "block"
		}).siblings("ul").css({
			"display": "none"
		})
	})
	/**
	 * 功能说明：体验区脚本代码结束
	 * ==========================================================================================
	 */

	/**
	 * 功能说明：频道区轮播图开始
	 * ==========================================================================================
	 */
	$("#sub_banner li a img").eq(0).show("fast");
	var sub_banner_index = 1;
	setInterval(function() {
		/*隐藏所有的图片*/
		$("#sub_banner li a img").each(function() {
			$(this).hide();
		});

		/*显示当前图片*/
		$("#sub_banner li a img").eq(sub_banner_index).fadeIn(1500);

		/*索引递增*/
		sub_banner_index++;

		/*判断索引*/
		if(sub_banner_index == $("#sub_banner li a img").length) {
			sub_banner_index = 0;
		}
	}, 6000);
	/**
	 * 功能说明：频道区轮播图结束
	 * ==========================================================================================
	 */

	/**
	 * 功能说明：用户评论开始
	 * ==========================================================================================
	 */
	$('.comment-list').slick({
		autoplay: true,
		infinite: true,
		lazyLoad: 'ondemand',
		speed: 500,
		slidesToShow: 3,
		slidesToScroll: 1,
		adaptiveHeight: true
	});
	/**
	 * 功能说明：用户评论结束
	 * ==========================================================================================
	 */

});