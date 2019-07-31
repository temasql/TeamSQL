$(document).ready(function(){
	
	$("#changedMainBody").on("click", ".table-active.clickEvent", function() {
		//사용자 아이드를 #userId 값으로 설정해주고
		var object_owner = $(this).find(".original_owners").val();
		$("#object_owner").val(object_owner);
		$("#frm").submit();
	});
});