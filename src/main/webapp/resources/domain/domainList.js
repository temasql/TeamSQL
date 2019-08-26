$(function() {
	domainPagingListAjaxHtml(1, 10);

	$('#domainType').keydown(function(e) {
		if (e.keyCode == 13) {
			postPagingListAjaxHtml(1,10);
		}
	});

	// 도메인 관리 화면에서 추가버튼 클릭 이벤트 
	$(document).on("click", "#domAddBtn", function() {
		var cdomain_name = $("#inputName").val();
		var cdomain_type = $("#inputType").val();
		
		if (cdomain_name == "" || cdomain_type == "") {
			alert("도메인명과 타입을 입력해 주세요.");
			return;
		}
		
		$.ajax({
			method : "post",
			url : "/commonDomain/insertCommonDomain",
			data : $("#addDomainFrm").serialize(),
			success : function(data) {
				if(data.msg == "존재하는 도메인") {
					alert("이미 존재하는 도메인명 입니다. \n 다른 도메인명을 입력해 주세요.");
					return;
				}
				
				if(data.typeMsg == "유효하지 않는 데이터 타입") {
					alert("유효하지 않는 데이터 타입 입니다.");
					return;
				}
				
				alert("도메인 등록 성공");
				
				$("#inputName").val("");
				$("#inputType").val("");
				
				domainList();
			},
			error : function(error) {
				alert("등록 실패");
			}
		})
	})
	
	
	// 도메인 관리 화면에서 수정버튼 클릭 이벤트
	$(document).on("click", ".modiBtn", function(){
					
//		console.log( $(this).parent().prev().prev().find("input").val() )
//		console.log( $(this).parent().prev().find("input").val() )
		
		
		var cdomain_id = $(this).parent().prev().prev().prev().find("input").val()
		var cdomain_name = $(this).parent().prev().prev().find("input").val()
		var cdomain_type = $(this).parent().prev().find("input").val()
		
		$("#dom_id").val(cdomain_id);
		$("#inputUpdateName").val(cdomain_name);
		$("#inputUpdateType").val(cdomain_type);

		if (cdomain_name == "" || cdomain_type == "") {
			alert("도메인명과 데이터 타입을 입력해 주세요.");
			return;
		}
		console.log($("#domainUpdateFrm").serialize())
		$.ajax({
			method : "post",
			url : "/commonDomain/updateCommonDomain",
			data : $("#domainUpdateFrm").serialize(),
			success : function(data) {
				if(data.msg == "존재하는 도메인") {
					alert("이미 존재하는 도메인명 입니다.\n 다른 도메인명을 입력해 주세요.");
					return;
				}
				
				if(data.typeMsg == "유효하지 않는 데이터 타입") {
					alert("유효하지 않는 데이터 타입 입니다.");
					return;
				}
				
				alert("도메인 수정 성공");
				domainList();
			},
			error : function(error) {
				alert("도메인 수정 실패");
			}
		})
		domainList();
	})
			
			
	// 도메인 관리 화면에서 삭제버튼 클릭 이벤트
	$(document).on("click", ".delBtn", function() {
					
		var domainId = $(this).parents("td").prevAll(".domainId").children().val();
					
		$("#deleteId").val(domainId);
				
		$("#delFrm").submit();
	})
	
	
})


function domainPagingListAjaxHtml(page, pageSize) {
	var domainId = $("#domainId").val()
	
	$.ajax({
		  url : "/commonDomain/domainList"
		, method : "post"
		, data : "page=" + page + "&pageSize=" + pageSize
		, success : function(data) {
			var html = data.split("SEPERATORSEPERATOR");
			
			$("#domainListTbody").html(html[0]);
			$(".pagination").html(html[1]);
		}
	})
}
