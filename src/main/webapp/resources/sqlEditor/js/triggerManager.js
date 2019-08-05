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

	$("#readTriggerSelect").on("change", function() {
		$("#readTriggerDiv").empty();
		
		var selectVal = $("#readTriggerSelect").val().trim();
		var triggerName = $("#triggerName").val().trim();
		var accountId = $("#triggerId").val().trim();
		if(selectVal == "코드") {
			$.ajax({
				url : "/sqlEditor/readTrigger",
				method : "post",
				data : "accountId=" + accountId + "&triggerName=" + triggerName,
				success :  function(data) {
					var temp = "<br><br><h4>" + data + "</h4>";
					$("#readTriggerDiv").append(temp);
				}
			});
		}else {
			$.ajax({
				url : "/sqlEditor/triggerDetail",
				method : "post",
				data : "triggerName=" + triggerName + "&accountId=" + accountId,
				success :  function(data) {
					var temp = "<br><br><table class='table table-hover'>" +
									"<tbody>" +
										"<tr class='table-active'>" +
											"<th scope='row'>OWNER</th>" +
											"<td>" + data.owner + "</td>" +
										"</tr>" +
										"<tr class='table-active'>" +
											"<th scope='row'>OBJECT_NAME</th>" +
											"<td>" + data.object_name + "</td>" +
										"</tr>" +
										"<tr class='table-active'>" +
											"<th scope='row'>SUBOBJECT_NAME</th>" +
											"<td>" + data.subobject_name + "</td>" +
										"</tr>" +
										"<tr class='table-active'>" +
											"<th scope='row'>OBJECT_ID</th>" +
											"<td>" + data.object_id + "</td>" +
										"</tr>" +
										"<tr class='table-active'>" +
											"<th scope='row'>DATA_OBJECT_ID</th>" +
											"<td>" + data.data_object_id + "</td>" +
										"</tr>" +
										"<tr class='table-active'>" +
											"<th scope='row'>OBJECT_TYPE</th>" +
											"<td>" + data.object_type + "</td>" +
										"</tr>" +
										"<tr class='table-active'>" +
											"<th scope='row'>CREATED</th>" +
											"<td>" + data.created + "</td>" +
										"</tr>" +
										"<tr class='table-active'>" +
											"<th scope='row'>LAST_DDL_TIME</th>" +
											"<td>" + data.last_ddl_time + "</td>" +
										"</tr>" +
										"<tr class='table-active'>" +
											"<th scope='row'>TIMESTAMP</th>" +
											"<td>" + data.timestamp + "</td>" +
										"</tr>" +
										"<tr class='table-active'>" +
											"<th scope='row'>STATUS</th>" +
											"<td>" + data.status + "</td>" +
										"</tr>" +
										"<tr class='table-active'>" +
											"<th scope='row'>TEMPORARY</th>" +
											"<td>" + data.temporary + "</td>" +
										"</tr>" +
										"<tr class='table-active'>" +
											"<th scope='row'>GENERATED</th>" +
											"<td>" + data.generated + "</td>" +
										"</tr>" +
										"<tr class='table-active'>" +
											"<th scope='row'>SECONDARY</th>" +
											"<td>" + data.secondary + "</td>" +
										"</tr>" +
										"<tr class='table-active'>" +
											"<th scope='row'>NAMESPACE</th>" +
											"<td>" + data.namespace + "</td>" +
										"</tr>" +
										"<tr class='table-active'>" +
											"<th scope='row'>EDITION_NAME</th>" +
											"<td>" + data.edition_name + "</td>" +
										"</tr>" +
									"</tbody>" +
								"</table>";
					$("#readTriggerDiv").append(temp);
				}
			});
		}
	});//$("#readTriggerSelect").on("change", function())
	
});