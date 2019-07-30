$(document).ready(function() {
	
	//alert 띄우기
	if($("#msg").val().trim().length > 0) {
		alert($("#msg").val());
	}
	
	// 워크시트 다운로드 버튼 이벤트
	$("#worksheetSave").on("click", function() {
		var fileName = prompt("저장하실 파일명을 입력해주세요.");
		saveToFile_Chrome(fileName, editor.getValue());
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
		$.ajax({
			url : "/sqlEditor/refresh",
			method : "get",
			success : function(data) {
				console.log(data);
			}
		});
	});
	
	// 결과하면 클리어
	$("#clearSpan").on("click", function() {
		if($("#resultViewArea").css("display") == "block") {
			$("#resultViewArea").text("");
		}else {
			$("#scriptViewArea").text("");
		}
	});
	
	// 결과창으로 바꾸기
	$("#resultViewSpan").on("click", function() {
		$("#resultViewArea").css("display", "block");
		$("#scriptViewArea").css("display", "none");
		$("#resultViewSpan").css("color", "red");
		$("#scriptViewSpan").css("color", "black");
	});
	
	// 스크립트 창으로 바꾸기
	$("#scriptViewSpan").on("click", function() {
		$("#scriptViewArea").css("display", "block");
		$("#resultViewArea").css("display", "none");
		$("#scriptViewSpan").css("color", "red");
		$("#resultViewSpan").css("color", "black");
	});
	
	// DB계정 생성 모달창 띄우기
	$("#accountImg").on("click", function() {
		$("#accountModal").css("display", "block");
	});
	
	// DB계정 삭제 모달창 띄우기
	$("#accountDeleteSpan").on("click", function() {
		$("#accountDeleteModal").css("display", "block");
	});
	
	// DB계정 비밀번호 찾기 모달창 띄우기
	$("#accountPwFindSpan").on("click", function() {
		$("#accountPwFindModal").css("display", "block");
	});
	
	// DB계정 비밀번호 수정 모달창 띄우기
	$("#accountPwUpdateSpan").on("click", function() {
		$("#accountPwUpdateModal").css("display", "block");
	});
	
	//일정관리 팝업창 띄우기
	$("#calendarPopup").on("click", function(){
		var temp = $("#acc_id").val();
		var w = 912; //팝업창의 가로크기(임의)
		var h = 984; //팝업창의 세로크기(임의)
		var x = screen.AvailWidth-w; //팝업창의 가로위치
		var y = screen.AvailHeight-h; //팝업창의 세로위치 (상단에 띄우려면 0)
		window.open("/cal?account_id="+temp, "_blank","scrollbar=no, resizeable=no, " +
							"top="+y+",left="+x+",width=912,height=984");
	})
	
	// 모달창 닫기
	$(".close").on("click", function() {
		$("#accountModal").css("display", "none");
		$("#accountDeleteModal").css("display", "none");
		$("#accountPwFindModal").css("display", "none");
		$("#accountPwUpdateModal").css("display", "none");
		$("#worksheetLoadModal").css("display", "none");
	});
	
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
	
	// 실행계획 버튼 클릭 이벤트
	$("#runPlan").on("click", function() {
		var dragText = editor.getSelectedText();
		
		if(dragText == "") {
			alert("실행계획을 보고싶은 쿼리를 드래그해주세요.");
			return;
		}
		
		var accountId = $("#radioId").val();
		var user_id = $("#userId").val();
		var indexCnt = accountId.indexOf(user_id.toUpperCase());
		var account_temp = accountId.substring(0, indexCnt);
		var account_id = account_temp + user_id;
		
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
					changeResult();
					$("#resultViewArea").text(result);
					$("#resultViewArea").scrollTop($("#resultViewArea")[0].scrollHeight);
				}
			}
		});
		
	});
	
	// 컨트롤 + 엔터 이벤트
	var ctrlDown = false;
	$(document).keydown(function(e) { 
		if(e.keyCode == 17) ctrlDown = true;
		if(e.keyCode == 13 && ctrlDown == true) {
			ctrlDown == false;
			// 구현 내용 작성
			var dragText = editor.getSelectedText();
			console.log(dragText);
			
			if(dragText.indexOf(";") == dragText.lastIndexOf(";")) {
				sqlRun(dragText);
			} else {
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
	$("#resultViewArea").css("display", "block");
	$("#scriptViewArea").css("display", "none");
	$("#resultViewSpan").css("color", "red");
	$("#scriptViewSpan").css("color", "black");
}

// 스크립트창 보기
function changeScript() {
	$("#scriptViewArea").css("display", "block");
	$("#resultViewArea").css("display", "none");
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
	
	var accountId = $("#radioId").val();
	var user_id = $("#userId").val();
	var indexCnt = accountId.indexOf(user_id.toUpperCase());
	var account_temp = accountId.substring(0, indexCnt);
	var account_id = account_temp + user_id;
	
	if(dragText.indexOf("select") != -1 || dragText.indexOf("SELECT") != -1) {
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
					var list = data.resultList;
					var view = "";
					for (var i = 0; i < list.length; i++) {
						for (var j = 0; j < list[0].length; j++) {
							view += list[i][j] + "|";
						}
						view += "\n";
					}
					changeResult();
					$("#resultViewArea").text(view);
					$("#resultViewArea").scrollTop($("#resultViewArea")[0].scrollHeight);
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
					var view = data.resultMap.objectType + " " + data.resultMap.resultStr + " 성공";
					changeResult();
					$("#resultViewArea").text(view);
					$("#resultViewArea").scrollTop($("#resultViewArea")[0].scrollHeight);
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
