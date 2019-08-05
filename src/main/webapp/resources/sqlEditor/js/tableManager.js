$(document).ready(function() {
	
	// 테이블 생성 모달창 띄우기
	$("#createTableSpan").on("click", function() {
		$("#craeteTableModal").css("display", "block");
	});
	$("#readTableSpan").on("click", function() {
		$("#readTableTitle").text($("#tableNm").val())
		$("#readTableModal").css("display", "block");
		readTableAjax()
	});
	$("#updateTableSpan").on("click", function() {
		$("#updateView").empty()
		updateColumnAjax()
		$("#updateTableTitle").text($("#tableNm").val())
		$("#updateTableModal").css("display", "block");
	});
	
	$(".tables").on("click", function(){
		$("#tableNm").val($(this).text())
		$("#tableReadThead").empty();
		$("#tableReadTbody").empty();
	})
	
	$(document).on("change", "#tableSelectChoice", function(){
		$("#tableReadThead").empty();
		$("#tableReadTbody").empty();
		readTableAjax()
	})
	$(document).on("change", "#updateSelectChoice", function(){
		$("#updateView").empty()
		updateColumnAjax()
	})
	
	// 모달창 닫기
	$(".close").on("click", function() {
		$("#tableReadThead").empty();
		$("#tableReadTbody").empty();
		$("#updateView").empty()
		$("#tableSelectChoice").val("column");
		$("#craeteTableModal").css("display", "none");
		$("#readTableModal").css("display", "none");
		$("#updateTableModal").css("display", "none");
	});
	
	// 테이블 생성
	$(document).on("click","#createTableBtn", function() {
//		$("#craeteTableModal").css("display", "none");
//		$("#createTableFrm").submit();
		var tableName = $("#tableName").val();
		var tableReg = /^[a-zA-Z][a-zA-Z0-9]{2,5}$/;
		var columnReg = /^[a-zA-Z][a-zA-Z0-9]{2,19}$/;
		if(tableReg.test(tableName)){
			var col = $(".col");
			$(".col").length
			var array = [];
			var count = 0;
			var colNameCheck = false;
			var inputName = [
				"colPKChecked"
				,"colName"
				,"colDataType"
				,"colSize"
				,"colNullCheck"
				,"colDefaultVal"
				,"colComment"
				];
			for(var objSize = 0; objSize < col.length/7; objSize++){
				colNameCheck = false;
				array[objSize] = []; 
				console.log("array[objSize] : " + array[objSize])
				for(var objVal = 0; objVal < 7; objVal++){
					array[objSize][objVal] = '{' + inputName[objVal] + ' : "' + col.eq(count).val() + '"}'
					count ++;
					if(objVal != 0 && 7/objVal == 7){
						colNameCheck = columnReg.test(col.eq(count - 1).val())
					}
				}
			}
			if(colNameCheck){
				console.log(JSON.stringify(array))
				console.log("arrayParse : " + array)
				console.log($("#acco_id").val())
				
				console.log($("#tableSelect").val())
				array[0][7] = $("#tableName").val()
				array[0][8] = $("#acco_id").val()
				$.ajax({
					url    : "/sqlEditor/createTable"
					,type : "post"
					,contentType: 'application/json'
					,data   :  JSON.stringify(array)
					,dataType: "json"
					,success : function(data) {
						
					}
					,error: function(data){
						location.replace("/sqlEditor/sqlEditorMain");
					}
				});

			}else{
				alert("컬럼의 이름을 입력해 주세요")
			}
		}else{
			alert("테이블 이름을 형식에 맞게 입력해주세요.")
		}
	});
	
	// 테이블 수정
	$(document).on("click","#updateTableBtn", function() {
		var tableName = $("#tableName").val();
		var tableReg = /^[a-zA-Z][a-zA-Z0-9]{2,5}$/;
		var columnReg = /^[a-zA-Z][a-zA-Z0-9]{2,19}$/;
		if(tableReg.test(tableName)){
			var col = $(".col");
			$(".col").length
			var array = [];
			var count = 0;
			var colNameCheck = false;
			var inputName = [
				"colPKChecked"
				,"colName"
				,"colDataType"
				,"colSize"
				,"colNullCheck"
				,"colDefaultVal"
				,"colComment"
				];
			for(var objSize = 0; objSize < col.length/7; objSize++){
				colNameCheck = false;
				array[objSize] = []; 
				console.log("array[objSize] : " + array[objSize])
				for(var objVal = 0; objVal < 7; objVal++){
					array[objSize][objVal] = '{' + inputName[objVal] + ' : "' + col.eq(count).val() + '"}'
					count ++;
					if(objVal != 0 && 7/objVal == 7){
						colNameCheck = columnReg.test(col.eq(count - 1).val())
					}
				}
			}
			if(colNameCheck){
				console.log(JSON.stringify(array))
				console.log("arrayParse : " + array)
				console.log($("#acco_id").val())
				
				console.log($("#tableSelect").val())
				array[0][7] = $("#tableName").val()
				array[0][8] = $("#acco_id").val()
				$.ajax({
					url    : "/sqlEditor/updateTable"
						,type : "post"
							,contentType: 'application/json'
								,data   :  JSON.stringify(array)
								,dataType: "json"
									,success : function(data) {
										
									}
				,error: function(data){
					location.replace("/sqlEditor/sqlEditorMain");
				}
				});
				
			}else{
				alert("컬럼의 이름을 입력해 주세요")
			}
		}else{
			alert("테이블 이름을 형식에 맞게 입력해주세요.")
		}
	});
	
	$(document).on("click", "#appendData", function(){
		appendDataAjax()
	})
	$(document).on("click", "#removeData", function(){
		$("#" + $("#deleteRowData").val()).remove()
	})
	$(document).on("click", "#updateAppendData", function(){
		updateAppendDataAjax()
	})
	$(document).on("click", "#updateRemoveData", function(){
		$("#" + $("#deleteRowData").val()).remove()
	})
	$(document).on("click", ".tableDataTr", function(){
		$("#deleteRowData").val($(this).attr("id"));
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
appendCnt = 0;

function readTableAjax(){
	var select = $("#tableSelectChoice").val();
	var tableName = $("#tableNm").val()
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
			
			$("#tableReadThead").append("<tr>")
			colNameArray.forEach(function(property){
				$("#tableReadThead").append("<th>" + property + "</th>");
			})
			$("#tableReadThead").append("</tr>")
			console.log(dataArray.length)
			$.each(dataArray ,function(idx, dataList){
				$("#tableReadTbody").append("<tr>");
				$.each(dataList, function(idx2,data){
					$("#tableReadTbody").append("<td>" + data + "</td>");
				})
				$("#tableReadTbody").append("</tr>");
			})
		
		}
	});
	
}
function updateColumnAjax(){
	var tableName = $("#tableNm").val()
	var account_id = $("#acco_id").val()
	var select = $("#updateSelectChoice").val()
	console.log(account_id)
	$.ajax({
		url    : "/sqlEditor/updateTable"
			,data : "TableName=" + tableName + "&account_id=" + account_id + "&select=" + select
			,success : function(data){
//				console.log(data)
				$("#updateView").append(data)
			}
	,error : function(error){
		console.log(error)
	}
	});
	
}

function appendDataAjax(){
	$.ajax({
		 url    : "/sqlEditor/appendDataAjax"
		,success : function(data){
			console.log(data);
			
			var rowData = "<tr class='tableDataTr' id='tableDataTr"+ appendCnt++ +"'>";
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
function updateAppendDataAjax(){
	$.ajax({
		url    : "/sqlEditor/appendDataAjax"
			,success : function(data){
				console.log(data);
				
				var rowData = "<tr class='tableDataTr' id='tableDataTr"+ appendCnt++ +"'>";
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
									$("#tableUpdateTbody").append(rowData);
			}
	});
}

