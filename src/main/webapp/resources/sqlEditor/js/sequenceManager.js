$(document).ready(function() {

	// 시퀀스 생성 모달창 띄우기
	$("#createsequenceSpan").on("click", function() {
		$("#craeteSequenceModal").css("display", "block");
	});
	
	// 캐시 텍스트 활성/비활성화
	$("#seqCache").on("click",function(){
		if($("#seqCache").val() == "CACHE"){
			$("#inputCache").attr("readonly",false);
		}else {
			$("#inputCache").attr("readonly",true);
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
		
		
		if(!nameReg.test(seqName)){
			$("#sequenceName").val("");
			alert("숫자만 입력 가능합니다.");
			return
		}
		
		// 시작값 정규식 조건
		if(!numbReg.test(seqStart)){
			$("#seqStart").val("");
			alert("숫자만 입력 가능합니다.");
			return
		}
		
		// 증분 정규식 조건
		if(!numbReg.test(seqIncrement)){
			$("#seqIncrement").val("");
			alert("숫자만 입력 가능합니다.");
			return	
		}
		// 최소값 정규식 조건
		if(!numbReg.test(seqMin)){
			$("#seqMin").val("");
			alert("숫자만 입력 가능합니다.");
			return
		}
		// 최대값 정규식 조건
		if(!numbReg.test(seqMax)){
			$("#seqMax").val("");
			alert("숫자만 입력 가능합니다.");
			return
		}
		
		var seqQuery = "";
		if(seqCache.trim().length == 0){
		seqQuery = "CREATE SEQUENCE " + seqName + " " + seqIncrement + " " + seqStart + " "
						+ seqMin + " " + seqMax + " " + seqCache + " " + seqCycle + " " + seqOrder + ";";
		}else{
			seqQuery = "CREATE SEQUENCE " + seqName + " " + seqIncrement + " " + seqStart + " "
			+ seqMin + " " + seqMax + " " + seqCache + " " + inputCache + " " + seqCycle + " " + seqOrder + ";";
		}
		
		$.ajax({
			url : ""
		});
		
		
	});

	// 모달창 닫기
	$(".close").on("click", function() {
		$("#craeteSequenceModal").css("display", "none");
	});
});
