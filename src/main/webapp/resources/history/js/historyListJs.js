$(document).ready(function(){
	historyPagingListAjaxHtml(1,10);
	
	$("#dbChangedListBody").on("click", ".clickEvent", function() {
		
		// 계정명
		var object_owner = $(this).find(".original_owners").val();
		$("#object_owner").val(object_owner);
		$("#frm").submit();
	});
});
function historyPagingListAjaxHtml(page, pageSize){
	$.ajax({
			url    : "/history/historyList"
			,method : "post"
			,data   : "page=" + page + "&pageSize=" + pageSize
			,success : function(data){
					// html
				var html = data.split("SEPERATORSEPERATOR");
				$("#dbChangedListBody").html(html[0]);
				$(".pagination").html(html[1]);
				}
	});
}
