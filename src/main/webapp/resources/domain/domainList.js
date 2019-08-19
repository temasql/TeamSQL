$(document).ready(function(){
	$(".modiBtn").on("click", function() {
			
		var domainId = $(this).parents("td").prevAll(".domainId").children().val();
		var domainName = $(this).parents("td").prevAll(".domainName").children().val();
		var domainType = $(this).parents("td").prevAll(".domainType").children().val();
			
		$("#updateId").val(domainId);
		$("#updateName").val(domainName);
		$("#updateType").val(domainType);
//		console.log($("#domainId").val())
//		console.log($("#domainName").val())
//		console.log($("#domainType").val())
//		console.log($("#frm").serialize())
			
		$("#frm").submit();
	})
	
	
		$(".delBtn").on("click", function() {
			
		var domainId = $(this).parents("td").prevAll(".domainId").children().val();
			
		$("#deleteId").val(domainId);
			
		$("#delFrm").submit();
	})

})