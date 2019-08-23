$(function() {
	templatePagingListAjaxHtml(1, 10);

	$('#templateOriginal').keydown(function(e) {
		if (e.keyCode == 13) {
			postPagingListAjaxHtml(1,10);
		}
	});

	
	$(document).on("click", ".modiBtn", function(){
					
		var ctemplate_id =  $(this).parent().prev().prev().prev().find("input").val()
		var ctemplate_abb = $(this).parent().prev().prev().find("input").val()
		var ctemplate_original = $(this).parent().prev().find("input").val()
					
		$("#temp_id").val(ctemplate_id);
		$("#inputUpdateAbb").val(ctemplate_abb);
		$("#inputUpdateOriginal").val(ctemplate_original);
					
		if (ctemplate_abb == "" || ctemplate_original == "") {
			alert("약어와 원문을 입력해 주세요.");
			return;
		}

		$.ajax({
			method : "post",
			url : "/commonTemplate/updateCommonTemplate",
			data : $("#templateUpdateFrm").serialize(),
			success : function(data) {
				if(data.msg == "존재하는 템플릿") {
					alert("이미 존재하는 약어 입니다.\n 다른 약어를 입력해 주세요.");
					return;
				}
				alert("템플릿 수정 성공");
				templateList();
			},
			error : function(error) {
				alert("템플릿 수정 실패");
			}
		})
		templateList();
	})
			
			
	$(document).on("click", ".delBtn", function() {
					
		var templateId = $(this).parents("td").prevAll(".templateId").children().val();
					
		$("#deleteId").val(templateId);
				
		$("#delFrm").submit();
	})
	

	$(document).on("click", "#tmpAddBtn", function() {
		var ctemplate_abb = $("#inputAbb").val();
		var ctemplate_original = $("#inputOriginal").val();
		
		if (ctemplate_abb == "" || ctemplate_original == "") {
			alert("약어와 원문을 입력해 주세요.");
			return;
		}
		
		$.ajax({
			method : "post",
			url : "/commonTemplate/insertCommonTemplate",
			data : $("#addTemplateFrm").serialize(),
			success : function(data) {
				if(data.msg == "존재하는 템플릿") {
					alert("이미 존재하는 약어 입니다. \n 다른 약어를 입력해 주세요.");
					return;
				}
				
				alert("템플릿 등록 성공");
				
				$("#inputAbb").val("");
				$("#inputOriginal").val("");
				
				templateList();
			},
			error : function(error) {
				alert("등록 실패");
			}
		})
	})
})


function templatePagingListAjaxHtml(page, pageSize) {
	var templateId = $("#templateId").val()
	
	$.ajax({
		  url : "/commonTemplate/templateList"
		, method : "post"
		, data : "page=" + page + "&pageSize=" + pageSize
		, success : function(data) {
			var html = data.split("SEPERATORSEPERATOR");
			
			$("#templateListTbody").html(html[0]);
			$(".pagination").html(html[1]);
		}
	})
}
	
