var cnt3 = 0;
$(document).ready(function() {
	$(".mycontainer").css("width","100%");
	$(".mycontainer").css("margin-left","0%");
	$(".mycontainer").css("margin-right","0%");
	
	$("#createTestDataBtn").on("click", function() {
		var dataCnt = $("#dataCnt").val().trim();
		if($("#table_name").val().trim() == "") {
			alert("테이블명을 입력해주세요.");
			$("#table_name").focus();
			return;
		}
		if(dataCnt == "" || dataCnt <= 0 || dataCnt > 100) {
			alert("수량은 1~100 사이에서 선택해주세요.");
			$("#dataCnt").val("");
			$("#dataCnt").focus();
			return;
		}
		
		$.ajax({
			url : "/sqlEditor/createTestData",
			method : "post",
			data : $("#tdFrm").serialize(),
			success : function(data) {
				$("#testDataModal").css("display", "none");
				for (var i = 0; i < data.length; i++) {
					editor.insert("\n");
					editor.getSelection().moveCursorLineEnd();
					editor.getSelection().moveCursorDown();
					editor.insert(data[i]);
				}
			},
			error : function(e) {
				alert("error");
			}
		});
		
		
	});
	
	$("#testData").on("click", function() {
		$("#table_name").val("");
		$("#dataCnt").val("");
		$("#testDataTable").empty();
		cnt3 = 0;
		$("#testDataModal").css("display", "block");
	});
	
	$("#testDataAddImg").on("click", function() {
		cnt3 += 1;
		var temp = "<tr id='tdId"+cnt3+"'>" +
						"<td class='tddds'>" +
							"<input type='text' name='column_name' class='form-control' value='COLUMN"+cnt3+"'/>" +
						"</td>";
		temp += "<td class='tddds'>" +
					"<select class='form-control' name='data_type'>" +
						"<option>이름</option>" +
						"<option>전화번호</option>" +
						"<option>이메일</option>" +
						"<option>날짜</option>" +
						"<option>날짜(오늘)</option>" +
						"<option>국적</option>" +
						"<option>도시(대한민국)</option>" +
					"</select>" +
				"</td>";
		temp += "<td class='tddds'>" +
					"<select class='form-control' name='isNull'>" +
						"<option>NOT NULL</option>" +
						"<option>NULL</option>" +
					"</select>" +
				"</td>";
		$("#testDataTable").append(temp);
	});
	
	$("#testDataDeleteImg").on("click", function() {
		var deleteId = $("#testDataHidden").val();
		$("#" + deleteId).remove();
	});
	
	$(document).on("click", ".tddds", function() {
		$("#testDataHidden").val($(this).parent().attr("id"));
	});
	
	
	$("#hiddenDiv").on("mousedown", function() {
		var accountListSize = $("#accountListSize").val();
		if(accountListSize <= 0) {
			alert("DB계정을 먼저 생성해주세요.");
		}else {
			$("#hiddenDiv").css("z-index", 0);
			$("#editor").find("textarea").first().focus();
		}
	});
	
	//alert 띄우기
	if($("#msg").val().trim().length > 0) {
		alert($("#msg").val());
	}
	
	// a태그 href막기
	$('a[href="#none"]').click(function(e) {
		e.preventDefault();
	});
	
	// 워크시트 다운로드 버튼 이벤트
	$("#worksheetSave").on("click", function() {
		var fileName = prompt("저장하실 파일명을 입력해주세요.");
		saveToFile_Chrome(fileName, editor.getValue());
	});
	
	// 프로시저 조회 모달창 띄우기
	$("#readProcedureSpan").on("click", function() {
		$("#selectCode3").prop("selected", "selected");
		$("#readProcedureDiv").empty();
		var procedureName = $("#procedureName").val().trim();
		var accountId = $("#procedureId").val().trim();
		$.ajax({
			url : "/sqlEditor/readProcedure",
			method : "post",
			data : "accountId=" + accountId + "&procedureName=" + procedureName,
			success :  function(data) {
				var temp = "<br><br><h4>" + data + "</h4>";
				$("#readProcedureDiv").append(temp);
			}
		});
		$("#readProcedureModal").css("display", "block");
	});
	
	// 함수 조회 모달창 띄우기
	$("#readFunctionSpan").on("click", function() {
		$("#selectCode2").prop("selected", "selected");
		$("#readFunctionDiv").empty();
		var functionName = $("#functionName").val().trim();
		var accountId = $("#functionId").val().trim();
		
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
		$("#readFunctionModal").css("display", "block");
	});
	
	// 트리거 조회 모달창 띄우기
	$("#readTriggerSpan").on("click", function() {
		$("#selectCode").prop("selected", "selected");
		$("#readTriggerDiv").empty();
		var triggerName = $("#triggerName").val().trim();
		var accountId = $("#triggerId").val().trim();
		
		$.ajax({
			url : "/sqlEditor/readTrigger",
			method : "post",
			data : "accountId=" + accountId + "&triggerName=" + triggerName,
			success :  function(data) {
				var temp = "<br><br><h4>" + data + "</h4>";
				$("#readTriggerDiv").append(temp);
			}
		});
		$("#readTriggerModal").css("display", "block");
	});
	
	// 트리거 삭제 버튼 클릭 이벤트
	$("#deleteTriggerSpan").on("click", function() {
		var triggerName = $("#triggerName").val().trim();
		var accountId = $("#triggerId").val().trim();
		$.ajax({
			url : "/sqlEditor/deleteTrigger",
			method : "post",
			data : "accountId=" + accountId + "&triggerName=" + triggerName,
			success :  function(data) {
				if(data == 0) {
					alert("트리거 삭제에 성공하였습니다.");
					location.replace("/sqlEditor/sqlEditorMain");
				}else {
					alert("트리거 삭제에 실패하였습니다.");
				}
			}
		});
	});
	
	// 프로시저 삭제 버튼 클릭 이벤트
	$("#deleteProcedureSpan").on("click", function() {
		var procedureName = $("#procedureName").val().trim();
		var accountId = $("#procedureId").val().trim();
		$.ajax({
			url : "/sqlEditor/deleteProcedure",
			method : "post",
			data : "accountId=" + accountId + "&procedureName=" + procedureName,
			success :  function(data) {
				if(data == 0) {
					alert("프로시저 삭제에 성공하였습니다.");
					location.replace("/sqlEditor/sqlEditorMain");
				}else {
					alert("프로시저 삭제에 실패하였습니다.");
				}
			}
		});
	});
	
	// 함수 삭제 버튼 클릭 이벤트
	$("#deleteFunctionSpan").on("click", function() {
		var functionName = $("#functionName").val().trim();
		var accountId = $("#functionId").val().trim();
		$.ajax({
			url : "/sqlEditor/deleteFunction",
			method : "post",
			data : "accountId=" + accountId + "&functionName=" + functionName,
			success :  function(data) {
				if(data == 0) {
					alert("함수 삭제에 성공하였습니다.");
					location.replace("/sqlEditor/sqlEditorMain");
				}else {
					alert("함수 삭제에 실패하였습니다.");
				}
			}
		});
	});
	
	// 트리거 패키지 모달창 띄우기
	$("#createTriggerSpan").on("click", function() {
		var account_id = $("#schema_id").val(); // 계정명(원본)
		
		$.ajax({
			url : "/sqlEditor/createTriggerReady",
			method : "get",
			data : "account_id=" + account_id,
			success : function(data) {
				console.log(data);
				var temp = "";
				for (var i = 0; i < data.length; i++) {
					if(i == 0)
						temp += "<option></option>";
					temp += "<option>" + data[i] + "</option>";
				}
				$("#tableSelect").html(temp);
			}
		});
		$("#triggerName").val("");
		$("#triggerPackageModal").css("display", "block");
	});
	
	// 워크시트 불러오기 모달창 띄우기
	$("#worksheetLoad").on("click", function() {
		var input = document.createElement("input");
		input.type = "file";
		input.accept = "text/plain";
		
		input.onchange = function(event) {
			processFile(event.target.files[0]);
		};
		
		input.click();
	});
	
	// 새로고침 버튼 이벤트
	$("#refresh").on("click", function() {
		location.replace("/sqlEditor/sqlEditorMain");
	});
	
	// 결과하면 클리어
	$("#clearSpan").on("click", function() {
		if($("#scriptViewArea").css("display") == "block") {
			$("#scriptViewArea").text("");
		}else {
			$("#resultTable").jqGrid('GridUnload');
		}
	});
	
	// 결과창으로 바꾸기
	$("#resultViewSpan").on("click", function() {
		$("#resultTable").css("display", "block");
		$("#scriptViewArea").css("display", "none");
		$("#resultViewSpan").css("color", "red");
		$("#scriptViewSpan").css("color", "black");
	});
	
	// 스크립트 창으로 바꾸기
	$("#scriptViewSpan").on("click", function() {
		$("#scriptViewArea").css("display", "block");
		$("#resultTable").css("display", "none");
		$("#scriptViewSpan").css("color", "red");
		$("#resultViewSpan").css("color", "black");
	});
	
	// DB계정 생성 모달창 띄우기
	$("#accountImg").on("click", function() {
		$("#accountName").val("");
		$("#accountPw").val("");
		$("#chatRoomName").val("");
		$("#accountModal").css("display", "block");
	});
	
	// DB계정 삭제 모달창 띄우기
	$("#accountDeleteSpan").on("click", function() {
		var account_id = $("#accou_id").val();
		var user_id = $("#userId").val();
		var underBarIdx = account_id.lastIndexOf("_");
		var creator = account_id.substring(underBarIdx+1);
		if(creator != user_id) {
			alert("DB계정 생성자만 이용가능한 기능입니다.");
			return;
		}
		$("#accountDeleteModal").css("display", "block");
	});
	
	// DB계정 비밀번호 찾기 모달창 띄우기
	$("#accountPwFindSpan").on("click", function() {
		var account_id = $("#accou_id").val();
		var user_id = $("#userId").val();
		var underBarIdx = account_id.lastIndexOf("_");
		var creator = account_id.substring(underBarIdx+1);
		if(creator != user_id) {
			alert("DB계정 생성자만 이용가능한 기능입니다.");
			return;
		}
		$("#accountPwFindModal").css("display", "block");
	});
	
	// DB계정 비밀번호 수정 모달창 띄우기
	$("#accountPwUpdateSpan").on("click", function() {
		var account_id = $("#accou_id").val();
		var user_id = $("#userId").val();
		var underBarIdx = account_id.lastIndexOf("_");
		var creator = account_id.substring(underBarIdx+1);
		if(creator != user_id) {
			alert("DB계정 생성자만 이용가능한 기능입니다.");
			return;
		}
		$("#accountPwUpdateModal").css("display", "block");
	});
	
	//일정관리 팝업창 띄우기(손주형)
	$("#calendarPopup").on("click", function(){
		var temp = $("#acc_id").val();
		var w = 912; //팝업창의 가로크기(임의)
		var h = 984; //팝업창의 세로크기(임의)
		var x = screen.AvailWidth-w; //팝업창의 가로위치
		var y = screen.AvailHeight-h; //팝업창의 세로위치 (상단에 띄우려면 0)
		window.open("/cal?account_id="+temp, "_blank","scrollbar=no, resizeable=no, " +
							"top="+y+",left="+x+",width=912,height=984");
	});
	
	//템플릿 모달창 띄우기(손주형)
	$("#templateId").on("click", function(){
		var user_id = $("#userId").val();
		
		templateList();
		
		$("#templateModal").css("display", "block");
		$("#tableBody").html();
	})
	
	//템플릿 화면에서 테이블(tr)클릭시 이벤트(손주형)
	$(document).on("click", "tr", function(){
		var tmpId = $(this).children().find(".utemplate_id").val();
		var tmpAbb = $(this).find(".tempTdAbb").text();
		var tmpOri = $(this).find(".tempTdOriginal").text();
		
		$("tr").css("background-color", "white");
		$(this).css("background-color", "rgba(0, 0, 0, 0.075)");
		$("#inputTmpId").val(tmpId);
		$("#inputTmpUpdateAbb").val(tmpAbb);
		$("#inputTmpUpdateOriArea").val(tmpOri);
	})
	
	//템플릿 추가 버튼 클릭시 이벤트(손주형)
	$("#templateAdd").on("click", function(){
		$("#templateAddModal").css("display", "block");
		
	})
	
	//템플릿 추가 화면에서의 추가버튼 클릭시 이벤트(손주형)
	$("#tempAdd").on("click", function(){
		var utemplate_abb = $("#inputAbb").val();
		var utemplate_original = $("#inputOriArea").val();
		var user_id = $("#userId").val();
		
		$.ajax({
			method : "post",
			url : "/userTemplate/insertUserTemplate",
			data : $("#templateFrm").serialize(),
			success : function(data){
				if(data.msg == "존재하는 약어"){
					alert("이미 존재하는 약어 입니다.\n다른 약어를 입력해주세요.");
					return;
				}
				
				alert("템플릿 등록 성공");
				
				$("#inputAbb").val("");
				$("#inputOriArea").val("");
				
				templateList();
			},
			error : function(error){
				console.log("등록 실패");
			}
		})
		
		$("#templateAddModal").css("display", "none");
		
	})
	
	function templateList(){
		var user_id = $("#userId").val();
		
		$.ajax({
			method:"post",
			url : "/userTemplate/userTemplate",
//			contentType : "application/json",
			dataType : "json",
			data : "user_id_fk="+user_id,
			success : function(response){
				
				
				console.log(response.templateList);
				var list = response.templateList;
				
				var rowData = "";
				
				response.templateList.forEach(function (templateVO){
					rowData += "<tr class='table table-hover templateRow'>";
					rowData += "<td class='tempTdAbb'><input type='hidden' id='utemplate_id' class='utemplate_id' value='"+ templateVO.utemplate_id +"'>" + templateVO.utemplate_abb + "</td>";
					rowData += "<td class='tempTdOriginal'>" + templateVO.utemplate_original + "</td>";
					rowData += "</tr>";
				})
				
				
				$("#templateTbody").empty();
				$("#templateTbody").append(rowData);
			},
			error : function(error){
				console.log(error);
			}
		})
	}
	
	// 템플릿 취소버튼 클릭시 이벤트(손주형)
	$("#tempCancle").on("click", function(){
		$("#templateAddModal").css("display", "none");
		$("tr").css("background-color", "white");
		
		$("#inputTmpId").val("");
		$("#inputTmpUpdateAbb").val("");
		$("#inputTmpUpdateOriArea").val("");
	})
	$("#tempUpdateCancle").on("click", function(){
		$("#templateUpdateModal").css("display", "none");
		$("tr").css("background-color", "white");
		
		$("#inputTmpId").val("");
		$("#inputTmpUpdateAbb").val("");
		$("#inputTmpUpdateOriArea").val("");
	})
	
	
	//템플릿 수정버튼 클릭시 이벤트(손주형)-수정화면으로 전환
	$("#templateUpdate").on("click", function(){
		if($("#inputTmpId").val() == ""){
			alert("템플릿을 선택후 클릭해 주세요.");
			return;
		}
		
		$("#templateUpdateModal").css("display", "block");
		
		$("#tempId").val($("#inputTmpId").val());
		$("#inputUpdateAbb").val($("#inputTmpUpdateAbb").val());
		$("#inputUpdateOriArea").val($("#inputTmpUpdateOriArea").val());
	})
	
	//템플릿 수정화면에서 수정버튼 클릭 시 이벤트(손주형)
	$("#tempUpdate").on("click",function(){
		var tempId = $("#tempId").val();
		var abb = $("#inputUpdateAbb").val();
		var original = $("#inputUpdateOriArea").val();
		
		if(tempId == ""){
			alert("ID 에러");
		}
		if(abb == ""){
			alert("약어를 입력해주세요.");
			return;
		}
		if(original == ""){
			alert("쿼리 원문을 입력해주세요.");
			return;
		}
		
		$.ajax({
			method : "post",
			url : "/userTemplate/updateUserTemplate",
			data : $("#templateUpdateFrm").serialize(),
			success : function(data){
				if(data.msg == "존재하는 약어"){
					alert("이미 존재하는 약어 입니다.\n다른 약어를 입력해주세요.");
					return;
				}
				
				alert("템플릿 수정 성공");
				$("#templateUpdateModal").css("display", "none");
				
				templateList();
			},
			error : function(error){
				alert("템플릿 수정 실패");
			}
		})
		
		var user_id = $("#userId").val();
		
		templateList();
	})
	
	//템플릿 삭제 버튼 클릭 시 이벤트
	$("#templateDelete").on("click", function(){
		var user_id = $("#userId").val();
		var utemplate_id = $("#inputTmpId").val();
		
		if(utemplate_id <= 0){
			alert("삭제할 템플릿을 클릭해 주세요.");
			return;
		}
		
		var boolean = "";
		
		// 템플릿 삭제
		$.ajax({
			method : "post",
			url : "/userTemplate/deleteUserTemplate",
			data : "utemplate_id="+utemplate_id,
			success : function(data){
				alert("템플릿 삭제 성공");
				boolean = "성공";
				
				templateList();
			},
			error : function(error){
				alert("템플릿 삭제 실패");
				boolean = "실패";
			}
		})
	})
	
	//템플릿의 모달안의 모달창의 닫기버튼 클릭시 이벤트(손주형)
	$(".addClose").on("click", function(){
		$("#templateAddModal").css("display", "none");
		$("#templateUpdateModal").css("display", "none");
		$("tr").css("background-color", "white");
		
		$("#inputTmpId").val("");
		$("#inputTmpUpdateAbb").val("");
		$("#inputTmpUpdateOriArea").val("");
	})
	
	// 모달창 닫기
	$(".close").on("click", function() {
		$("#accountModal").css("display", "none");
		$("#accountDeleteModal").css("display", "none");
		$("#accountPwFindModal").css("display", "none");
		$("#accountPwUpdateModal").css("display", "none");
		$("#worksheetLoadModal").css("display", "none");
		$("#triggerPackageModal").css("display", "none");
		$("#readTriggerModal").css("display", "none");
		$("#fucntionPackageModal").css("display", "none");
		$("#readFunctionModal").css("display", "none");
		$("#procedurePackageModal").css("display", "none");
		$("#readProcedureModal").css("display", "none");

		$("#testDataModal").css("display", "none");

		//손주형
		$("#templateModal").css("display", "none");
		$("#templateUpdateModal").css("display", "none");
		$("tr").css("background-color", "white");
		$("#templateTbody").empty();
		
		$("#inputTmpId").val("");
		$("inputTmpUpdateAbb").val("");
		$("inputTmpUpdateOriArea").val("");
	});
	
	$(".addClose").on("click", function(){
		$("#templateAddModal").css("display", "none");
	})
	
	// DB계정 생성
	$("#addAccountBtn").on("click", function() {
		if($("#accountName").val().trim().length <= 0) {
			$("#accountName").focus();
			return;
		}
		
		if($("#accountPw").val().trim().length <= 0) {
			$("#accountPw").focus();
			return
		}
		
		if($("#chatRoomName").val().trim().length <= 0) {
			$("#chatRoomName").focus();
			return;
		}
		
		$("#accountAddfrm").submit();
	});
	
	// DB계정 삭제
	$("#accountDeleteBtn").on("click", function() {
		if($("#deletePw").val().trim().length <= 0) {
			$("#deletePw").focus();
			return;
		}
		$("#accountDeleteFrm").submit();
	});
	
	// DB계정 비밀번호 찾기
	$("#accountPwFindBtn").on("click", function() {
		if($("#user_id").val().trim().length <= 0) {
			$("#user_id").focus();
			return;
		}
		
		if($("#user_email").val().trim().length <= 0) {
			$("#user_email").focus();
			return;
		}
		$("#accountPwFindFrm").submit();
	});
	
	// DB계정 비밀번호 수정
	$("#accountPwUpdateBtn").on("click", function() {
		if($("#originalPw").val().trim().length <= 0) {
			$("#originalPw").focus();
			return;
		}
		
		if($("#updatePw").val().trim().length <= 0) {
			$("#updatePw").focus();
			return;
		}
		
		if($("#reUpdatePw").val().trim().length <= 0) {
			$("#reUpdatePw").focus();
			return;
		}
		$("#accountPwUpdateFrm").submit();
	});
	
	// 쿼리매니저
	$("#queryManager").on("click", function(){
		var dragText = editor.getSelectedText();
		if(dragText == "") {
			alert("쿼리매니저를 사용하고 싶은 쿼리를 드래그해주세요.");
			return;
		}
		$.ajax({
			 url : "/worksheet/queryManager"
			,data : "dragText=" + dragText
			,success : function(data) {
				console.log(data)
				var result = confirm("기존쿼리 " + data.dragText + "\n\n는 " + data.result + data.dt+" 으로 개선하실 수 있습니다. \n\n개선하시겠습니까?");
				 if(result) {
					for(var i = 0; i < 200; i++){
						editor.getSelection().moveCursorLineEnd();
						editor.getSelection().moveCursorDown();
					}
					editor.insert("\n");
					editor.insert("\n");
					editor.insert("--개선된 쿼리");
					editor.insert("\n");
					editor.insert(data.dt);
				 }
				
			}
		});
		
	})
	// 실행계획 버튼 클릭 이벤트
	$("#runPlan").on("click", function() {
		var dragText = editor.getSelectedText();
		
		if(dragText == "") {
			alert("실행계획을 보고싶은 쿼리를 드래그해주세요.");
			return;
		}
		
		var accountId = $("#radioId").val(); // BB_KKK123
		var underBarIdx = accountId.lastIndexOf("_");
		var accountFront = accountId.substring(0,underBarIdx); // BB
		var accountBack = accountId.substring(underBarIdx); // _KKK123
		var account_id = accountFront + accountBack.toLowerCase(); // BB_kkk123
		
		$.ajax({
			url : "/worksheet/planRun",
			method : "get",
			data : "dragText=" + dragText + "&account_id=" + account_id,
			success : function(data) {
				console.log(data);
				var result = "";
				if(data[0].indexOf("ORA") != -1) {
					result = "\n\n작업 실패\nERROR) " + data[0];
					changeScript();
					$("#scriptViewArea").append(result);
					$("#scriptViewArea").scrollTop($("#scriptViewArea")[0].scrollHeight);
				}else {
					for (var i = 0; i < data.length; i++) {
						var temp = data[i].replace(/---/gi, "──");
						result += temp + "\n";
					}
					changeScript();
					$("#scriptViewArea").text(result);
					$("#scriptViewArea").scrollTop($("#scriptViewArea")[0].scrollHeight);
				}
			}
		});
		
	});
	
	// 컨트롤 + 엔터 이벤트
	var ctrlDown = false;
	$(document).keydown(function(e) { 
		if(e.keyCode == 17) ctrlDown = true;
		if(e.keyCode == 13 && ctrlDown == true) {
			ctrlDown = false;
			// 구현 내용 작성
			var dragText = editor.getSelectedText();
			console.log(dragText);
			if(dragText.indexOf("CREATE") != -1 && dragText.indexOf("PROCEDURE") != -1) {
				sqlRun(dragText);
			}else if(dragText.indexOf("CREATE") != -1 && dragText.indexOf("FUNCTION") != -1) {
				sqlRun(dragText);
			}else if(dragText.indexOf("CREATE") != -1 && dragText.indexOf("TRIGGER") != -1) {
				sqlRun(dragText);
			}else if(dragText.indexOf(";") == dragText.lastIndexOf(";")) {
				sqlRun(dragText);
			}else {
				var dragTextArr = dragText.split(";");
				console.log(dragTextArr);
				for (var i = 0; i < dragTextArr.length; i++) {
					if(dragTextArr[i] == "") continue;
					sqlRun(dragTextArr[i]);
				}
			}
		}}).keyup(function(e) { 
				if (e.keyCode == 17) ctrlDown = false;
				if (e.keyCode == 13) ctrlDown = false;
			});
	
    // 라디오버튼 클릭 이벤트
    $(".radioClass").on("click", function() {
		var temp = $(this).attr("id");
		$("#radioId").val(temp);
		$.ajax({
			url : "/worksheet/accountChange",
			method : "get",
			success : function(data) {}
		});
	});
    
    var acco_id = $("#dbAccountView").children(0).children(0).children(0).children(0).children(0).attr("id");
    document.getElementById(acco_id).setAttribute('checked', 'checked');
    $("#radioId").val(acco_id);
    
});

