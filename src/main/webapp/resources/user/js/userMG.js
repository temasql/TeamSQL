$(function (){
	$(document).on("change", ".checkbox", function (){
		if($(this).is(":checked")){
            $("#deleteForm").append("<input type='hidden' class='deleteChecked' name='deleteCheck' value='"+ $(this).val()+"'/>")
        }else{
            $(this).removeAttr("name")
            $("#deleteForm :last-child").remove();
        }
	})
	$(document).on("change", "#pageSizeSelect", function (){
		var pageSize = $(this).val()
		userPagingListAjaxHtml(1,pageSize);
	});
	
	$("#deleteUser").on("click", function (){
		if($(".deleteChecked").length == 0){
			alert("탈퇴 시킬 회원을 선택 해 주세요")
		}else{
			var tmp = "";
			$.each($(".deleteChecked"), function(idx, dt){
				tmp += dt.value + " ";
			})
			var result = confirm("회원 " + tmp + "을 탈퇴 시키시겠습니까?");
			if(result){
				alert("회원 " + tmp + "을 탈퇴 했습니다.")
				$("#deleteForm").submit();
			}
		}
	})
	userPagingListAjaxHtml(1, 10);
	
	$("#btnSearch").on("click", function(){
		search()
	})
	$('#searchfor').keyup(function(e) {
		search()
	})
	$('#searchfor').keydown(function(e) {
        if (e.keyCode == 13) {
        	search();
        }
    });
	
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
