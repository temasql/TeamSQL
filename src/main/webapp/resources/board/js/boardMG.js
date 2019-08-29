$(document).ready(
	function() {
		if ($("#updateUse_yn").val() == "") {
			$("#updateUse_yn").val("Y");
	}

	$(".updateUse_yn").change(function() {
		var use_yn = $(this).val();
		$("#updateUse_yn").val($(this).val());
	});

	$(".modiBtn").on("click", function() {
		var updateUser_yn = $(this).parents("td").prevAll(".modiSel").find(".updateUse_yn").val();
		$("#updateUse_yn").val(updateUser_yn);
		var boardId = $(this).parents("td").prevAll(".boardId").html();
		$("#boardId").val(boardId);
		$("#frm").submit();
	})
})