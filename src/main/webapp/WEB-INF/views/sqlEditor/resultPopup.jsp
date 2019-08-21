<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<link rel="stylesheet" type="text/css" media="screen" href="${cp}/resources/jquery-ui/jquery-ui.min.css">
<link rel="stylesheet" type="text/css" media="screen" href="${cp}/resources/jqGrid/css/ui.jqgrid.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<head>
<meta charset="UTF-8">
<title>Result PopUp</title>
<script type="text/javascript">
$(document).ready(function() {
	var dragText = $("#dragText").val();
	var account_id = $("#account_id").val();
	
	$.ajax({
		url : "/worksheet/selectRun",
		dataType : "json",
		method : "get",
		async : false,
		data : "dragText=" + dragText + "&account_id=" + account_id,
		success : function(data) {
			
			console.log(data);
			console.log(data.resultList);
			
			if(data.errorMsg == null) {
				var dataArray = new Array(); // 데이터 담을 배열
				var colNameArray = new Array(); // 컬럼명 담을 배열
				var colModelArray = new Array();
				var reg = /'([\w\d\s]*)'/; // 싱글 쿼테이션 제거 정규식
				
				var colNames = data.resultList[0]; // 컬럼명들이 있는 배열
				console.log(colNames);
				
				for (var i = 0; i < colNames.length; i++) { // 싱글 쿼테이션 제거한 컬럼명이 들어있는 배열 구성
					if(colNames[i].match(reg) == null)
						colNameArray.push(colNames[i]);
					else
						colNameArray.push(colNames[i].match(reg)[1]);
				}
				
				$.each(colNameArray, function(idx, obj) {
					var t = {name : obj, label : obj, align : 'center'};
					colModelArray.push(t);
				});
				
				$.each(data.resultList, function(idx, obj){
					if(idx >= 1) {
						var i = 0;
						var t = new Object();
						for (var i = 0; i < colNameArray.length; i++) {
							t[colNameArray[i]] = obj[i];
						}
						dataArray.push(t);
					}
				});
				
				console.log(dataArray);
				
				$("#resultTable2").jqGrid({
					datatype : 'local',
					styleUI: 'Foundation',
					data : dataArray,
					colModel :colModelArray,
					pager : '#pager',
					rowNum : 10,
					rowList : [10,20,30],
					height : '250px',
					autowidth: true
				});
			}else {
				var error = "<h2>작업 실패<br><br>";
				error += "ERROR) " + data.errorMsg + "</h2>";
				$("body").append(error);
			}
		}
	});
	
});
</script>
<style type="text/css">
#resultTable2 {
	font-size: 1.5em;
}

</style>
</head>
<body>
<table id="resultTable2"></table>
<div id="pager"></div>
<br><br>
<input type="hidden" id="dragText" value="${dragText}">
<input type="hidden" id="account_id" value="${account_id}">
</body>
<script src="${cp}/resources/jqGrid/js/i18n/grid.locale-kr.js"></script>
<script src="${cp}/resources/jqGrid/js/minified/jquery.jqGrid.min.js"></script>
</html>