$(function() {

	/**
	 * 功能说明：模态框管理中心开始
	 * ==========================================================================================
	 */
	// 修改密码脚本
	changePassword = function() {
		Dialog({
			title: "修改密码",
			content: $(".changePasswordBox").html(),
			ok: {
				text: "确定", // 确定按钮的文字
				waiting: false, // 点击确定按钮时，是否显示 waiting 效果
				waitingText: "确定", // 显示 waiting 效果时，确定按钮文字的变化
				notClose: false, // 点击确定按钮是否关闭对话框
				callback: function() {} // 点击确定按钮的回调函数
			}
		});
	};

	// 设置邮箱脚本
	changeEmail = function() {
		Dialog.prompt("设置邮箱", function(value) {
			if(value == "123456") {
				alert("正确");
				Dialog.close();
			} else {
				alert("错误");
			}
		});
	};

	// 修改手机脚本
	changePhone = function() {
		Dialog({
			title: "修改手机",
			content: $(".changePhoneBox").html(),
			ok: {
				text: "确定", // 确定按钮的文字
				waiting: false, // 点击确定按钮时，是否显示 waiting 效果
				waitingText: "确定", // 显示 waiting 效果时，确定按钮文字的变化
				notClose: false, // 点击确定按钮是否关闭对话框
				callback: function() {} // 点击确定按钮的回调函数
			}
		});
	};

	// 设置密保问题
	changeAnswer = function() {
		Dialog({
			title: "设置密保",
			content: $(".changeAnswerBox").html(),
			ok: {
				text: "确定", // 确定按钮的文字
				waiting: false, // 点击确定按钮时，是否显示 waiting 效果
				waitingText: "确定", // 显示 waiting 效果时，确定按钮文字的变化
				notClose: false, // 点击确定按钮是否关闭对话框
				callback: function() {} // 点击确定按钮的回调函数
			}
		});
	};
	/**
	 * 功能说明：模态框管理中心结束
	 * ==========================================================================================
	 */

});