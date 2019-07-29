
$(document).ready(function(){
	historyPagingListAjaxHtml(1,10);
	
	$("#dbChangedListBody").on("click", ".table-active.clickEvent", function() {
		
		//사용자 아이드를 #userId 값으로 설정해주고
		var object_owner = $(this).find(".object_owners").text();
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
