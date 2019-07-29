$(document).ready(function() {
	
	// alert 띄우기
	if($("#msg").val().trim().length > 0) {
		alert($("#msg").val());
	}
	
	// DB계정 생성 모달창 띄우기
	$("#createTableSpan").on("click", function() {
		$("#craeteTableModal").css("display", "block");
	});
	
	// 모달창 닫기
	$(".close").on("click", function() {
		$("#craeteTableModal").css("display", "none");
	});
	
	// DB계정 생성
	$("#createTableBtn").on("click", function() {
		alert()
//		$("#accountAddfrm").submit();
	});
	
	$("#appendData").on("click", function(){
		$("#tableDataTbody").append("<tr>")
		$("#tableDataTbody").append("<td scope='row' id='dataCheckBox'></td>")
		$("#tableDataTbody").append("<td scope='row'><input type='text' value='COLUMN1'/></td>")
		$("#tableDataTbody").append("<td scope='row'></td>")
		$("#tableDataTbody").append("<td scope='row'></td>")
		$("#tableDataTbody").append("<td scope='row'></td>")
		$("#tableDataTbody").append("<td scope='row'></td>")
		$("#tableDataTbody").append("</tr>")
	})
	$("#removeData").on("click", function(){
		alert("remove");
	})
	
});

var editor = ace.edit("editor");
editor.setTheme("ace/theme/twilight");
editor.session.setMode("ace/mode/sql");
