$(document).ready(function() {
	
	var shiftKey = false;
	$(document).keydown(function(e) {
		if (e.keyCode == 16)					   // shiftKey
			shiftKey = true; 
		if (e.keyCode == 18 && shiftKey == true) { //shiftKey + SpaceBarKey
			shiftKey = false;
			// 구현 내용 작성
//			alert("dd");
			var dragText = editor.getSelectedText();
			
			tmpRun(dragText);
			
		}
	}).keyup(function(e) {
		if (e.keyCode == 16)
			shiftKey = false; // shiftKey
		if (e.keyCode == 18)
			shiftKey = false; // SpaceBarKey
	});
	
	function tmpRun(dragText) {
		if(dragText == "") { // 드래그를 하지 않고 shift + spaceBar를 실행했을 경우
			var aceDocument = editor.getSession().getDocument();// aceEditor의 document객체 구하기
			var cursorPosition = editor.getCursorPosition(); 	// editor의 현재커서 위치 구하기(row, column으로 구성)
			var cursorRow = cursorPosition.row; 				// editor의 커서 위치에서 row만 추출
			var cursorColumn = cursorPosition.column; 			// editor의 커서 위치에서 column만 추출
			var lineText = aceDocument.getLine(cursorRow); 		// 커서위치의 라인에 있는 Text 전체 구하기
			
			var str = lineText.substring(0, cursorColumn);
			
			cursorColumn = parseInt(cursorColumn);
			
//			alert(str.substring(cursorColumn-1, cursorColumn));	//커서위치의 문자열
			
//			for(i=0; i<cursorColumn; i++){
//				if(str.substring(cursorColumn-(i+1), cursorColumn-i)==null || str.substring(cursorColumn-(i+1), cursorColumn-i)==""){
//					alert("공백 문자열 !");
//					
//				}
//			}
			
			// 커서위치를 기준으로 앞의문자열들을 합치기(공백 전까지)
			var utemplate_abb = "";
			for(i=0; i<cursorColumn; i++){
				if(str.substring(cursorColumn-1, cursorColumn) == " "){
					al = "공백에서는 템플릿기능을 제공하지 않습니다.";
					alert(al);
					return;
				} else{
					if(str.substring(cursorColumn-(i+1), cursorColumn-i)== " "){
						break;
					}
					var tmp =  str.substring(cursorColumn-(i+1), cursorColumn-i)
					tmp += utemplate_abb;
					utemplate_abb = tmp;
				}
			}
//			console.log(utemplate_abb);
			
			
			//DB에 사용자가 입력한 약어와 일치하는 원문을 가져오는 AJAX
			$.ajax({
				method : "post",
				url : "/userTemplate/getOriginal",
				data : "utemplate_abb=" + utemplate_abb,
				dataType : "json",
				success : function(data){
					
					if(data.result == "없는 약어입니다."){
						alert("없는 약어입니다.");
					}else{
						console.log(data.result);
						var original = data.result;
						
						editor.removeWordLeft()
						editor.insert(original);
					}
				},
				error : function(error){
					console.log("실패");
				}
			})
		}
	}
})