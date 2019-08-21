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
	});
	
	// 모달창 닫기
	$(".close").on("click",function(){
		$("#ddlQueryModal").css("display","none");
	});
});

function historyDetailPagingListAjaxHtml(page, pageSize){
	$.ajax({
			url    : "/history/historyDetail"
			,method : "post"
			,data   : "page=" + page + "&pageSize=" + pageSize + "&object_owner=" + $("#object_owner").val()
			,success : function(data){
				// html
				var html = data.split("SEPERATORSEPERATOR");
				$("#dbChangedDetailListBody").html(html[0]);
				$(".pagination").html(html[1]);
				}
	});
}

