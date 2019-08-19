$(function() {
	templatePagingListAjaxHtml(1, 10);


	$(document).on("click", ".modiBtn", function(){
					
		var templateId = $(this).parents("td").prevAll(".templateId").children().val();
		var templateAbb = $(this).parents("td").prevAll(".templateAbb").children().val();
		var templateOriginal = $(this).parents("td").prevAll(".templateOriginal").children().val();
					
		$("#updateId").val(templateId);
		$("#updateAbb").val(templateAbb);
		$("#updateOriginal").val(templateOriginal);
					
		$("#frm").submit();
	})
			
			
	$(document).on("click", ".delBtn", function() {
					
		var templateId = $(this).parents("td").prevAll(".templateId").children().val();
					
		$("#deleteId").val(templateId);
				
		$("#delFrm").submit();
	})
	
})


function templatePagingListAjaxHtml(page, pageSize) {
	var templateId = $("#templateId").val()
	
	$.ajax({
		  url : "/commonTemplate/templateList"
		, method : "post"
		, data : "page=" + page + "&pageSize=" + pageSize
		, success : function(data) {
			var html = data.split("SEPERATORSEPERATOR");
			
			$("#templateListTbody").html(html[0]);
			$(".pagination").html(html[1]);
		}
	})
}
	
