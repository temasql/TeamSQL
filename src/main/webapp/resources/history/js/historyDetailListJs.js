$(document).ready(function(){

});
function userPagingListAjaxHtml(page, pageSize){
	$.ajax({
			url    : "/history/historyList"
			,method : "post"
			,data   : "page=" + page + "&pageSize=" + pageSize + "&user_id=" + $("#user_id").val()
			,success : function(data){
				// html
				var html = data.split("SEPERATORSEPERATOR");
				$("#dbChangedDetailListBody").html(html[0]);
				$(".pagination").html(html[1]);
				}
	});
}
