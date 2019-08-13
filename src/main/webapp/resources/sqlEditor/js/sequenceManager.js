
$(document).ready(function() {

	// 시퀀스 생성 모달창 띄우기
	$("#createsequenceSpan").on("click", function() {
		$("#craeteSequenceModal").css("display", "block");
	});
	
	// 캐시 텍스트 활성/비활성화
	$("#seqCache").on("click",function(){
		if($("#seqCache").val() == "CACHE"){
			$("#inputCache").attr("readonly",false);
			$("#inputCache").val("20");
		}else {
			$("#inputCache").attr("readonly",true);
			$("#inputCache").val("");
		}
	})
	
	// 시퀀스 생성
	$(document).on("click","#createSeqBtn",function(){
		// 입력 값
		var seqName = $("#seqName").val();				// 시퀀스명
		var seqStart = $("#seqStart").val();			// 시작값
		var seqIncrement = $("#seqIncrement").val();	// 증분
		var seqMin = $("#seqMin").val();				// 최소값
		var seqMax	 = $("#seqMax").val();				// 최대값
		var seqCache = $("#seqCache").val();			// 캐쉬 
		var inputCache = $("#inputCache").val();		// 캐쉬 입력값
		var seqCycle = $("#seqCycle").val();			// 주기
		var seqOrder = $("#seqOrder").val();			// 정렬
		var accountid = $("#hiddenSeqName").val(); 		// 계정명
		var tempid = accountid.toUpperCase();			// 계정 풀네임
		// 시퀀스명 정규식
		var nameReg = /^[a-zA-Z][a-zA-Z0-9#$_]{2,19}$/;
		
		// 음수가능 숫자 정규식
		var numbReg = /^-?[0-9]*$/;
		
		// 시퀀스명 널값 체크
		if(seqName.trim().length <= 0){
			alert("시퀀스명을 입력해주세요.");
			$("#seqName").focus();
			return
		}
		
		// 시퀀스명 정규식 조건
		if(!nameReg.test(seqName)){
			$("#seqName").val("");
			alert("잘못 입력되었습니다.");
			$("#seqName").focus();
			return
		}
		
		// 시작값 정규식 조건
		if(!numbReg.test(seqStart)){
			$("#seqStart").val("");
			alert("숫자만 입력 가능합니다.");
			return
		}
		// 시작값 숫자 치환
		var start = parseInt(seqStart);
		
		// 값이 있으면 쿼리 추가
		if(seqStart.length > 0){
			seqStart = "START WITH " + seqStart;
		}
		
		// 증분 정규식 조건
		if(!numbReg.test(seqIncrement)){
			$("#seqIncrement").val("");
			alert("숫자만 입력 가능합니다.");
			return	
		}
		
		// 값이 있으면 쿼리 추가
		if(seqIncrement.length > 0){
			seqIncrement = "INCREMENT BY " + seqIncrement;
		}
		
		// 최소값 정규식 조건
		if(!numbReg.test(seqMin)){
			$("#seqMin").val("");
			alert("숫자만 입력 가능합니다.");
			return
		}
		
		// 최소값 숫자 치환
		var min = parseInt(seqMin);
		
		// 값이 있으면 쿼리 추가
		if(seqMin.length > 0){
			seqMin = "MINVALUE " + seqMin;
		}
		
		// 최대값 정규식 조건
		if(!numbReg.test(seqMax)){
			$("#seqMax").val("");
			alert("숫자만 입력 가능합니다.");
			return
		}
		
		// 최대값 숫자 치환
		var max = parseInt(seqMax);
		
		// 값이 있으면 쿼리 추가
		if(seqMax.length > 0){
			seqMax = "MAXVALUE " + seqMax;
		}
		
		if(!numbReg.test(inputCache)){
			$("#inputCache").val("");
			alert("숫자만 입력 가능합니다.");
			return
		}
		// 캐시값 숫자 치환
		var minCache = parseInt(inputCache)
		if(minCache < 1){
			alert("1보다 커야합니다.");
			
		}
		
		// 시작값 최소값 조건
		
		if(start < min){
			$("#seqStart").val("");
			alert("시작값은 최소값과 같거나 커야 합니다.");
			$("#seqStart").focus();
			return;
		}
		
		// 최소값 최대값 조건
		if(min > max){
			alert("최소값은 최대값보다 클 수 없습니다.")
		}
		// 시퀀스 생성 쿼리문
		var query = "";
		if(seqCache.trim().length == 0){
			query = "CREATE SEQUENCE " + '"'+ tempid + '"' + "." + '"' + seqName + '"' + " " + seqIncrement + " " + seqStart + " "
						+ seqMin + " " + seqMax + " " + seqCache + " " + seqCycle + " " + seqOrder 
		}else{
			query = "CREATE SEQUENCE " + '"'+ tempid +'"' + "." + '"' + seqName + '"' + " " + seqIncrement + " " + seqStart + " "
			+ seqMin + " " + seqMax + " " + seqCache + " " + inputCache + " " + seqCycle + " " + seqOrder 
		}
		
		$.ajax({
			type : "post"
			,url : "/sqlEditor/createSequence"
			,data : "query="+ query
			,success: function(data){
				if(data == 0){
				alert("생성되었습니다.")
				location.replace("/sqlEditor/sqlEditorMain");
				$("#craeteSequenceModal").css("display", "none");
				}else{
					alert("시퀀스명이 중복됩니다.")
					return
				}
			}
			,error : function(data){
				
			}
		});
		
	});

	// 시퀀스 생성 모달창 닫기
	$(".close").on("click", function() {
		$("#craeteSequenceModal").css("display", "none");
		$("#selectSequenceModal").css("display", "none");
		$("#updateSequenceModal").css("display", "none");
	});
	
	// 조회창 생성
	$("#readSequenceSpan").on("click", function() {
		$("#selectSequenceModal").css("display", "block");
		
		$("#readSequenceDiv").empty();	// div 비우기
		
		var selectVal = $("#readSequenceSelect").val().trim();		// 선택된 옵션
		var owner = $("#sequenceOwner").val().trim();				// DB계정명		
		var sequence_owner= owner.toUpperCase();					// DB계정명 대문자 치환
		var sequence_name= $("#sequenceName").val().trim();			// 시퀀스명
		
		$.ajax({
			url : "/sqlEditor/readSequenceQuery"
		  , method : "post"
		  , data : "sequence_owner=" + sequence_owner + "&sequence_name=" + sequence_name
		  , success : function(data){
			  var temp = "<br><br><h4>" + data.trim() + "</h4>";
			  $("#readSequenceDiv").append(temp);
			  
		  },error : function(data){
			  var temp = "<br><br><h4>시퀀스가 없거나 잘못 입력되었습니다.</h4>";
			  $("#readSequenceDiv").append(temp);	
		  }
		});
	});
	
	// 시퀀스 조회 셀렉박스 change 이벤트
	$("#readSequenceSelect").on("change",function(){
		$("#readSequenceDiv").empty();
		
		var selectVal = $("#readSequenceSelect").val().trim();		// 선택된 옵션
		var owner = $("#sequenceOwner").val().trim();				// DB계정명
		var sequence_owner = owner.toUpperCase();					// DB계정명 대문자 치환
		var sequence_name= $("#sequenceName").val().trim();			// 시퀀스명
		
		if(selectVal == "코드"){
			$.ajax({
				url : "/sqlEditor/readSequenceQuery"
			  , method : "post"
			  , data : "sequence_owner=" + sequence_owner + "&sequence_name=" + sequence_name
			  , success : function(data){
				  var temp = "<br><br><h4>" + data.trim() + "</h4>";
				  $("#readSequenceDiv").append(temp);
				  
			  },error : function(data){
				  var temp = "<br><br><h4>시퀀스가 없거나 잘못 입력되었습니다.</h4>";
				  $("#readSequenceDiv").append(temp);	
			  }
			})
		}else{
			$.ajax({
				url : "/sqlEditor/readSequenceTable"
			  , method : "post"
			  , data : "sequence_name=" + sequence_name + "&sequence_owner=" + sequence_owner
			  , success : function(data){
				  var temp = "<br><br><table class='table table-hover'>"+
				  			"<tbody>" +
			  					"<tr class='table-active'>" +
				  					"<th scope='row'>CREATED</th>" +
				  					"<td>" + data.created + "</td>" +
			  					"</tr>" +
				  				"<tr class='table-active'>" +
				  					"<th scope='row'>LAST_DDL_TIME</th>" +
				  					"<td>" + data.last_ddl_time + "</td>" +
				  				"</tr>" +
				  				"<tr class='table-active'>" +
				  					"<th scope='row'>SEQUENCE_OWNER</th>" +
				  					"<td>" + data.sequence_owner+ "</td>" +
			  					"</tr>" +
			  					"<tr class='table-active'>" +
				  					"<th scope='row'>SEQUENCE_NAME</th>" +
				  					"<td>" + data.sequence_name + "</td>" +
			  					"</tr>" +
			  					"<tr class='table-active'>" +
				  					"<th scope='row'>MIN_VALUE</th>" +
				  					"<td>" + data.min_value + "</td>" +
			  					"</tr>" +
			  					"<tr class='table-active'>" +
				  					"<th scope='row'>MAX_VALUE</th>" +
				  					"<td>" + data.max_value + "</td>" +
			  					"</tr>" +
			  					"<tr class='table-active'>" +
			  						"<th scope='row'>INCREMENT_BY</th>" +
			  						"<td>" + data.increment_by + "</td>" +
			  					"</tr>" +
			  					"<tr class='table-active'>" +
			  						"<th scope='row'>CYCLE_FLAG</th>" +
			  						"<td>" + data.cycle_flag + "</td>" +
			  					"</tr>" +
			  					"<tr class='table-active'>" +
			  						"<th scope='row'>ORDER_FLAG</th>" +
			  						"<td>" + data.order_flag + "</td>" +
			  					"</tr>" +
			  					"<tr class='table-active'>" +
			  						"<th scope='row'>CACHE_SIZE</th>" +
			  						"<td>" + data.cache_size + "</td>" +
			  					"</tr>" +
			  					"<tr class='table-active'>" +
			  						"<th scope='row'>LAST_NUMBER</th>" +
			  						"<td>" + data.last_number + "</td>" +
			  					"</tr>" +
			  				"</tbody>" +
			  			"</table>";
				  	$("#readSequenceDiv").append(temp);	
			  },error : function(data){
				  var temp = "<br><br><h4>시퀀스가 없거나 잘못 입력되었습니다.</h4>";
				  $("#readSequenceDiv").append(temp);	
			  }
			});
		}
		
	})
	
		// 시퀀스 편집 모달창 띄우기
		$("#updateSequenceSpan").on("click", function() {
			$("#updateSequenceModal").css("display", "block");
			$("#updateSequence").empty();
			
			var owner = $("#sequenceOwner").val().trim();				// DB계정명
			var sequence_owner = owner.toUpperCase();					// DB계정명 대문자 치환
			var sequence_name= $("#sequenceName").val().trim();			// 시퀀스명
			var idx = sequence_owner.indexOf("_");
			owner = sequence_owner.substring(0, idx); 
			
			$.ajax({
				url : "/sqlEditor/beforeSequence"
			  , method : "post"
			  , data : "sequence_owner=" + sequence_owner + "&sequence_name=" + sequence_name
			  , success : function(data){
				  var temp= "<div class='form-group'>" +
				  "<label id='updateSeqSchema'>"+ owner +"</lable>" + 
				  "<input type='text' class='form-control' id='updateSeqName' name='updateSeqName' value='"+
				  data.sequence_name+"'>" +
				  "<small class='form-text text-muted' id='updateTableNameHint'>영문으로 시작하여 특수문자(#,$,_)포함 3~20글자 사이입니다.</small>"+
				  "</div>" +
				  "<label id='updateLblOption'>속성</label>" +
				  "<input type='text' class='form-control' id='updateSeqIncrement' name='updateSeqIncrement' value='"+
				  data.increment_by + "'>" + "<input type='hidden' id='hidden_number' value='"+ data.last_number + "'>" +					  
				  "<input type='text' class='form-control' id='updateSeqMin' name='updateSeqMin' value='"+
				  data.min_value + "'>" +					  
				  "<input type='text' class='form-control' id='updateSeqMax' name='updateSeqMax' value='"+
				  data.max_value + "'>" +
				  "<label for='from-control' id='updateLblCache'>캐시</label>" +
				  "<select class='form-control' id='updateSeqCache' name='updateSeqCache'>";
				  if(data.cache_size > 0){
					  temp += "<option id ='cache' value='CACHE' selected='selected'>캐시</option><option id='nocahe' value='NOCACHE'>캐시없음</option></select>" +
					  "<input type='text' class='form-control' id='updateInputCache' name='updateInputCache' value='"+data.cache_size+"'>";
					  
				  }else{
					  temp += "<option id ='cache' value='CACHE'>캐시</option><option id='nocache' selected='selected' value='NOCACHE'>캐시없음</option></select>" +
					  "<input type='text' class='form-control' id='updateInputCache' name='updateInputCache' readonly>";
				  }
				  temp +="<label for='form-control'>주기</label>" +
				  "<select class='form-control' id='updateSeqCycle' name='updateSeqCycle'>";
				  if(data.cycle_flag == "Y"){
					  temp += "<option id='cycle' value='CYCLE' selected='selected'>주기</option><option id='nocycle' value='NOCYCLE'>주기없음</option></select>";
				  }else{
					  temp += "<option id='cycle' value='CYCLE'>주기</option><option id='nocycle' value='NOCYCLE' selected='selected'>주기없음</option></select>";
				  }
				  temp += "<label for='form-control'>정렬</label>"+
				  "<select class='form-control' id='updateSeqOrder' name='updateSeqOrder'>";
				  if(data.order_flag == "Y"){
					  temp += "<option id='order' value='ORDER' selected='selected'>정렬</option><option id='noorder' value='NOORDER'>정렬없음</option></select><br>";
				  }else{
					  temp += "<option id='order' value='ORDER' >정렬</option><option id='noorder' selected='selected' value='NOORDER'>정렬없음</option></select><br>";
				  }
				  
				  $("#updateSequence").append(temp);
				  
				  
				  $("#updateSeqCache").on("click",function(){
					  if($("#updateSeqCache").val() == "CACHE"){
						  $("#updateInputCache").attr("readonly",false);
						  $("#updateInputCache").val("20");
					  }else {
						  $("#updateInputCache").attr("readonly",true);
						  $("#updateInputCache").val("");
					  }
				  })
				  
			  }
			})
		});
	
	// 시퀀스 편집 버튼 클릭
	$("#updateSeqBtn").on("click",function(){
		
		// 입력 값
		var seqName = $("#updateSeqName").val();			// 시퀀스명
		var seqIncrement = $("#updateSeqIncrement").val();	// 증분
		var seqMin = $("#updateSeqMin").val();				// 최소값
		var seqMax	 = $("#updateSeqMax").val();			// 최대값
		var seqCache = $("#updateSeqCache").val();			// 캐쉬 
		var inputCache = $("#updateInputCache").val();		// 캐쉬 입력값
		var seqCycle = $("#updateSeqCycle").val();			// 주기
		var seqOrder = $("#updateSeqOrder").val();			// 정렬
		
		// 시퀀스명 정규식
		var nameReg = /^[a-zA-Z][a-zA-Z0-9#$_]{2,19}$/;
		
		// 음수가능 숫자 정규식
		var numbReg = /^-?[0-9]*$/;
		
		// 시퀀스명 널값 체크
		if(seqName.trim().length <= 0){
			alert("시퀀스명을 입력해주세요.");
			$("#seqName").focus();
			return;
		}
		
		// 시퀀스명 정규식 조건
		if(!nameReg.test(seqName)){
			$("#updateSeqName").val("");
			alert("잘못 입력되었습니다.");
			$("#updateSeqName").focus();
			return;
		}
		
		// 증분 정규식
		if(!numbReg.test(seqIncrement)){
			seqIncrement.val("");
			alert("숫자만 입력 가능합니다.");
			return;
		}
		if(seqIncrement == 0){
			alert("증분값을 입력해주세요.");
		}
		if(seqIncrement.trim().length >0){
			seqIncrement = "INCREMENT BY " + seqIncrement;
		}
		
		// 최소값 정규식
		if(!numbReg.test(seqMin)){
			seqMin.val("");
			alert("숫자만 입력 가능합니다.");
			return;
		}
		if(seqMin == 0){
			alert("최소값을 입력해주세요.");
		}
		var min = parseInt(seqMin);
		if(seqMin.trim().length){
			seqMin = "MINVALUE " + seqMin;
		}
		
		// 최대값 정규식
		if(!numbReg.test(seqMax)){
			seqMax.val("");
			alert("숫자만 입력 가능합니다.");
			return;
		}
		var max = parseInt(seqMax);
		if(seqMax.trim().length){
			seqMax = "MAXVALUE " + seqMax;
		}
		
		// 캐시값 정규식
		if(!numbReg.test(inputCache)){
			inputCache.val("");
			alert("숫자만 입력 가능합니다.");
			return;
		}
		var hiddenVal = $("#hidden_number").val();
		// 최소값 최대값 조건
		
		var currval = parseInt(hiddenVal);
		
		if(min >= max){
			alert("최소값은 최대값보다 작아야 합니다.");
			return;
		}
		if(currval < min){
			alert("현재값 미만의 값을 줄 수 없습니다. 현재 값 : " + currval)
			return;
		}

		var query ="";
		
		if(currval = min){
		query = "ALTER SEQUENCE " + seqName + " " + seqIncrement + " "+ seqMax + " " + seqCache + " " +
					inputCache + " " + seqCycle + " " + seqOrder ;
		
		}else if(currval > min){
			query = "ALTER SEQUENCE " + seqName + " " + seqIncrement + " " +
			seqMin + " " + seqMax + " " + seqCache + " " + inputCache + " " + seqCycle + " " + seqOrder;
		}
		
		$.ajax({
			type : "post"
		  , url : "/sqlEditor/updateSequence"
		  , data : "query=" + query
		  , success : function(data){
			  if(data == 0){
				  alert("변경되었습니다.");
				  $("#updateSequenceModal").css("display", "none");
				  location.replace("/sqlEditor/sqlEditorMain");
			  }else{
				  alert("시퀀스명이 중복됩니다.");
				  return;
			  }
		  },error : function(data){
			  
		  }
		});
	});
	
	// 시퀀스 삭제
	$("#deleteSequenceSpan").on("click",function(){
		
		var owner = $("#sequenceOwner").val().trim();				// DB계정명
		var sequence_owner = owner.toUpperCase();					// DB계정명 대문자 치환
		var sequence_name= $("#sequenceName").val().trim();			// 시퀀스명
		
		$.ajax({
			type : "post"
		  , url : "/sqlEditor/deleteSequence"
		  , data : "sequence_owner=" + sequence_owner + "&sequence_name=" + sequence_name
		  , success : function(data){
			  alert("시퀀스 삭제에 성공하였습니다.");
			  location.replace("/sqlEditor/sqlEditorMain");
		  },error : function(){
			  alert("시퀀스 삭제에 실패하였습니다.");
		  }
		})
	})
	
	
});
