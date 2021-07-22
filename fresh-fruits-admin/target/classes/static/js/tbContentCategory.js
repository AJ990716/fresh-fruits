$(function() {
	loadData(1);
});

function loadData(pageNumber){
	$.post("http://127.0.0.1:8081/content_category/listPage",{"pageNumber":pageNumber,"pageSize":10,"queryData":$("#queryData").val()}, function(result) {
		//alert(JSON.stringify(result));
		if(result.code == 200) {
			// 附上模板
			$("#res").setTemplateElement("template");
			// 给模板加载数据
			$("#res").processTemplate(result);
		}
	},"json");
}


//新增
function save() {
	//表单的数据
	var data = $("#addForm").serialize();
	//alert(data);
	$.post("http://127.0.0.1:8081/content_category/add", data, function(result) {
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
	$.getJSON("http://127.0.0.1:8081/content_category/findById/" + id, function(result) {
		//alert(JSON.stringify(result));
		$("#id").val(result.data.id); //隐藏ID
		$("#name").val(result.data.name); //回显name
	});
}

//更新
function update() {
	//alert($("#updateForm").serialize());
	//Ajax发送普通请求，数据更新
	$.ajax({
		url: "http://127.0.0.1:8081/content_category/update",
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

function deleteOne(id) {
	if(confirm("确定要删除吗?")) {
		//发送delete请求
		$.ajax({
			url: "http://127.0.0.1:8081/content_category/delete/" + id,
			type: "delete", //发送delete请求
			dataType: "json",
			success: function(result) {
				if(result.code == 200) {
					alert("删除成功");
					window.location.reload();
				} else if(result.code == 800) {
					alert("数据在使用中，请勿删除！");
				}
			}
		});
	}
}

//批量删除
function deleteList() {
	var cks = $(":checkbox:checked");
	var ids = new Array();
	if(cks.length > 0) {
		cks.each(function(index, element) {
			var id = $(element).val();
			ids.push(id);
		});
		//alert(ids.toString());
		$.getJSON("http://127.0.0.1:8081/content_category/deleteList/" + ids, function(result) {
			//alert(JSON.stringify(result));
			if(result.code == 200) {
				alert("批量删除成功");
				window.location.reload();
			} else {
				alert("删除异常");
			}
		});

	} else {
		alert("请选中要删除的数据");
	}

}