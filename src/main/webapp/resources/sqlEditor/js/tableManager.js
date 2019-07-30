$(document).ready(function() {
	
	// 테이블 생성 모달창 띄우기
	$("#createTableSpan").on("click", function() {
		$("#craeteTableModal").css("display", "block");
	});
	
	// 모달창 닫기
	$(".close").on("click", function() {
		$("#craeteTableModal").css("display", "none");
	});
	
	// 테이블 생성
	$(document).on("click","#createTableBtn", function() {
//		$("#craeteTableModal").css("display", "none");
//		$("#createTableFrm").submit();
		var col = $(".col");
		var array = [];
		var count = 0;
		var inputName = [
				 "colPKChecked"
				,"colName"
				,"colDataType"
				,"colSize"
				,"colNullCheck"
				,"colDefaultVal"
				,"colComment"
		];
		// [
		for(var objSize = 0; objSize < col.length/7; objSize++){
			array[objSize] = []; //array{ 
			console.log("array[objSize] : " + array[objSize])
			for(var objVal = 0; objVal < 7; objVal++){
				array[objSize][objVal] = '{' + inputName[objVal] + ' : "' + col.eq(count).val() + '"}'
				count ++;
			}
			// }
		}
		// ]
		console.log(array)
		console.log(JSON.stringify(array))
//		array = [{'colPKChecked' : "true"},{'colName' : 'asd'},{'colDataType' : 'VARCHAR2'},{'colSize' : 'ad'},{'colNullCheck' : "true"},{'colDefaultVal' : 'te'},{'colComment' : 'at'}]
//		if(colName.length <= 0){
//			alert("컬럼의 이름을 입력해 주세요")
//		}
		console.log("arrayParse : " + array)
		console.log($("#tableSelect").val())
		$.ajax({
			url    : "/sqlEditor/createTable"
			,type : "post"
			,contentType: 'application/json'
			,data   :  JSON.stringify(array)
			,dataType: "json"
			,success : function(data){
				console.log(data)
			}
		,	error: function(data){
				console.log(data);
			}
		});
	});
	
	$("#appendData").on("click", function(){
		appendDataAjax()
	})
	$("#removeData").on("click", function(){
		alert("remove");
	})
	
	$(document).on("change", "[name=colPKChecked]", function(){
		$(this).val($(this).is(":checked"))
	})
	$(document).on("click", "[name=colNullCheck]", function(){
		$(this).val($(this).is(":checked"))
	})
	$(document).on("change", "#tableSelect", function(){
//		alert($(this).val());
		$("#tableSelect").val($(this).val());
//		alert($("#tableSelect").val())
		console.log($("#tableSelect").val())
	})
	
});

function appendDataAjax(){
	$.ajax({
		 url    : "/sqlEditor/appendDataAjax"
		,success : function(data){
			console.log(data);
			
			var rowData = "<tr>";
			rowData += "<td><input  name='colPKChecked' class='col' value='false' type='checkbox'></td>"
			rowData += "<td><input  name='colName' class='tableManagerText colName col' type='text'/> </td>"
			rowData += "<td><select class='tableManagerSelectBox col'>";
			
			data.dataTypeList.forEach(function (dataType){
				rowData += "<option value='" + dataType +"'>" + dataType + "</option>"
			})
			rowData +="</select></td>";
			rowData += "<td><input name='colSize' class='tableManagerText col' type='text'/> </td>"
			rowData += "<td><input name='colNullCheck' class='col' value='false' type='checkbox'></td>"
			rowData += "<td><input name='colDefaultVal' class='tableManagerText col' type='text'/> </td>"
			rowData += "<td><input name='colComment' class='tableManagerText col' type='text'/> </td>"
			rowData += "</tr>"
			$("#tableDataTbody").append(rowData);
		}
	});
}

var editor = ace.edit("editor");
editor.setTheme("ace/theme/twilight");
editor.session.setMode("ace/mode/sql");
