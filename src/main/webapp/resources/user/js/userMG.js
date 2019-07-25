$(function (){
	$(document).on("change", ".checkbox", function (){
		if($(this).is(":checked")){
            $("#deleteForm").append("<input type='hidden' class='deleteChecked' name='deleteCheck' value='"+ $(this).val()+"'/>")
        }else{
            $(this).removeAttr("name")
            $("#deleteForm :last-child").remove();
        }
	})
	
	$("#deleteUser").on("click", function (){
		if($(".deleteChecked").length == 0){
			alert("탈퇴 시킬 회원을 선택 해 주세요")
		}else{
			$("#deleteForm").submit();
		}
	})
	userPagingListAjaxHtml(1, 10);
	
	$("#btnSearch").on("click", function(){
		search()
	})
	
})
function userPagingListAjaxHtml(page, pageSize){
	$.ajax({
				url    : "/user/userManager"
				,method : "post"
				,data   : "page=" + page + "&pageSize=" + pageSize + "&searchfor=" + $("#searchfor").val()
				,success : function(data){
					// html
					var html = data.split("SEPERATORSEPERATOR");
					$("#userListTbody").html(html[0]);
					$(".pagination").html(html[1]);
				}
	});
}
function search(){
	$.ajax({
		url    : "/user/userManager"
			,method : "post"
				,data   : "page=1&pageSize=10&searchfor=" +$("#searchfor").val()
				,success : function(data){
					// html
					var html = data.split("SEPERATORSEPERATOR");
					$("#userListTbody").html(html[0]);
					$(".pagination").html(html[1]);
				}
	});
}
