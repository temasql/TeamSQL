$(function(){
	postPagingListAjaxHtml(1, 10);
	
	$(document).on("click", ".postTr", function(){
		var postId = $(this).find(".postId").text();
		$("#postId").val(postId);
		
		var post_yn = $(this).find(".post_yns").val();
		$("#post_yn").val(post_yn);
		
		if ($("#post_yn").val() == "Y") {
			$("#frm").submit();
		}
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