var editor = ace.edit("editor");
editor.setTheme("ace/theme/twilight");
editor.session.setMode("ace/mode/sql");

// 결과창 보기
function changeResult() {
	$("#resultTable").css("display", "block");
	$("#scriptViewArea").css("display", "none");
	$("#resultViewSpan").css("color", "red");
	$("#scriptViewSpan").css("color", "black");
}

// 스크립트창 보기
function changeScript() {
	$("#scriptViewArea").css("display", "block");
	$("#resultTable").css("display", "none");
	$("#scriptViewSpan").css("color", "red");
	$("#resultViewSpan").css("color", "black");
}

function sqlRun(dragText) {
	if(dragText == "") { // 드래그를 하지 않고 ctrl + enter를 실행했을 경우
		var aceDocument = editor.getSession().getDocument(); // aceEditor의 document객체 구하기
		var cursorPosition = editor.getCursorPosition(); // editor의 현재커서 위치 구하기(row, column으로 구성)
		var cursorRow = cursorPosition.row; // editor의 커서 위치에서 row만 추출
		var lineArry = aceDocument.getAllLines(); // editor의 모든 라인 구하기(배열)
		
		var previousLines = new Array(); // 현재 커서위치 포함 이전 라인을 담을 배열
		var nextLines = new Array(); // 현재 커서위치 미포함 다음 라인을 담을 배열
		
		var right = "N"; // 처음 검사하는 라인에 ';'이 있었는지 없었는지 알려주는 구분자('N'은 없었음, 'Y'는 있었음)
		
		for (var i = cursorRow; i >= 0; i--) { // 이전 라인을 담는 작업
			// 처음 검사하는 라인에 ';'이 있을 경우 구분자를 'Y'로 변경
			if(aceDocument.getLine(i).indexOf(";") != -1 && i == cursorRow) right = "Y";
			// 처음 검사하는 라인이 아니고 ';'이 존재하면 break
			if(aceDocument.getLine(i).indexOf(";") != -1 && i != cursorRow) break;
			previousLines.unshift(aceDocument.getLine(i)); // 이전 라인을 배열에 담음
		}
		
		if(right != "Y") { // 처음 검사하는 라인에 ';'가 없었던 경우(다음 라인을 담는 작업이 필요해짐)
			for (var i = cursorRow+1; i < lineArry.length; i++) { // 다음 라인을 담는 작업
				nextLines.push(aceDocument.getLine(i)); // 다음 라인을 배열에 담음
				if(aceDocument.getLine(i).indexOf(";") != -1) break; // 배열에 담은 후 ';'이 있으면 break
			}
			
			for (var i = 0; i < previousLines.length; i++) // dragText에 이전 라인 옮겨담는 작업
				dragText += previousLines[i];
			
			for (var i = 0; i < nextLines.length; i++) // dragText에 다음 라인 옮겨담는 작업
				dragText += nextLines[i];
			
		}else { // 처음 검사하는 라인에 ';'가 있었던 경우(이전 라인만 담는 작업 필요)
			for (var i = 0; i < previousLines.length; i++) // dragText에 이전 라인 옮겨담는 작업
				dragText += previousLines[i];
		}
	}
	
	var accountId = $("#radioId").val(); // BB_KKK123
	var underBarIdx = accountId.lastIndexOf("_");
	var accountFront = accountId.substring(0,underBarIdx); // BB
	var accountBack = accountId.substring(underBarIdx); // _KKK123
	var account_id = accountFront + accountBack.toLowerCase(); // BB_kkk123
	
	if(dragText.indexOf("select") != -1 || dragText.indexOf("SELECT") != -1) {
		
		if($("#resultTable")[0].childNodes.length > 0) {
			if(confirm("결과창을 새창에 띄우시겠습니까?")) {
				window.open("/sqlEditor/resultPopup?dragText=" + dragText + "&account_id=" + account_id, 
						"_blank","scrollbar=no, resizeable=no, top=100,left=1015,width=600,height=651");
				return;
			}
		}
		
		$.ajax({
			url : "/worksheet/selectRun",
			dataType : "json",
			method : "get",
			async : false,
			data : "dragText=" + dragText + "&account_id=" + account_id,
			success : function(data) {
				
				console.log(data);
				console.log(data.resultList);
				
				if(data.errorMsg == null) {
					var dataArray = new Array(); // 데이터 담을 배열
					var colNameArray = new Array(); // 컬럼명 담을 배열
					var colModelArray = new Array();
					var reg = /'([\w\d\s]*)'/; // 싱글 쿼테이션 제거 정규식
					
					var colNames = data.resultList[0]; // 컬럼명들이 있는 배열
					console.log(colNames);
					
					for (var i = 0; i < colNames.length; i++) { // 싱글 쿼테이션 제거한 컬럼명이 들어있는 배열 구성
						if(colNames[i].match(reg) == null)
							colNameArray.push(colNames[i]);
						else
							colNameArray.push(colNames[i].match(reg)[1]);
					}
					
					$.each(colNameArray, function(idx, obj) {
						var t = {name : obj, label : obj, align : 'center'};
						colModelArray.push(t);
					});
					
					$.each(data.resultList, function(idx, obj){
						if(idx >= 1) {
							var i = 0;
							var t = new Object();
							for (var i = 0; i < colNameArray.length; i++) {
								t[colNameArray[i]] = obj[i];
							}
							dataArray.push(t);
						}
					});
					
					console.log(dataArray);
					changeResult();
					$("#resultTable").jqGrid('GridUnload');
					
					$("#resultTable").jqGrid({
						datatype : 'local',
						styleUI: 'Foundation',
						data : dataArray,
						colModel :colModelArray,
						height : '250px',
						autowidth: true
					});
				}else {
					var error = "\n\n작업 실패\n";
					error += "ERROR) " + data.errorMsg;
					changeScript();
					$("#scriptViewArea").append(error);
					$("#scriptViewArea").scrollTop($("#scriptViewArea")[0].scrollHeight);
				}
			}
		});
	}else if(dragText.indexOf("create") != -1 || dragText.indexOf("CREATE") != -1 || 
			 dragText.indexOf("alter") != -1 || dragText.indexOf("ALTER") != -1 || 
			 dragText.indexOf("drop") != -1 || dragText.indexOf("DROP") != -1 || 
			 dragText.indexOf("rename") != -1 || dragText.indexOf("RENAME") != -1) {
		$.ajax({
			url : "/worksheet/ddlRun",
			dataType : "json",
			method : "get",
			async : false,
			data : "dragText=" + dragText + "&account_id=" + account_id,
			success : function(data) {
				if(data.resultMap.result == "Y") {
					var view = "\n\n" + data.resultMap.objectType + " " + data.resultMap.resultStr + " 성공";
					changeScript();
					$("#scriptViewArea").append(view);
					$("#scriptViewArea").scrollTop($("#scriptViewArea")[0].scrollHeight);
				}else {
					var error = "\n\n작업 실패\n";
					error += "ERROR) " + data.resultMap.result;
					changeScript();
					$("#scriptViewArea").append(error);
					$("#scriptViewArea").scrollTop($("#scriptViewArea")[0].scrollHeight);
				}
			}
		});
	}else if(dragText.indexOf("commit") != -1 || dragText.indexOf("COMMIT") != -1) {
		$.ajax({
			url : "/worksheet/commitRun",
			dataType : "json",
			method : "get",
			async : false,
			data : "dragText=" + dragText + "&account_id=" + account_id,
			success : function(data) {
				if(data.resultCnt == 1) {
					changeScript();
					$("#scriptViewArea").append("\n\nCOMMIT 완료");
					$("#scriptViewArea").scrollTop($("#scriptViewArea")[0].scrollHeight);
				}else {
					changeScript();
					$("#scriptViewArea").append("\n\nCOMMIT 실패");
					$("#scriptViewArea").scrollTop($("#scriptViewArea")[0].scrollHeight);
				}
			}
		});
	}else if(dragText.indexOf("rollback") != -1 || dragText.indexOf("ROLLBACK") != -1){
		$.ajax({
			url : "/worksheet/rollbackRun",
			dataType : "json",
			method : "get",
			async : false,
			data : "dragText=" + dragText + "&account_id=" + account_id,
			success : function(data) {
				if(data.resultCnt == 1) {
					changeScript();
					$("#scriptViewArea").append("\n\nROLLBACK 완료");
					$("#scriptViewArea").scrollTop($("#scriptViewArea")[0].scrollHeight);
				}
				else {
					changeScript();
					$("#scriptViewArea").append("\n\nROLLBACK 실패");
					$("#scriptViewArea").scrollTop($("#scriptViewArea")[0].scrollHeight);
				}
			}
		});
	}else {
		$.ajax({
			url : "/worksheet/anotherRun",
			dataType : "json",
			method : "get",
			async : false,
			data : "dragText=" + dragText + "&account_id=" + account_id,
			success : function(data) {
				if(data.resultMap.result == "Y") {
					var view = "\n\n" + data.resultMap.resultCnt + "건이 " + data.resultMap.resultStr + "되었습니다.";
					changeScript();
					$("#scriptViewArea").append(view);
					$("#scriptViewArea").scrollTop($("#scriptViewArea")[0].scrollHeight);
				}else {
					var error = "\n\n작업 실패\n";
					error += "ERROR) " + data.resultMap.result;
					changeScript();
					$("#scriptViewArea").append(error);
					$("#scriptViewArea").scrollTop($("#scriptViewArea")[0].scrollHeight);
				}
			}
		});
	}
}

function saveToFile_Chrome(fileName, content) {
    var blob = new Blob([content], { type: 'text/plain' });
 
    objURL = window.URL.createObjectURL(blob);
            
    // 이전에 생성된 메모리 해제
    if (window.__Xr_objURL_forCreatingFile__) {
        window.URL.revokeObjectURL(window.__Xr_objURL_forCreatingFile__);
    }
    window.__Xr_objURL_forCreatingFile__ = objURL;
 
    var a = document.createElement('a');
 
    a.download = fileName;
    a.href = objURL;
    a.click();
}

function processFile(file) {
	var reader = new FileReader();
	
	reader.onload = function() {
		editor.setValue(reader.result);
	};
	
	reader.readAsText(file, "UTF-8");
}
