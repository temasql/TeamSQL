$(document).ready(function() {

	// 시퀀스 생성 모달창 띄우기
	$("#createsequenceSpan").on("click", function() {
		$("#craeteSequenceModal").css("display", "block");
	});
	
	// 
	
	// 시퀀스 생성
	$(document).on("click","#createSeqBtn",function(){
		var seqName = $("#sequenceName").val();
		var seqReg = /^[a-zA-Z][a-zA-Z0-9#$_]{2,8}$/;
	})

	// 모달창 닫기
	$(".close").on("click", function() {
		$("#craeteSequenceModal").css("display", "none");
	});
});
