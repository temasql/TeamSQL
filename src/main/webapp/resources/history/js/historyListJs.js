
$(document).ready(function(){
	userPagingListAjaxHtml(1,10);
	
	$("#dbChangedListBody").on("click", ".table-active", function() {
		console.log("table-active click");
		//userId를 획득하는 방법
		//$(this).find(".userId").text();
		//$(this).data("userid"); ==> //밑에 <tr class="userTr" data-userid="${user.userId }" data-name="${user.name }">
		
		//사용자 아이드를 #userId 값으로 설정해주고
		var object_owner = $(this).find(".object_owner").text();
		$("#object_owner").val(object_owner);

		//#frm응 이용하여 submit();
		$("#frm").submit();
	});
});
function userPagingListAjaxHtml(page, pageSize){
	$.ajax({
			url    : "/history/historyList"
			,method : "post"
			,data   : "page=" + page + "&pageSize=" + pageSize + "&user_id=" + $("#user_id").val()
			,success : function(data){
					// html
				var html = data.split("SEPERATORSEPERATOR");
				$("#dbChangedListBody").html(html[0]);
				$(".pagination").html(html[1]);
				}
	});
}
