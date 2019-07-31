$(document).ready(function(){
	historyDetailPagingListAjaxHtml(1,10);
	alert($("#object_owner").val());
});
function historyDetailPagingListAjaxHtml(page, pageSize){
	$.ajax({
			url    : "/history/historyDetail"
			,method : "post"
			,data   : "page=" + page + "&pageSize=" + pageSize + "&object_owner=" + $("#object_owner").val()
			,success : function(data){
				// html
				var html = data.split("SEPERATORSEPERATOR");
				$("#dbChangedDetailListBody").html(html[0]);
				$(".pagination").html(html[1]);
				}
	});
}
