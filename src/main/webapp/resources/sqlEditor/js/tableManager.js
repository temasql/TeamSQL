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
				console.log($("#tableSelect").val())
				array[0][7] = $("#tableName").val()
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

			}else{
				alert("컬럼의 이름을 입력해 주세요")
			}
		}else{
			alert("테이블 이름을 형식에 맞게 입력해주세요.")
		}
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
