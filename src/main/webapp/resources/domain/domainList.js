$(function() {
	domainPagingListAjaxHtml(1, 10);

	$('#domainType').keydown(function(e) {
		if (e.keyCode == 13) {
			postPagingListAjaxHtml(1,10);
		}
	});
	
	$(document).on("click", ".modiBtn", function(){
					
		var domainId = $(this).parents("td").prevAll(".domainId").children().val();
		var domainName = $(this).parents("td").prevAll(".domainName").children().val();
		var domainType = $(this).parents("td").prevAll(".domainType").children().val();
					
		$("#updateId").val(domainId);
		$("#updateName").val(domainName);
		$("#updateType").val(domainType);
//		console.log($("#domainId").val())
//		console.log($("#domainName").val())
//		console.log($("#domainType").val())
//		console.log($("#frm").serialize())
					
		$("#frm").submit();
	})
			
			
	$(document).on("click", ".delBtn", function() {
					
		var domainId = $(this).parents("td").prevAll(".domainId").children().val();
					
		$("#deleteId").val(domainId);
				
		$("#delFrm").submit();
	})
	
	
	$(document).on("click", "#domAddBtn", function() {
		if(validation()) {
			$("#addDomainFrm").submit();
		} else {
			alert("도메인명과 데이터 타입을 입력해 주세요.");
		}
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
	
function validation(){
	var domainName = $("#domainName").val();
	var domainType = $("#domainType").val();
	
	if(domainName == '' || domainType == '') {
		return false;
	}
	return true;
}