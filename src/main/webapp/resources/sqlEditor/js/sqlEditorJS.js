$(document).ready(function() {
	
	// alert 띄우기
	if($("#msg").val().trim().length > 0) {
		alert($("#msg").val());
	}
	
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
		window.open("/cal?account_id"+temp, "_blank","scrollbar=no, resizeable=no, " +
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
	
	// textarea 드래그 영역 text 가져오기
    function selectText() {
        var selectionText = "";
        if (document.getSelection) {
            selectionText = document.getSelection().toString();
        } else if (document.selection) {
            selectionText = document.selection.createRange().text;
        }
        return selectionText;
    }
	
	// 실행계획 버튼 클릭 이벤트
	$("#runPlan").on("click", function() {
		var dragText = selectText();
		
		if(dragText == "") {
			alert("실행계획을 보고싶은 쿼리를 드래그해주세요.");
			return;
		}
		
		$.ajax({
			url : "/sqlEditor/runPlan",
			method : "get",
			data : "dragText=" + dragText,
			success : function(data) {
				console.log(data);
				var result = "";
				for (var i = 0; i < data.length; i++) {
					var temp = data[i].replace(/---/gi, "──");
					result += temp + "\n";
				}
				$("#resultView").text(result);
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
			var dragText = selectText();
			if(dragText == "") {
				alert("실행시킬 쿼리문을 드래그해주세요.");
				return;
			}
			
			$.ajax({
				url : "/worksheet/run",
				dataType : "json",
				method : "get",
				data : "dragText=" + dragText,
				success : function(data) {
					var view = "";
					console.log(data);
					for (var i = 0; i < data.iterator.length; i++) {
						view += data.iterator[i] + " | ";
					}
					view += "\n";
					
					for (var i = 0; i < data.resultList.length; i++) {
						for (var j = 0; j < data.iterator.length; j++) {
							var temp = data.iterator[j]; // "USER_DT"
							view += data.resultList[i][temp] + " | ";
						}
						view += "\n";
					}
					
					$("#resultView").text(view);
				}
			});
			
		}}).keyup(function(e) { 
				if (e.keyCode == 17) ctrlDown = false;
				if (e.keyCode == 13) ctrlDown = false;
			});
	
});

