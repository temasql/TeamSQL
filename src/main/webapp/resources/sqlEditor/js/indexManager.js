$(document).ready(function() {

	// 인덱스 생성 모달창 띄우기
	$("#createIndexSpan").on("click", function() {
		var owner = $("#table_owner").val().trim();				// DB계정명
		var table_owner= owner.toUpperCase();					// DB계정명 대문자 변경
		
		$("#craeteIndexModal").css("display", "block"); // 모달창 띄우기
		$("#index_tableBody").empty();	// 테이블 초기화
		$("#indexName").val("");		// 인덱스명 초기화
		$("#hidden_table").empty();		// DDL쿼리 초기화
		$("#define").prop("selected", true);
		cnt = 0;
		
		$("#td_select").empty();
		$.ajax({
			url :"/sqlEditor/indexTable"
		  , type : "post"
		  , data : "index_owner=" + table_owner
		  , success : function(data){
			  var temp = "";
			  for (var i = 0; i < data.length; i++) {
				temp += "<option>" + data[i] + "</option>";
			}
			  $("#td_select").append(temp);
		  },error : function(){
			  return;
		  }
		});
		
		$("#table_name").val($("#td_select").val())
		
	});
	
	
	
	// 생성 셀렉박스 changed 이벤트
	$("#td_select").on("change",function(){
		var table_name = $("#td_select").val().toUpperCase();
		
		$.ajax({
			url : "/sqlEditor/indexTblCol"
		  , type : "post"
		  , data : "table_name=" + table_name
		  , success : function(data){
		  }
		});
		
	})
	
	// 인덱스 컬럼 삭제
	$("#removeIndex").on("click", function() {
		$("#" + $("#deleteCol").val()).remove()
		var deleteId = $("#deleteCol").val();
		
		$("#" + deleteId).remove();
	});
	$(document).on("click", ".selecCol", function() {
		$("#deleteCol").val($(this).parent().attr("id"));
		console.log("태그 아이디 : " + $("#deleteCol").val($(this).parent().attr("id")).val())
	});
	
	// 인덱스 편집 컬럼 삭제
	$("#updateRemoveIndex").on("click", function() {
		$("#" + $("#updateDeleteHidden").val()).remove()
		var deleteId = $("#updateDeleteHidden").val();
		$("#" + deleteId).remove();
	});
	
	// 인덱스 편집 원래 있던 컬럼 선택시 이벤트
	$(document).on("click", ".beforeSelecCol", function() {
		console.log("클릭")
		
		$("#updateDeleteHidden").val($(this).parent().attr("id"));
		
		console.log($("#updateDeleteHidden").val($(this).parent().attr("id")).val());
	});
	
	// 인덱스 편집 추가된 컬럼 선택시 이벤트
	$(document).on("click", ".updateSelecCol", function() {
		console.log("클릭")
		
		$("#updateDeleteHidden").val($(this).parent().attr("id"));
		console.log($("#updateDeleteHidden").val($(this).parent().attr("id")).val());
	});
	
	
	
	
	// DDL 쿼리 출력
	$("#select_1").on("change", function(){
		if($("#select_1").val()== "DDL"){
			if($("#tableColumn").length >= 1){
		$.ajax({
			url : "/sqlEditor/ddlQuery"
		  , type : "post"
		  , data : $("#createIndexFrm").serialize()
		  , success : function(data){
			$("#hidden_table").empty();
			var temp = "<br><br><h5>" + data.trim() + "</h5>";
			$("#hidden_table").append(temp);
			$("#index_table").hide();
		  },error : function(){
			  alert("인덱스 표현식을 하나 이상 지정해야 합니다.");
			  $("#define").prop("selected", true);
		  }
		});
		}else{
			alert("인덱스 표현식을 하나 이상 지정해야 합니다.");
			$("#define").prop("selected", true);
		}
		}else{
			$("#hidden_table").empty();
			$("#index_table").show();
		}
		
	})
	
	// 인덱스 생성 버튼 클릭 이벤트
	$("#createIndexBtn").on("click",function(){
		
		if($("#indexName").val().trim() <= 0){
			alert("인덱스 이름을 입력해주세요.");
			$("#indexName").val("");
			$("#indexName").focus();
			return;
		}
		if($("#td_indexType_select").val() == "BITMAP"){
			alert("오류발생 : \n" +
					"ORA-00439: feature not enabled: Bit-mapped indexes")
			return;
		}
		$.ajax({
			url : "/sqlEditor/createIndex"
		  , type : "post"
		  , data : $("#createIndexFrm").serialize()
		  , success : function(data){
			  $("#craeteIndexModal").css("display","none");
			  alert("인덱스가 생성 되었습니다.")
			  location.replace("/sqlEditor/sqlEditorMain");
			  $("#data.hidden_tableName").val(data.hidden_tableName);
			  
		  },error : function(){
			  alert("인덱스 생성에 실패하였습니다.")
		  }
		})
	});
		
	// 인덱스 편집 버튼 클릭 이벤트
	$("#updateIndexBtn").on("click",function(){
		
		if($("#updateIndexName").val().trim() <= 0){
			alert("인덱스 이름을 입력해주세요.");
			$("#updateIndexName").val("");
			$("#updateIndexName").focus();
			return;
		}
		if($("#updateTd_indexType_select").val() == "BITMAP"){
			alert("오류발생 : \n" +
			"ORA-00439: feature not enabled: Bit-mapped indexes")
			return;
		}
		$.ajax({
			url : "/sqlEditor/updateIndex"
				, type : "post"
					, data : $("#updateIndexFrm").serialize()
					, success : function(data){
						$("#updateIndexModal").css("display","none");
						alert("인덱스가 편집 되었습니다.")
						location.replace("/sqlEditor/sqlEditorMain");
						$("#data.hidden_tableName").val(data.hidden_tableName);
						
					},error : function(){
						alert("인덱스 편집에 실패하였습니다.")
					}
		})
	});
	
	// 모달창 닫기
	$(".close").on("click", function() {
		$("#craeteIndexModal").css("display", "none");
		$("#selectIndexModal").css("display", "none");
		$("#updateIndexModal").css("display", "none");
	});
	
	// 인덱스 컬럼 추가
	$("#appendIndex").on("click",function(){
		appendIndexAjax();
	})
	
	// 인덱스 변경 컬럼 추가
	$("#updateaApendIndex").on("click",function(){
		updateAppendIndexAjax();
	})
	
	
	// 인덱스 조회 모달창 띄우기
	$("#readIndexSpan").on("click", function() {
		$("#selectIndexModal").css("display", "block");
		$("#selectIdxCode").prop("selected","selected");
		$("#readIndexDiv").empty();// div 비우기
		
		var selectVal = $("#readIndexSelect").val().trim();		// 선택된 옵션
		var owner = $("#tableOwner").val().trim();				// DB계정명
		var table_owner= owner.toUpperCase();					// DB계정명 대문자 변경
		var index_name= $("#indexName").val().trim();			// 인덱스명
		
		$.ajax({
			url : "/sqlEditor/readIndexQuery"
		  , method : "post"
		  , data : "index_owner=" + table_owner + "&index_name=" + index_name
		  , success : function(data){
			  var temp = "<br><br><h4>" + data.trim() + "</h4>";
			  $("#readIndexDiv").append(temp);
			  
		  },error : function(){
			  var temp = "<br><br><h4>인덱스가 조회되지 않습니다.</h4>";
			  $("#readIndexDiv").append(temp);	
		  }
		});
	});
	
	// 인덱스 조회 셀렉박스 change이벤트
	$("#readIndexSelect").on("change",function(){
		$("#readIndexDiv").empty();
		
		var selectVal = $("#readIndexSelect").val().trim();		// 선택된 옵션
		var owner = $("#tableOwner").val().trim();				// DB계정명
		var table_owner= owner.toUpperCase();					// DB계정명 대문자 변경
		var index_name= $("#indexName").val().trim();			// 인덱스명
		
		if(selectVal == "코드"){
			$.ajax({
				url : "/sqlEditor/readIndexQuery"
			  , method : "post"
			  , data : "index_owner=" + table_owner + "&index_name=" + index_name
			  , success : function(data){
				  var temp = "<br><br><h4>" + data.trim() + "</h4>";
				  $("#readIndexDiv").empty();
				  $("#readIndexDiv").append(temp);
				  
			  },error : function(){
				  var temp = "<br><br><h4>인덱스가 조회되지 않습니다.</h4>";
				  $("#readIndexDiv").append(temp);	
			  }
			});
		}else if(selectVal =="세부정보"){
			$.ajax({
				url : "/sqlEditor/readIndexTable"
			  , method : "post"
			  , data : "table_owner=" + table_owner + "&index_name=" + index_name
			  , success : function(data){
				  var temp = "<br><br><table class='table table-hover'>"+
				  			"<tbody>" +
				  				"<tr class='table-active'>" +
				  				"<th scope='row'>이름</td><th scope='row'>값</td>"+
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>CREATED</th>" +  
				  				   "<td>" + data.created +"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>LAST_DDL_TIME</th>" +  
				  				   "<td>" + data.last_ddl_time +"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>OWNER</th>" +  
				  				   "<td>" + data.owner +"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>INDEX_NAME</th>" +  
				  				   "<td>" + data.index_name +"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>INDEX_TYPE</th>" +  
				  				   "<td>" + data.index_type +"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>TABLE_OWNER</th>" +  
				  				   "<td>" + data.table_owner+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>TABLE_NAME</th>" +  
				  				   "<td>" + data.table_name +"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>UNIQUENESS</th>" +  
				  				   "<td>" + data.uniqueness +"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>COMPRESSION</th>" +  
				  				   "<td>" + data.compression +"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>PREFIX_LENGTH</th>" +  
				  				   "<td>" + data.prefix_length+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>TABLESPACE_NAME</th>" +  
				  				   "<td>" + data.tablespace_name+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>INI_TRANS</th>" +  
				  				   "<td>" + data.ini_trans+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>MAX_TRANS</th>" +  
				  				   "<td>" + data.max_trans+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>INITIAL_EXTENT</th>" +  
				  				   "<td>" + data.initial_extent+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>NEXT_EXTENT</th>" +  
				  				   "<td>" + data.next_extent +"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>MIN_EXTENTS</th>" +  
				  				   "<td>" + data.min_extents+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>MAX_EXTENTS</th>" +  
				  				   "<td>" + data.max_extents +"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>PCT_INCREASE</th>" +  
				  				   "<td>" + data.pct_increase+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>PCT_THRESHOLD</th>" +  
				  				   "<td>" + data.pct_threshold +"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>INCLUDE_COLUMN</th>" +  
				  				   "<td>" + data.include_column+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>FREELISTS</th>" +  
				  				   "<td>" + data.freelists+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>FREELIST_GROUPS</th>" +  
				  				   "<td>" + data.freelist_groups+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>PCT_FREE</th>" +  
				  				   "<td>" + data.pct_free+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>LOGGING</th>" +  
				  				   "<td>" + data.logging+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>BLEVEL</th>" +  
				  				   "<td>" + data.blevel+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>LEAF_BLOCKS</th>" +  
				  				   "<td>" + data.leaf_blocks+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>DISTINCT_KEYS</th>" +  
				  				   "<td>" + data.distinct_keys+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>AVG_LEAF_BLOCKS_PER_KEY</th>" +  
				  				   "<td>" + data.avg_leaf_blocks_per_key+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>AVG_DATA_BLOCKS_PER_KEY</th>" +  
				  				   "<td>" + data.avg_data_blocks_per_key+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>CLUSTERING+FACTOR</th>" +  
				  				   "<td>" + data.clustering_factor+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>STATUS</th>" +  
				  				   "<td>" + data.status+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>NUM_ROWS</th>" +  
				  				   "<td>" + data.num_rows+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>SAMPLE_SIZE</th>" +  
				  				   "<td>" + data.sample_size+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>LAST_ANALYZED</th>" +  
				  				   "<td>" + data.last_analyzed+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>DEGREE</th>" +  
				  				   "<td>" + data.degree+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>INSTANCES</th>" +  
				  				   "<td>" + data.instances+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>PARTITIONED</th>" +  
				  				   "<td>" + data.partitioned+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>TEMPORARY</th>" +  
				  				   "<td>" + data.temporary+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>GENERATED</th>" +  
				  				   "<td>" + data.generated+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>SECONDARY</th>" +  
				  				   "<td>" + data.secondary+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>BUFFER_POOL</th>" +  
				  				   "<td>" + data.buffer_pool+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>FLASH_CACHE</th>" +  
				  				   "<td>" + data.flash_cache+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>CELL_FLASH_CACHE</th>" +  
				  				   "<td>" + data.cell_flash_cache+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>USER_STATS</th>" +  
				  				   "<td>" + data.user_stats+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>DURATION</th>" +  
				  				   "<td>" + data.duration+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>PCT_DIRECT_ACCESS</th>" +  
				  				   "<td>" + data.pct_direct_access+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>ITYP_OWNER</th>" +  
				  				   "<td>" + data.ityp_owner+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>ITYP_NAME</th>" +  
				  				   "<td>" + data.ityp_name+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>PARAMETERS</th>" +  
				  				   "<td>" + data.parameters+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>GLOBAL_STATS</th>" +  
				  				   "<td>" + data.global_stats+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>DOMIDX_STATUS</th>" +  
				  				   "<td>" + data.domidx_status+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>DOMIDX_OPSTATUS</th>" +  
				  				   "<td>" + data.domidx_opstatus+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>FUNCIDX_STATUS</th>" +  
				  				   "<td>" + data.funcidx_status+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>JOIN_INDEX</th>" +  
				  				   "<td>" + data.join_index+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>IOT_REDUNDANT_PKEY_ELIM</th>" +  
				  				   "<td>" + data.iot_redundant_pkey_elim+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>DROPPED</th>" +  
				  				   "<td>" + data.dropped+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>VISIBILITY</th>" +  
				  				   "<td>" + data.visibility+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>DOMIDX_MANAGEMENT</th>" +  
				  				   "<td>" + data.domidx_management+"</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  				   "<th scope='row'>SEGMENT_CREATED</th>" +  
				  				   "<td>" + data.segment_created+"</td>" +
				  				"</tr>" +
				  			"</tbody>" +
				  		"</table>";
				  $("#readIndexDiv").append(temp);
			  },error : function(){
				  var temp = "<br><br><h4>인덱스가 조회되지 않습니다.</h4>";
				  $("#readSequenceDiv").append(temp);	
			  }
			});
		}else{
			$.ajax({
				url : "/sqlEditor/readIndexCol"
			  , method : "post"
			  , data : "index_name=" + index_name + "&index_owner=" +table_owner
			  , success : function(data){
				  var temp = "<br><br><table class='table table-hover'>" +
				  			 "<tbody>" +
				  			   "<tr class='table-active'>" +
					  			 	"<th scope='row'>INDEX_OWNER</th>" +
					  			 	"<th scope='row'>INDEX_NAME</th>" +
					  			 	"<th scope='row'>TABLE_OWNER</th>" +
					  			 	"<th scope='row'>TABLE_NAME</th>" +
					  			 	"<th scope='row'>COLUMN_NAME</th>" +
					  			 	"<th scope='row'>COLUMN_POSITION</th>" +
					  			 	"<th scope='row'>DESCEND</th>" +
				  			 	"</tr>" ;
				  for (var i = 0; i < data.length; i++) {
					temp+= "<tr class='table-active'>"+
					"<td>" + data[i].index_owner + "</td>" +
					"<td>" + data[i].index_name + "</td>" +
					"<td>" + data[i].table_owner + "</td>" +
					"<td>" + data[i].table_name + "</td>" +
					"<td>" + data[i].column_name + "</td>" +
					"<td>" + data[i].column_position + "</td>" +
					"<td>" + data[i].descend + "</td>" +
					"</tr>"
				}
				  temp += "</tbody></table>";
				 $("#readIndexDiv").append(temp);
			  },error : function(){
				  var temp = "<br><br><h4>인덱스가 조회되지 않습니다.</h4>";
				  $("#readSequenceDiv").append(temp);	
			  }
			});
		}
	});
	
	// 인덱스 삭제
	$("#deleteIndexSpan").on("click",function(){
		var owner = $("#tableOwner").val().trim();				// DB계정명
		var table_owner= owner.toUpperCase();					// DB계정명 대문자 변경
		var index_name= $("#indexName").val().trim();			// 인덱스명
		
		$.ajax({
			url : "/sqlEditor/indexDelete"
		  , type : "post"
		  , data : "index_owner=" + table_owner + "&index_name=" + index_name
		  , success : function(data){
			  if(data == -99){
				  alert("SQL 오류: ORA-02429: 고유/기본 키 적용을  위한 \n\t인덱스를 삭제할 수 없습니다.");
				  
			  }else{
				  alert("인덱스 삭제에 성공하였습니다.");
				  location.replace("/sqlEditor/sqlEditorMain");
			  }
			  
		  },error : function(){
			  alert("SQL 오류: ORA-02429: 고유/기본 키 적용을  위한 \n\t인덱스를 삭제할 수 없습니다.");
		  }
		})
	})
	
// 인덱스 컬럼 추가
	function appendIndexAjax(){
		var owner = $("#table_owner").val().trim();				// DB계정명
		var table_owner= owner.toUpperCase();					// DB계정명 대문자 변경
		var table_name = $("#td_select").val();
		
		cnt += 1;
		console.log(cnt)
		
		$.ajax({
			url :"/sqlEditor/indexTblCol"
				, type : "post"
					, data : "owner=" + table_owner + "&table_name=" + table_name 
					, success : function(data){
						
						// 컬럼 개수 지정
						var trCnt = $(".tableColumnTr").length;
						if(data.length <= trCnt) {
							alert("테이블에 컬럼을 추가해주세요.");
							return;
						}
						
						var rowIndex = "<tr class='tableColumnTr' id='tableColumnTr"+cnt+"'>" +
						"<td class='selecCol'><select class='form-control' id='tableColumn' name='param_column'>";
						for (var i = 0; i < data.length; i++) {
							rowIndex += "<option value='" + data[i] + "'>" + data[i] + "</option>";
						}
						
						rowIndex += "</select>"+
						"</td>" +
						"<td>"+
						"<select class='form-control' class= 'columnOrder' id='columnOrder' name='param_order'>" +
						"<option value='지정되지 않음'>지정되지 않음</option>"+ 
						"<option value='ASC'>ASC</option>" +
						"<option value='DESC'>DESC</option>"+
						"</select>"
						"<input type='hidden' id = 'trCnt' value='"+trCnt +"'>";
						$("#index_tableBody").append(rowIndex);
						
					}
		})
	}
	

	
	// 인덱스 편집 모달창 띄우기
	$("#updateIndexSpan").on("click",function(){
		
		var owner = $("#tableOwner").val().trim();				// DB계정명
		var table_upperOwner = owner.toUpperCase();					// DB계정명 대문자 변경
		console.log(owner)
		$("#low_owner").val(owner);
		console.log($("#low_owner").val())
		var index_name= $("#indexName").val().trim();			// 인덱스명
		var idx = table_upperOwner.indexOf("_");
		var table_owner = table_upperOwner.substring(0, idx);
		fff = '';
		$("#updateIndex_tableBody").empty();
		console.log(table_upperOwner);
		
		$.ajax({
			url : "/sqlEditor/getTable_name",
			async: false
		  , method: "post"
		  , data : "table_owner=" + table_upperOwner + "&index_name=" + index_name
		  , success : function(data){
			  fff = data[0].table_name;
		  }
		})
		
		$("#updateIndexModal").css("display","block");
		$("#updateIndex").empty();
		
		
		
		cnt += 1;
		
		$.ajax({
		
			url : "/sqlEditor/beforeIndex"
		  , method : "post"
		  , data : "table_owner=" + table_upperOwner + "&index_name=" + index_name + "&table_name=" + fff
		  , dataType : "json"
		  , success : function(data){
			  console.log(data)
			  console.log(data.idxVO[0].index_name)
			  console.log(data.idxVO[0].table_owner)
			  
			  var owner = data.idxVO[0].table_owner;	// db계정명
			  var table_upperOwner = owner.toUpperCase();	// 대문자 변환
			  var idx = table_upperOwner.indexOf("_");	// 구분자
			  var table_owner = table_upperOwner.substring(0, idx);	// 구분자부터 끝까지 짜르기
			  
			  $("#update_owner").val(table_upperOwner);
			  $("#tblOwner").text(table_owner); // db계정명
			  $("#updateIndexName").val(data.idxVO[0].index_name); // 인덱스명
			  $("#selectedTable_name").val(data.idxVO[0].table_name); // 테이블명
			  
			  
			  if(data.tbl_name == "NONUNIQUE"){
				  $("#updateTd_indexType_select option:eq(0)").prop("selected", true);
			  }else if(data.tbl_name == "UNIQUE"){
				  $("#updateTd_indexType_select option:eq(1)").prop("selected", true);
			  }else{
				  $("#updateTd_indexType_select option:eq(2)").prop("selected", true);
			  }
			  
			  var beforeCol = "";
			  
			  for(var i = 0; i < data.idxVO.length; i++){
				  console.log("idxVO[i] : " +   data.idxVO[i].column_name)
				  beforeCol += "<tr class='tableColmunTr' id='updateTableColumnTr"+i +"'>" +
				  "<td class='beforeSelecCol'><select class='form-control update_column' id='update_column' name='update_column'>";
				  for(var j = 0; j < data.col_name.length; j++){
					  if (data.col_name[j] == data.idxVO[i].column_name) {
						  beforeCol +="<option value='"+ data.col_name[j] + "' selected>" + data.col_name[j] + "</option>";
					}else {
						beforeCol +="<option value='"+ data.col_name[j] + "'>" + data.col_name[j] + "</option>";
					}
				  }
				  beforeCol +="</select>";
				  beforeCol +="</td>" +
					  "<td>" +
						  "<select class='form-control updateColumnorder' id='updateColumnorder' name='update_order'>";
					   	if (data.idxVO[i].descend == 'ASC'){ 	
							  beforeCol +="<option value='ASC' selected>ASC</option>";
							  beforeCol +="<option value='DESC' >DESC</option>";
							
						} else if(data.idxVO[i].descend == 'DESC'){
							beforeCol +="<option value='ASC' >ASC</option>";
							beforeCol +="<option value='DESC' selected>DESC</option>";
						}
				  	
				  beforeCol += "</select>" +
					  "</td>" +
				  "</tr>";
				  
			  }
				$("#updateIndex_tableBody").append(beforeCol);
				
			  
		  }
		});
	});
	
	colCnt = 0;
	// 업데이트 인덱스 컬럼 추가
	function updateAppendIndexAjax(){
		var owner = $("#tblOwner").text().trim();				// DB계정명
		var table_owner= owner.toUpperCase();					// DB계정명 대문자 변환
		var table_name = $("#selectedTable_name").val();
		
		
		
		$.ajax({
			url :"/sqlEditor/indexTblCol"
				, type : "post"
					, data : "owner=" + table_owner + "&table_name=" + table_name 
					, success : function(data){
						var optionCnt = $(".update_column").length;
						optionCnt = data.length - optionCnt;
						// 컬럼 개수 지정
						var trCnt = $(".updateTableColumnTr").length;
						if(optionCnt <= trCnt) {
							alert("테이블에 컬럼을 추가해주세요.");
							return;
						}
						var rowIndex = "<tr class='updateTableColumnTr' id='addTableColumnTr"+ colCnt++ +"'>" +
						"<td class='updateSelecCol'><select class='form-control' id='updateColumn' name='update_column'>";
						for (var i = 0; i < data.length; i++) {
							rowIndex += "<option value='" + data[i] + "'>" + data[i] + "</option>";
						}
						rowIndex += "</select>"+
						"</td>" +
						"<td>"+
						"<select class='form-control' class= 'columnOrder' id='columnOrder' name='update_order'>" +
						"<option value='지정되지 않음'>지정되지 않음</option>"+ 
						"<option value='ASC'>ASC</option>" +
						"<option value='DESC'>DESC</option>"+
						"</select>"
						"<input type='hidden' id = 'trCnt' value='"+trCnt +"'>";
						$("#updateIndex_tableBody").append(rowIndex);
						
					}
		});
	}
	
  });



