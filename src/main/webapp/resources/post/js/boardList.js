$(function(){
	postPagingListAjaxHtml(1, 10);
	
	
	$(document).on("click", ".postTr", function(){
		var postId = $(this).find(".postId").text();
		$("#postId").val(postId);
		
		var post_yn = $(this).prev().val()
		if (post_yn  == "Y") {
			$("#frm").submit();
		}
	})
	
	$("#btnSearch").on("click", function(){
		postPagingListAjaxHtml(1,10);
	})
})

function postPagingListAjaxHtml(page, pageSize) {
	var board_id = $("#board_id").val()
	var search = $("#searchfor").val()
	var select = $("#select").val()
	$.ajax({
		  url : "/post/boardList"
		, method : "post"
		, data : "page=" + page + "&pageSize=" + pageSize + "&board_id="  + board_id + "&search=" + search + "&selectBox=" + select 
		, success : function(data) {
			console.log(data)
			var html = data.split("SEPERATORSEPERATOR");
			console.log(html[0])
			$("#boardListTbody").html(html[0]);
			$(".pagination").html(html[1]);
		}
	});
}