<%--------------------------------------------------------------------------------
	* 화면명 : Smart Editor 2.8 에디터 연동 페이지
	* 파일명 : /page/test/index.jsp
--------------------------------------------------------------------------------%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Smart Editor</title>

<!-- Favicon -->
<link rel="shortcut icon" href="favicon.ico" />

<!-- jQuery -->
<!-- <script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/jquery-ui.min.js"></script>-->

<!-- <script type="text/javascript" src="/js/jquery/jquery-3.2.1.js"></script> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>


<script src="${cp }/SE2/js/HuskyEZCreator.js"></script>
<script type="text/javascript">
var oEditors = []; // 개발되어 있는 소스에 맞추느라, 전역변수로 사용하였지만, 지역변수로 사용해도 전혀 무관 함.

$(document).ready(function() {
	// Editor Setting
	nhn.husky.EZCreator.createInIFrame({
		oAppRef : oEditors, // 전역변수 명과 동일해야 함.
		elPlaceHolder : "smarteditor", // 에디터가 그려질 textarea ID 값과 동일 해야 함.
		sSkinURI : "${cp }/SE2/SmartEditor2Skin.html", // Editor HTML
		fCreator : "createSEditor2", // SE2BasicCreator.js 메소드명이니 변경 금지 X
		htParams : {
			// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseToolbar : true,
			// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseVerticalResizer : true,
			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
			bUseModeChanger : true, 
		}
	});

	// 전송버튼 클릭이벤트
	$("#savebutton").click(function(){
		if(confirm("등록하시겠습니까?")) {
			// id가 smarteditor인 textarea에 에디터에서 대입
			oEditors.getById["smarteditor"].exec("UPDATE_CONTENTS_FIELD", []);

			// 이부분에 에디터 validation 검증
			if(validation()) {
				$("#frm2").submit();
			}
		}
	})
	
	// 사용자 등록 버튼 클릭 이벤트 핸들러
		$("#userRegBtn").on("click", function() {
			// 유효성 체크
			// 여기까지 도달하면 유효성 검사 완료(submit)
			$("#frm").submit();
		})

	var count = 1;
	$("#plusFile").on("click", function() {
		if(count <=5 ){
// 			$("#plus").after('<br><input type="file" class="custom-file-input" name="files" id="inputGroupFile"' + count + '">');
// 			$("#plus").after('<label class="custom-file-label" for="inputGroupFile"' + count + '">Choose file</label>')
			$("#plus").after('<br><input type="file"  name="files">');
			count += 1;
			
		}else{
			alert("파일 업로드는 최대 5개까지만 가능합니다.");
		}
	});
});


// 필수값 Check
function validation(){
	var contents = $.trim(oEditors[0].getContents());
	var title = $("#post_title").val();
	
	if(contents === '<p>&nbsp;</p>' || contents === ''){ // 기본적으로 아무것도 입력하지 않아도 <p>&nbsp;</p> 값이 입력되어 있음. 
		alert("내용을 입력하세요.");
		oEditors.getById['smarteditor'].exec('FOCUS');
		return false;
	}
	
	if(title == "") {
		alert("제목을 입력하세요.");
		return false;
	}

	return true;
}


</script>

<style>
#plusFile{
	margin-left : 100px;
}
#savebutton{
	margin-left : 20px;
}
</style>
</head>
<body>

<form action="${cp }/post/postForm" class="form-horizontal" method="post" enctype="multipart/form-data" id="frm2">
	<div class="form-group">
		<div class="col-sm-11">
			<input type="text" class="form-control" id="post_title" name="post_title" placeholder="제목">
		</div>
		<div class="col-sm-2">
		</div>
	</div>


	<textarea name="smarteditor" id="smarteditor" rows="10" cols="100" style="width:766px; height:412px;"></textarea> 
	<input type="hidden" id="boardId" name="board_id" value="${board_id}"/>
	<input type="hidden" id="userId" name="user_id" value="${USER_INFO.user_id }"/>
	<input type="hidden" id="postId" name="post_id" value="${post_id}"/>
<br><br>
<div class="form-group">
	<div id="plus" class="col-sm-offset-2 col-sm-11">

	 	<button type="button" id="plusFile" class="btn" style="background: black; color: white;">파일 업로드</button>
		<input type="button" class="btn" style="background: black; color: white;" id="savebutton" value="등록" />
	</div>
</div>
</form>
</body>
</html>