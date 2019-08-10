$(document).ready(function() {
	// 사용자 tr 태그 이벤트 등록
	$(".postTr").on("click", function() {

		var postId = $(this).find(".postId").text();
		$("#postId").val(postId);
		var post_yn = $(this).find(".post_yns").val()
		$("#post_yn").val(post_yn);
		//#frm을 이용하여 submit();
		if ($("#post_yn").val() == "Y") {
			$("#frm").submit();
		}
	})
})