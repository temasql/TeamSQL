$(document).ready(function() {
	
	$("#createProcedureSpan").on("click", function() {
		
		$("#procedure_name").val("");
		$("#tableBody2").empty();
		cnt2 = 0;
		
		$("#procedurePackageModal").css("display", "block");
	});
	
	$("#addImg2").on("click", function() {
		cnt2 += 1;
		var temp = "<tr id='trssId"+cnt2+"'>" +
						"<td class='tds2'>" +
							"<input type='text' name='param_name2' class='form-control' value='PARAM"+cnt2+"'/>" +
						"</td>";
		temp += "<td class='tds2'>" +
					"<select class='form-control' name='param_mode2'>" +
						"<option><지정되지 않음></option>" +
						"<option selected>IN</option>" +
						"<option>OUT</option>" +
						"<option>IN OUT</option>" +
					"</select>" +
				"</td>";
		temp += "<td class='tds2'>" +
					"<select class='form-control' name='param_type2'>" +
						"<option>VARCHAR2</option>" +
						"<option>NUMBER</option>" +
						"<option>DATE</option>" +
						"<option>CLOB</option>" +
						"<option>BLOB</option>" +
					"</select>" +
				"</td>";
		temp += "<td class='tds2'><input class='form-control' name='param_default2' value=' ' type='text'/></td></tr>";
		$("#tableBody2").append(temp);
	});
	
	$("#deleteImg2").on("click", function() {
		var deleteId = $("#procedureHidden").val();
		$("#" + deleteId).remove();
	});
	
	$(document).on("click", ".tds2", function() {
		$("#procedureHidden").val($(this).parent().attr("id"));
	});
	
	$("#createProcedureBtn").on("click", function() {
		if($("#procedure_name").val().trim() <= 0) {
			alert("함수 이름을 입력해주세요.");
			$("#procedure_name").val("");
			$("#procedure_name").focus();
			return;
		}
		$.ajax({
			url : "/sqlEditor/createProcedure",
			method : "post",
			data : $("#createProcedureFrm").serialize(),
			success : function(data) {
				$("#procedurePackageModal").css("display", "none");
				editor.insert("\n");
				editor.getSelection().moveCursorLineEnd();
				editor.getSelection().moveCursorDown();
				editor.insert(data);
			}
		});
	});
	
	$("#readProcedureSelect").on("change", function() {
		$("#readProcedureDiv").empty();
		var selectVal = $("#readProcedureSelect").val().trim();
		var procedureName = $("#procedureName").val().trim();
		var accountId = $("#procedureId").val().trim();
		if(selectVal == "코드") {
			$.ajax({
				url : "/sqlEditor/readProcedure",
				method : "post",
				data : "accountId=" + accountId + "&procedureName=" + procedureName,
				success :  function(data) {
					var temp = "<br><br><h4>" + data + "</h4>";
					$("#readProcedureDiv").append(temp);
				}
			});
		}else {
			$.ajax({
				url : "/sqlEditor/procedureDetail",
				method : "post",
				data : "accountId=" + accountId + "&procedureName=" + procedureName,
				success : function(data) {
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
					$("#readProcedureDiv").append(temp);
				}
			});
		}
	});
	
	
});

var cnt2 = 0;