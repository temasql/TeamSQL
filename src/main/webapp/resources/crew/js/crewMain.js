$(function (){
	
	$(document).on("change", "#select", function (){
		$("#accountSelect").val($(this).val());
		crewPagingListAjaxHtml(1,10);
	});
	
	// modal
	$("#btnInviteCrew").on("click", function() {
		$("#inviteModal").css("display", "block");
	});
	
	// close
	$(".close").on("click", function() {
		$("#inviteModal").css("display", "none");
	});
	
	$("#btnInviteCrewOk").on("click", function(){
		var user_id = $("#user_id").val();
		alert()
		var account_id_fk = $("#select").val();
		
		if(user_id.length == 0){
			alert("초대할 계정의 아이디를 입력 해 주세요")
		}else{
			$.ajax({
				url	 : "/crew/inviteCrew"
					,method  : "post"
						,data : "user_id=" + user_id +"&ac_id=" +account_id_fk
						,success : function(data){
							console.log(data)
							if(data.user_id == null){
								$("legend").html("입력하신 아이디와 일치하는 계정이 없습니다.")
							}else{
								$("#inviteModal").css("display", "none");
								alert(user_id + "님을 초대하였습니다.")
							}
						}
			});
		}
	});
	
	$(document).on("change", ".checkbox", function (){
		if($(this).is(":checked")){
            $("#deleteForm").append("<input type='hidden' class='deleteChecked' name='deleteCheck' value='"+ $(this).val()+"'/>")
        }else{
            $(this).removeAttr("name")
            $("#deleteForm :last-child").remove();
        }
	})
	
	$("#deleteCrew").on("click", function (){
		if($(".deleteChecked").length == 0){
			alert("탈퇴 시킬 회원을 선택 해 주세요")
		}else{
			$("#deleteForm").submit();
		}
	})
	crewPagingListAjaxHtml(1, 10);
	
	$("#btnSearch").on("click", function(){
		search()
	})
	
})
function crewPagingListAjaxHtml(page, pageSize){
	$.ajax({
				url    : "/crew/crewManager"
				,method : "post"
				,data   : "page=" + page + "&pageSize=" + pageSize + "&searchfor=" + $("#searchfor").val() + "&account_id_fk=" + $("#select").val()
				,success : function(data){
					// html
					var html = data.split("SEPERATORSEPERATOR");
					$("#crewListTbody").html(html[0]);
					$(".pagination").html(html[1]);
				}
	});
}
function search(){
	$.ajax({
		url    : "/crew/crewManager"
			,method : "post"
				,data   : "page=1&pageSize=10&searchfor=" +$("#searchfor").val() + "&account_id_fk=" + $("#select").val()
				,success : function(data){
					// html
					var html = data.split("SEPERATORSEPERATOR");
					$("#crewListTbody").html(html[0]);
					$(".pagination").html(html[1]);
				}
	});
}
