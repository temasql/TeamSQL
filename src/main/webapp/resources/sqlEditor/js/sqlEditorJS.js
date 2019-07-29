$(document).ready(function() {
	
	// alert 띄우기
	if($("#msg").val().trim().length > 0) {
		alert($("#msg").val());
	}
	
	$("#refresh").on("click", function() {
		$.ajax({
			url : "/sqlEditor/sqlEditorMain",
			method : "get",
			success : function(data) {
				console.log(data);
				alert("성공");
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
			if(dragText == "") {
				alert("실행시킬 쿼리문을 드래그해주세요.");
				return;
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