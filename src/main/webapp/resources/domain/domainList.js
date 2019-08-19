$(function() {
	domainPagingListAjaxHtml(1, 10);


	$(document).on("click", ".modiBtn", function(){
					
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
			
			
	$(document).on("click", ".delBtn", function() {
					
		var domainId = $(this).parents("td").prevAll(".domainId").children().val();
					
		$("#deleteId").val(domainId);
				
		$("#delFrm").submit();
	})
	
})


function domainPagingListAjaxHtml(page, pageSize) {
	var domainId = $("#domainId").val()
	
	$.ajax({
		  url : "/commonDomain/domainList"
		, method : "post"
		, data : "page=" + page + "&pageSize=" + pageSize
		, success : function(data) {
			var html = data.split("SEPERATORSEPERATOR");
			
			$("#domainListTbody").html(html[0]);
			$(".pagination").html(html[1]);
		}
	})
}
	
