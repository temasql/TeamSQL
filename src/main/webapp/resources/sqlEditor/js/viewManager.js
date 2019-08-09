$(document).ready(function() {
	
	// 뷰 생성 모달창 띄우기
	$("#createViewSpan").on("click", function() {
		$("#craeteViewModal").css("display", "block");
	});
	
	$("#updateViewSpan").on("click", function() {
		$("#updateViewModal").css("display", "block");
		$("#oldVN").val($("#viewNm").val())
		updateViewTextArea();
	});
	
	$(".views").on("click", function(){
		$("#viewNm").val($(this).text())
		$("#oldVN").val($(this).text())
	})
	$(".sc_id").val($("#acco_id").val())
	
	$("#readViewSpan").on("click", function() {
		$("#readViewTitle").text($("#viewNm").val())
		$("#readViewModal").css("display", "block");
		readViewAjax()
	});
	
	$(document).on("change", "#viewSelectChoice", function(){
		$("#viewReadThead").empty();
		$("#viewReadTbody").empty();
		readViewAjax()
	})	
	// 모달창 닫기
	$(".close").on("click", function() {
		$("#craeteViewModal").css("display", "none");
		$("#updateViewModal").css("display", "none");
		$("#viewReadThead").empty();
		$("#viewReadTbody").empty();
		$("#readViewModal").css("display", "none");
	});
	$("#viewText").text("SELECT\n\nFROM")
	
	$(document).on("click","#createViewBtn", function() {
//		$("#craeteTableModal").css("display", "none");
//		$("#createTableFrm").submit();
		var tableName = $("#viewNm").val();
		var tableReg = /^[a-zA-Z][a-zA-Z0-9]{2,5}$/;
		var columnReg = /^[a-zA-Z][a-zA-Z0-9]{2,19}$/;
		$("#createViewFrm").submit();
	});
	$(document).on("click","#updateViewBtn", function() {
//		$("#craeteTableModal").css("display", "none");
//		$("#createTableFrm").submit();
		var tableName = $("#updateViewName").val();
		var tableReg = /^[a-zA-Z][a-zA-Z0-9]{2,5}$/;
		var columnReg = /^[a-zA-Z][a-zA-Z0-9]{2,19}$/;
		$("#updateViewFrm").submit();
	});
	
	$("#deleteViewSpan").on("click", function(){
		var viewName = $("#viewNm").val();
		var ac_id = $("#acco_id").val();
		location.href="/sqlEditor/deleteView?view_name=" + viewName + "&sc_id= " + ac_id; 
	})
	
});

function updateViewTextArea(){
	var viewName = $("#viewNm").val();
	var scName = $("#acco_id").val();
	$.ajax({
		 url    : "/sqlEditor/updateViewTA"
		 ,data : "view_name=" +viewName + "&sc_id=" + scName
		,success : function(data){
			console.log(data)
			$("#scName").val(scName);
			$("#updateViewName").val(viewName);
			$("#updateViewText").text(data.data);
		}
	});
}

function readViewAjax(){
	var select = $("#viewSelectChoice").val();
	var tableName = $("#viewNm").val()
	var account_id = $("#acco_id").val()
	$.ajax({
		 url    : "/sqlEditor/selectTable"
		 ,data : "select=" + select + "&TableName=" + tableName + "&account_id=" + account_id 
		,success : function(data){
			
			var dataArray = new Array(); // 데이터 담을 배열
			
			var colNameArray = data.resultList[0]; // 컬럼명들이 있는 배열
			
			
			$.each(data.resultList, function(idx, data){
				if(idx >= 1) {
					var t = new Array();
					for (var i = 0; i < colNameArray.length; i++) {
						t.push(data[i]);
						console.log("22kkk" + data[i])
					}
					dataArray.push(t);
				}
			});
			console.log(colNameArray)
			console.log(dataArray)
			
			$("#viewReadThead").append("<tr>")
			colNameArray.forEach(function(property){
				$("#viewReadThead").append("<th>" + property + "</th>");
			})
			$("#viewReadThead").append("</tr>")
			console.log(dataArray.length)
			$.each(dataArray ,function(idx, dataList){
				$("#viewReadTbody").append("<tr>");
				$.each(dataList, function(idx2,data){
					$("#viewReadTbody").append("<td>" + data + "</td>");
				})
				$("#viewReadTbody").append("</tr>");
			})
		
		}
	});
	
}
