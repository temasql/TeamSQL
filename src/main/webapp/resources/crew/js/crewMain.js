$(function (){
	$(document).on("change", "#select", function (){
		$("#accountSelect").val($(this).val());
		crewPagingListAjaxHtml(1,10);
	});
	
	// modal
	$(document).on("click","#btnInviteCrew", function() {
		$("#inviteModal").css("display", "block");
	});
	
	// close
	$(".close").on("click", function() {
		$("#inviteModal").css("display", "none");
	});
	
	$(document).on("click","#btnInviteCrewOk", function(){
		var user_id = $("#user_id").val();
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
							if(data.overCrew == "true"){
								alert(user_id + "이미 존재하는 구성원입니다.")
							}else if(data.overCrew != "true" &&data.user_id == null){
								$("legend").html("입력하신 아이디와 일치하는 계정이 없습니다.")
							}else if(data.overCrew != "true" &&data.user_id != null){
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
	
	$(document).on("click", "#deleteCrew", function (){
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
					$("#btnDelAndAdd").html(html[0]);
					$("#crewListTbody").html(html[1]);
					$(".pagination").html(html[2]);
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
					$("#btnDelAndAdd").html(html[0]);
					$("#crewListTbody").html(html[1]);
					$(".pagination").html(html[2]);
				}
	});
}
