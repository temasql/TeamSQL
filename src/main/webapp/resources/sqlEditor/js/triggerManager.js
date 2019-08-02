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
					temp += "<option class='columns'>" + data[i] + "</option>";
				}
				$("#columnSelect").html(temp);
			}
		});
		$(".events").prop("selected", false);
	});
	
	$("#eventSelect").on("change", function() {
		var eventType = $("#eventSelect").val();
		if(eventType.indexOf("UPDATE") != -1) {
			$("#columnSelect").removeAttr("disabled");
		}else {
			$("#columnSelect").attr("disabled","disabled");
			$(".columns").prop("selected", false);
		}
	});
	
	$("#createTriggerBtn").on("click", function() {
		if($("#triggerName").val().trim().length <= 0) {
			alert("트리거 이름을 입력해주세요.");
			$("#triggerName").focus();
			return;
		}
		
		if($("#tableSelect").val() == "") {
			alert("기본 객체를 선택해주세요.");
			return;
		}
		
		if($("#eventSelect").val() == null) {
			alert("하나 이상의 이벤트를 선택해주세요.");
			return;
		}
		
		$.ajax({
			url : "/sqlEditor/createTriggerReady",
			method : "post",
			data : $("#createTriggerFrm").serialize(),
			success : function(data) {
				$("#triggerPackageModal").css("display", "none");
				editor.insert("\n");
				editor.getSelection().moveCursorLineEnd();
				editor.getSelection().moveCursorDown();
				editor.insert(data);
			}
		});
		
	});
	
});