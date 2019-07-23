$(document).ready(function() {
	
	if($("#msg").val().trim().length > 0) {
		alert($("#msg").val());
	}
	
	var modal = $("#accountModal");
	
	$("#accountImg").on("click", function() {
		modal.css("display", "block");
	});
	
	$(".close").on("click", function() {
		modal.css("display", "none");
	});
	
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
	
});

