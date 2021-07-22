$(function() {
	loadData(1);
});

function loadData(pageNumber) {
	$.post("http://localhost:8081/itemcat/listPage", {
		"pageNumber": pageNumber,
		"pageSize": 3,
		"name": $("#name").val(),
		"parent_id":$("#parent_id").val()
	}, function(result) {
		//alert(JSON.stringify(result));
		if(result.code == 200) {
			// 附上模板
			$("#res").setTemplateElement("template");
			// 给模板加载数据
			$("#res").processTemplate(result);
			
			//获取导航菜单，展示出来
			var menus=result.data.pagedata.str;
			$("#menus").html("");
			$("#menus").append(menus);
			
			// 是否是第三级
			var flag=result.data.pagedata.flag;
			if(flag==true){
				$("[name=xiayiji]").remove();
			}
		}
	}, "json");
}

//查询下一级
function queryData(parent_id){
	//alert(parent_id);
	$.post("http://localhost:8081/itemcat/listPage", {
		"pageNumber": 1,
		"pageSize": 3,
		"name": $("#name").val(),
		"parent_id":parent_id
	}, function(result) {
		//给藏的值赋值
		$("#parent_id").val(parent_id);
		//alert(JSON.stringify(result));
		if(result.code == 200) {
			// 附上模板
			$("#res").setTemplateElement("template");
			// 给模板加载数据
			$("#res").processTemplate(result);
			
				//获取导航菜单，展示出来
			var menus=result.data.pagedata.str;
			$("#menus").html("");
			$("#menus").append(menus);
			
			// 是否是第三级
			var flag=result.data.pagedata.flag;
			if(flag==true){
				$("[name=xiayiji]").remove();
			}
		}
	}, "json");
}
