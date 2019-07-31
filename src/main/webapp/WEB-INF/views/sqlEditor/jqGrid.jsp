<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		
		<link rel="stylesheet" type="text/css" media="screen" href="${cp}/resources/jquery-ui/jquery-ui.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="${cp}/resources/jqGrid/css/ui.jqgrid.css">
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="${cp}/resources/jqGrid/js/i18n/grid.locale-kr.js"></script>
		<script src="${cp}/resources/jqGrid/js/minified/jquery.jqGrid.min.js"></script>
	</head>
	
	<body>
		<table id="list"><tr><td></td></tr></table>
		<div id="pager"></div>
		
		<script>
		    var dataArray = [
		        {
		          "name": "Lorene Battle",
		          "phone": "(936) 574-3976"
		        },
		        {
		          "name": "Wendi Downs",
		          "phone": "(815) 510-3017"
		        }
		      ];
	   
		      $(document).ready(function() {
		        $("#list").jqGrid({
		          datatype: 'local',
		          styleUI: 'Foundation',
		          data: dataArray,
		          colModel: [
		            {name: 'name', label : 'Name'},
		            {name: 'phone', label : 'Phone Number'}
		          ],
		          caption : 'Users Grid',
		          height: 'auto',
		          rowNum: 5,
		          pager: '#pager'
		        });
		      });
		</script>
		
	</body>
</html>