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
		var seqName = $("#seqName").val();			// 시퀀스명
		var seqStart = $("#seqStart").val();			// 시작값
		var seqIncrement = $("#seqIncrement").val();	// 증분
		var seqMin = $("#seqMin").val();				// 최소값
		var seqMax	 = $("#seqMax").val();				// 최대값
		var seqCache = $("#seqCache").val();			// 캐쉬 
		var inputCache = $("#inputCache").val();		// 캐쉬 입력값
		var seqCycle = $("#seqCycle").val();			// 주기
		var seqOrder = $("#seqOrder").val();			// 정렬
		var accountid = $("#hiddenSeqName").val(); 
		var tempid = accountid.toUpperCase(); // 계정 풀네임
		
		// 시퀀스명 정규식
		var nameReg = /^[a-zA-Z][a-zA-Z0-9#$_]{2,8}$/;
		
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
		if(seqStart.length > 0){
			seqStart = "START WITH " + seqStart;
		}
		
		// 증분 정규식 조건
		if(!numbReg.test(seqIncrement)){
			$("#seqIncrement").val("");
			alert("숫자만 입력 가능합니다.");
			return	
		}
		if(seqIncrement.length > 0){
			seqIncrement = "INCREMENT BY " + seqIncrement;
		}
		
		// 최소값 정규식 조건
		if(!numbReg.test(seqMin)){
			$("#seqMin").val("");
			alert("숫자만 입력 가능합니다.");
			return
		}
		if(seqMin.length > 0){
			seqMin = "MINVALUE " + seqMin;
		}
		
		// 최대값 정규식 조건
		if(!numbReg.test(seqMax)){
			$("#seqMax").val("");
			alert("숫자만 입력 가능합니다.");
			return
		}
		if(seqMax.length > 0){
			seqMax = "MAXVALUE " + seqMax;
		}
		minCache = parseInt(inputCache)
		if(minCache < 1){
			alert("1보다 커야합니다.");
			
		}
		
		// 시작값 최소값 조건
		var start = parseInt(seqStart);
		var min = parseInt(seqMin);
		if(start < min){
			$("#seqStart").val("");
			alert("시작값은 최소값과 같거나 커야 합니다.");
			$("#seqStart").focus();
			return;
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
		alert (query.trim())
		
		$.ajax({
			type : "post"
			,url : "/sqlEditor/createSequence"
			,data : "query="+ query
			,success: function(data){
				if(data == 0){
				alert("생성되었습니다.")
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

	// 모달창 닫기
	$(".close").on("click", function() {
		$("#craeteSequenceModal").css("display", "none");
	});
	
	// 조회창 생성
	$("#readSequenceSpan").on("click", function() {
		$("#selectSequenceModal").css("display", "block");
	});
	
	
	$("#readSequenceSelect").on("changed",function(){
		$("#readSequenceDiv").empty();
		
		var selectVal = $("#readSequenceSelect").val().trim();
		var sequenceOwner = $("#sequenceOwner").val().trim();
		var sequenceName = $("#sequenceName").val().trim();
		if(selectVal == "코드"){
			$.ajax({
				url : "/sqlEditor/readSequenceQuery"
			  , method : "post"
			  , data : "sequenceOwner=" + sequenceOwner + "&sequenceName=" + sequenceName
			  , success : function(data){
				  var temp = "<br><br><h4>" + data + "</h4>";
				  $("#readSequenceDiv").append(temp);
			  }
			})
		}else{
			$.ajax({
				url : "/sqlEditor/readSequenceTable"
			  , method : "post"
			  , data : ""
			})
		}
		
		
	})
});
