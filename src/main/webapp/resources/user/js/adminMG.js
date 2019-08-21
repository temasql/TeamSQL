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
		adminPagingListAjaxHtml(1,pageSize);
	});
	if($("#msg").val() != ""){
		alert($("#msg").val())
	}
	
	$("#deleteAdmin").on("click", function (){
		if($(".deleteChecked").length == 0){
			alert("탈퇴 시킬 회원을 선택 해 주세요")
		}else{
			var tmp = "";
			$.each($(".deleteChecked"), function(idx, dt){
				tmp += dt.value + " ";
			})
			var result = confirm("관리자 " + tmp + "을 삭제 시키시겠습니까?");
			if(result){
				alert("관리자 " + tmp + "을 삭제 합니다.")
				$("#deleteForm").submit();
			}
		}
	})
	
	adminPagingListAjaxHtml(1, 10);
	
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
function adminPagingListAjaxHtml(page, pageSize){
	$.ajax({
				url    : "/user/adminManager"
				,method : "post"
				,data   : "page=" + page + "&pageSize=" + pageSize + "&searchfor=" + $("#searchfor").val()
				,success : function(data){
					// html
					var html = data.split("SEPERATORSEPERATOR");
					$("#adminListTbody").html(html[0]);
					$(".pagination").html(html[1]);
				}
	});
}
function search(){
	$.ajax({
		url    : "/user/adminManager"
			,method : "post"
				,data   : "page=1&pageSize=10&searchfor=" +$("#searchfor").val()
				,success : function(data){
					// html
					var html = data.split("SEPERATORSEPERATOR");
					$("#adminListTbody").html(html[0]);
					$(".pagination").html(html[1]);
				}
	});
}
