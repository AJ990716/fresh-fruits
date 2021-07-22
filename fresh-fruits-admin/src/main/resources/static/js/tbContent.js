$(function() {
	loadData(1);
	//广告分类新增下拉框
	loadaddSelect();
});

function loadData(pageNumber) {
	$.post("http://127.0.0.1:8081/content/listPage", {
		"pageNumber": pageNumber,
		"pageSize": 3,
		"queryData": $("#queryData").val()
	}, function(result) {
		//alert(JSON.stringify(result));
		if(result.code == 200) {
			// 附上模板
			$("#res").setTemplateElement("template");
			// 给模板加载数据
			$("#res").processTemplate(result);
		}
	}, "json");
}

//广告分类下拉框
function loadaddSelect() {
	$.getJSON("http://127.0.0.1:8081/content_category/list", function(result) {
		//alert(JSON.stringify(result));
		for(var i = 0; i < result.data.length; i++) {
			$("#addSelect").append("<option value='" + result.data[i].id + "'>" + result.data[i].name + "</option>")
		}
	});
}

//新增表单文件异步上传
function upload_image_add() {
	//点击按钮，不是提交表单
	//模拟一个表单对象
	var formData = new FormData();
	//向表单中新增一个文件上传框
	formData.append("file", document.getElementById("file_add").files[0]);
	//异步提交上面模拟的表单
	$.ajax({
		url: "http://127.0.0.1:8081/file/upload", //后台准备一个文件上传的控制器
		type: "post", //一定是post请求 文件上传是post
		data: formData, //要提交的数据
		contentType: false, //必须为false，文件上传的时候默认才会有正确 content-Type
		processData: false, //必须为false，异步对象XMLhttpRequest才会对文件上传表单正确处理
		success: function(result) {
			alert(JSON.stringify(result));
			//页面回显上传成功的图片
			if(result.code == 200) {
				$("#file_add").val("");
				//回显图片
				$("#img_show_add").prop("src", result.data);
				//藏值，提交表单新增到数据库
				$("#upload_img_add_pic").val(result.data);
			} else {
				alert("文件上传失败，请联系运维....");
			}
		}
	});

}

//新增
function save() {
	//表单的数据
	var data = $("#addForm").serialize();
	alert(data);
	$.post("http://127.0.0.1:8081/content/add", data, function(result) {
		if(result.code == 200) {
			alert("新增成功");
			window.location.reload();
		} else {
			alert("新增异常");
		}
	});
}


//数据回显
function findOne(id) {
	$.getJSON("http://127.0.0.1:8081/content/findById/" + id, function(result) {
		//alert(JSON.stringify(result));
		$("#id").val(result.data.id); //隐藏ID
		$("#title").val(result.data.title); //回显title
		$("#url").val(result.data.url); //回显url
		$("#img_show_update").prop("src",result.data.image); //回显image
		$("#upload_img_update_pic").val(result.data.image); 
		$("#orders").val(result.data.orders); //回显url
		var categoryId=result.data.categoryId;
		
		var cks=$("[lang='status']");
		for(var i=0;i<cks.length;i++){
			if(cks[i].value==result.data.status){
				cks[i].checked=true;
			}
		}
		
		//下拉框
		$.getJSON("http://127.0.0.1:8081/content_category/list", function(result) {
		    //alert(JSON.stringify(result));
			for(var i = 0; i < result.data.length; i++) {
				$("#updateSelect").append("<option value='" + result.data[i].id + "'>" + result.data[i].name + "</option>")
			}
			//让下拉框选中
			$("#updateSelect").val(categoryId);
	    });
		
		
	});
}


//更新广告图片
function upload_image_update(){
	
	//点击按钮，不是提交表单
	//模拟一个表单对象
	var formData = new FormData();
	//向表单中新增一个文件上传框
	formData.append("file", document.getElementById("file_update").files[0]);
	//异步提交上面模拟的表单
	$.ajax({
		url: "http://127.0.0.1:8081/file/upload", //后台准备一个文件上传的控制器
		type: "post", //一定是post请求 文件上传是post
		data: formData, //要提交的数据
		contentType: false, //必须为false，文件上传的时候默认才会有正确 content-Type
		processData: false, //必须为false，异步对象XMLhttpRequest才会对文件上传表单正确处理
		success: function(result) {
			alert(JSON.stringify(result));
			//页面回显上传成功的图片
			if(result.code == 200) {
				$("#file_update").val("");
				//回显图片
				$("#img_show_update").prop("src", result.data);
				//藏值，提交表单新增到数据库
				$("#upload_img_update_pic").val(result.data);
			} else {
				alert("文件上传失败，请联系运维....");
			}
		}
	});
	
}


function update(){
	alert($("#updateForm").serialize());
	//Ajax发送普通请求，数据更新
	$.ajax({
		url: "http://127.0.0.1:8081/content/update",
		data: $("#updateForm").serialize(),
		type: "put", //发送put请求
		dataType: "json",
		success: function(result) {
			if(result.code == 200) {
				alert("更新成功");
				window.location.reload();
			}
		}
	});
}
