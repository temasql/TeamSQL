$(document).ready(function(){
	historyDetailPagingListAjaxHtml(1,10);
	
	// 모달창 생성
	$("#dbChangedDetailListBody").delegate("tr" , "click", function(){
		$("#textQuery").empty();
		$("#ddlQueryModal").css("display", "block");
		console.log("클릭");
		var str =($(this).find(".hiddenQuery").val());
		console.log(str)
//		str = decodeURI(str);
//		str = str.replace(/\"/gi, "");
		$("#textQuery").html("<h4>"+str+"</h4>");
		jQuery("#ddlQueryModal")[0].scrollIntoView(); 
	});
	
	// 모달창 닫기
	$(".close").on("click",function(){
		$("#ddlQueryModal").css("display","none");
	});
	
	$(document).on("change", "#pageSizeSelect", function (){
		var pageSize = $(this).val()
		historyDetailPagingListAjaxHtml(1,pageSize);
	});
	
	// 검색어 엔터
	$('#searchfor').keydown(function(e) {
		if (e.keyCode == 13) {
			historyDetailPagingListAjaxHtml(1,10);
		}
	});
	// 입력시 검색
	$('#searchfor').keyup(function(e) {
		historyDetailPagingListAjaxHtml(1,10);
	})
	
	// 검색버튼 클릭
	$("#btnSearch").on("click",function(){
		historyDetailPagingListAjaxHtml(1,10);
	})
	
});

function historyDetailPagingListAjaxHtml(page, pageSize){
	$.ajax({
			url    : "/history/historyDetail"
			,method : "post"
			,data   : "page=" + page + "&pageSize=" + pageSize + "&object_owner=" + $("#object_owner").val() + "&search=" + $("#searchfor").val() + "&selectBox=" +$("#select").val()
			,success : function(data){
				// html
				var html = data.split("SEPERATORSEPERATOR");
				$("#dbChangedDetailListBody").html(html[0]);
				$(".pagination").html(html[1]);
				}
	});
}

