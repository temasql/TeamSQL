$(document).ready(function() {

	// 시퀀스 생성 모달창 띄우기
	$("#createsequenceSpan").on("click", function() {
		$("#craeteSequenceModal").css("display", "block");
	});

	// 모달창 닫기
	$(".close").on("click", function() {
		$("#craeteSequenceModal").css("display", "none");
	});

});
