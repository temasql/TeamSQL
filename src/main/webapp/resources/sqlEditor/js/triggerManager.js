$(document).ready(function() {
	
	$("#tableSelect").on("change", function() {
		var tableName = $("#tableSelect").val();
		if(tableName == "") {
			alert("기본 객체를 선택해주세요.");
			return;
		}
		
		var account_id = $("#schema_id").val();
		
		$.ajax({
			url : "/sqlEditor/getColumns",
			method : "get",
			data : "tableName=" + tableName + "&account_id=" + account_id,
			success : function(data) {
				console.log(data);
				var temp = "";
				for (var i = 0; i < data.length; i++) {
					temp += "<option>" + data[i] + "</option>";
				}
				$("#columnSelect").html(temp);
			}
		});
	});
	
	$("#eventSelect").on("change", function() {
		var eventType = $("#eventSelect").val();
		alert(eventType);
		if(eventType.indexOf("UPDATE") != -1) {
			alert("update 포함");
			$("#columnSelect").removeAttribute("disabled");
		}
		
	});
	
});