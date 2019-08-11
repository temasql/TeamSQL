$(function(){
	postPagingListAjaxHtml(1, 10);
	
	$(document).on("click", ".postTr", function(){
		alert()
	})
})

function postPagingListAjaxHtml(page, pageSize) {
	var board_id = $("#board_id").val()
	$.ajax({
		  url : "/post/boardList"
		, method : "post"
		, data : "page=" + page + "&pageSize=" + pageSize + "&board_id="  + board_id 
		, success : function(data) {
			console.log(data)
			var html = data.split("SEPERATORSEPERATOR");
			console.log(html[0])
			$("#boardListTbody").html(html[0]);
			$(".pagination").html(html[1]);
		}
	});
}