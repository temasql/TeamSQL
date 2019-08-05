$(document).ready(function() {
	
	$("#createFunctionSpan").on("click", function() {
		
		$("#function_name").val("");
		$("#tableBody").empty();
		cnt = 0;
		
		$("#fucntionPackageModal").css("display", "block");
	});
	
	$("#addImg").on("click", function() {
		cnt += 1;
		var temp = "<tr id='trsId"+cnt+"'>" +
						"<td class='tds'>" +
							"<input type='text' name='param_name' class='form-control' value='PARAM"+cnt+"'/>" +
						"</td>";
		temp += "<td class='tds'>" +
					"<select class='form-control' name='param_mode'>" +
						"<option><지정되지 않음></option>" +
						"<option selected>IN</option>" +
						"<option>OUT</option>" +
						"<option>IN OUT</option>" +
					"</select>" +
				"</td>";
		temp += "<td class='tds'>" +
					"<select class='form-control' name='param_type'>" +
						"<option>VARCHAR2</option>" +
						"<option>NUMBER</option>" +
						"<option>DATE</option>" +
						"<option>CLOB</option>" +
						"<option>BLOB</option>" +
					"</select>" +
				"</td>";
		temp += "<td class='tds'><input class='form-control' name='param_default' value=' ' type='text'/></td></tr>";
		$("#tableBody").append(temp);
	});
	
	$("#deleteImg").on("click", function() {
		var deleteId = $("#deleteHidden").val();
		$("#" + deleteId).remove();
	});
	
	$(document).on("click", ".tds", function() {
		$("#deleteHidden").val($(this).parent().attr("id"));
	});
	
	$("#createFunctionBtn").on("click", function() {
		if($("#function_name").val().trim() <= 0) {
			alert("함수 이름을 입력해주세요.");
			$("#function_name").val("");
			$("#function_name").focus();
			return;
		}
		
		$.ajax({
			url : "/sqlEditor/createFunction",
			method : "post",
			data : $("#createFunctionFrm").serialize(),
			success : function(data) {
				$("#fucntionPackageModal").css("display", "none");
				editor.insert("\n");
				editor.getSelection().moveCursorLineEnd();
				editor.getSelection().moveCursorDown();
				editor.insert(data);
			}
		});
	});
	
	$("#readFunctionSelect").on("change", function() {
		$("#readFunctionDiv").empty();
		var selectVal = $("#readFunctionSelect").val().trim();
		var functionName = $("#functionName").val().trim();
		var accountId = $("#functionId").val().trim();
		
		if(selectVal == "코드") {
			$.ajax({
				url : "/sqlEditor/readFunction",
				method : "post",
				data : "accountId=" + accountId + "&functionName=" + functionName,
				success :  function(data) {
					data.replace(/\n/gi, "<br>")
					var temp = "<br><br><h4>" + data + "</h4>";
					$("#readFunctionDiv").append(temp);
				}
			});
		}else {
			$.ajax({
				url : "/sqlEditor/fucntionDetail",
				method : "post",
				data : "accountId=" + accountId + "&functionName=" + functionName,
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
					$("#readFunctionDiv").append(temp);
				}
			});
		}
		
	});
	
	
});


var cnt = 0;