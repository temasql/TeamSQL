$(function (){
	
	// modal
	$("#btnInsertBlackList").on("click", function() {
		$("#insertBlackListModal").css("display", "block");
	});
	
	$(document).on("change", "#pageSizeSelect", function (){
		var pageSize = $(this).val()
		blackListPagingListAjaxHtml(1,pageSize);
	});
	
	// close
	$(".close").on("click", function() {
		$("#insertBlackListModal").css("display", "none");
	});
	
	$("#insertBlackListModalOk").on("click", function(){
		var user_id_fk = $("#user_id_fk").val();
		var reason = $("#reason").val();
		
		if(user_id_fk.length == 0 || reason.length == 0){
			alert("추가할 계정 아이디와 사유를 모두 입력해 주세요")
		}else{
			var result = confirm("정말 추가 하시겠습니까?")
			if(result){
				$.ajax({
					url	 : "/blackList/insertBlackList"
						,method  : "post"
							,data : "user_id_fk=" + $("#user_id_fk").val()+"&reason=" +$("#reason").val() 
							,success : function(data){
								console.log(data)
								if(data.user_id == null){
									$("legend").html("입력하신 아이디와 일치하는 계정이 없습니다.")
								}else{
									alert(data.user_id + "를 블랙리스트에 추가했습니다.")
									$("#insertBlackListModal").css("display", "none");
									blackListPagingListAjaxHtml(1, 10);
								}
							}
				});
			}
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
	
	$("#deleteBlackList").on("click", function (){
		if($(".deleteChecked").length == 0){
			alert("해제 시킬 회원을 선택 해 주세요")
		}else{
			var tmp = "";
			$.each($(".deleteChecked"), function(idx, dt){
				console.log(dt)
				tmp += $("#blackListTbody").find("input[value=" + dt.value + "]").parent().next().text() + " ";
			})
			var result = confirm("회원 " + tmp + "을 블랙리스트에서 해제 시키시겠습니까?");
			if(result){
				alert("회원 " + tmp + "을 블랙리스트에서 해제 했습니다.")
				$("#deleteForm").submit();
			}
		}
	})
	blackListPagingListAjaxHtml(1, 10);
	
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
function blackListPagingListAjaxHtml(page, pageSize){
	$.ajax({
				url    : "/blackList/blackListManager"
				,method : "post"
				,data   : "page=" + page + "&pageSize=" + pageSize + "&searchfor=" + $("#searchfor").val()
				,success : function(data){
					// html
					var html = data.split("SEPERATORSEPERATOR");
					$("#blackListTbody").html(html[0]);
					$(".pagination").html(html[1]);
				}
	});
}
function search(){
	$.ajax({
		url    : "/blackList/blackListManager"
			,method : "post"
				,data   : "page=1&pageSize=10&searchfor=" +$("#searchfor").val()
				,success : function(data){
					// html
					var html = data.split("SEPERATORSEPERATOR");
					$("#blackListTbody").html(html[0]);
					$(".pagination").html(html[1]);
				}
	});
}

	
	
	


