$(document).ready(function() {
	
	// 전송버튼 클릭 이벤트
	$("#questionBtn").on('click',function(){

		// textArea 입력값 가져오기
		var userQuestion= $("#userInput").val();
		
		var question = userQuestion.replace(/(?:\r\n|\r|\n)/g, '<br />');
		var defined = "제 전문 분야가 아니에요.<br>저는 TeamSQL관련 서비스 관련 도움을 드리고 있습니다.";
		// user입력 textArea 출력하기
		
		var questionInput = $(".liList").append("<div class='chatBotBox1'><div class='userBox2'><div class='userResult'><p class='pchatBot'>"+question+"</p></div></div></div>")
		
		// 테이블 생성
		var questionTable= $("#questionTable").find('p').html();
		// 테이블 생성
		var tableCreate= $("#tableCreate").find('p').html();
		// 테이블 삭제
		var tableDrop= $("#tableDrop").find('p').html();
		// 테이블 수정
		var tableAlt= $("#tableAlt").find('p').html();
		
		// 데이터 
		var questionData= $("#questionData").find('p').html();
		// 데이터 입력
		var dataInsert= $("#dataInsert").find('p').html();
		// 데이터 수정
		var dataUpdate= $("#dataUpdate").find('p').html();
		// 데이터 삭제
		var dataDelete= $("#dataDelete").find('p').html();
		// 데이터 조회
		var dataSelect= $("#dataSelect").find('p').html();
		
		// 회원탈퇴
		var memOut= $("#memOut").find('p').html();
		
		// DB계정
		var owner= $("#owner").find('p').html();
		
		// SQL에디터
		var sqlEditor= $("#sqlEditor").find('p').html();
		
		// 쿼리매니저
		var qrManager= $("#qrManager").find('p').html();
		
		// 공백 제거
		var gap = question.split(' ').join('');
		// 소문자 변환
		var str = gap.toLowerCase();
		
		// 질의응답
		if(str =='테이블' || str== 'table'){
			$(".liList").append("<div class='chatBotBox1'><div></div><img class='chatBotImg' title='chatBot' alt='chatBot' src='/resources/img/chatbot.png'><div class='chatBotBox2'><span class='chatBotSpan'>챗봇</span><div class='chatBotResult'><p class='pchatBot'>"+questionTable+"</p></div></div></div>")
		}
		// 테이블 생성 비교문
		else if(str== 'table생성' || str== 'table생성하기' || str== 'table생성하는방법' || str== 'table생성하는법' 
			|| str== 'table만들기' || str== 'table만드는법' || str== 'table만드는방법' || str== '테이블생성' 
			|| str== '테이블생성하기' || str == '테이블생성하는방법' || str == '테이블생성하는법' || str == '테이블만들기'
			|| str == '테이블만드는법' || str == '테이블만드는방법'
			)
			// 같을 경우
			$(".liList").append("<div class='chatBotBox1'><div></div><img class='chatBotImg' title='chatBot' alt='chatBot' src='/resources/img/chatbot.png'><div class='chatBotBox2'><span class='chatBotSpan'>챗봇</span><div class='chatBotResult'><p class='pchatBot'>"+tableCreate+"</p></div></div></div>")
			// 테이블 삭제 비교문
		else if(str== 'table삭제' || str == 'table삭제하기' || str == 'table삭제하는방법' || str == 'table삭제하는법'
				|| str == 'table지우기' || str == 'table지우는법' || str == 'table지우는방법' || str == '테이블삭제' 
				|| str == '테이블삭제하기' || str == '테이블삭제하는법' || str == '테이블삭제하는방법' || str == '테이블지우기'
				|| str == '테이블지우는법' || str == '테이블 지우는방법'
				)
				// 같을 경우
			$(".liList").append("<div class='chatBotBox1'><div></div><img class='chatBotImg' title='chatBot' alt='chatBot' src='/resources/img/chatbot.png'><div class='chatBotBox2'><span class='chatBotSpan'>챗봇</span><div class='chatBotResult'><p class='pchatBot'>"+tableDrop+"</p></div></div></div>")
				
		// 테이블 변경 비교문
		else if(str== 'table변경' || str == 'table변경하기' || str == 'table변경하는법' || str == 'table변경하는방법'
				|| str == 'table수정' || str == 'table수정하기' || str == 'table수정하는법' || str == 'table수정하는방법'
				|| str == '테이블변경' || str == '테이블변경하기'|| str == '테이블변경하는법'|| str == '테이블변경하는방법'
				|| str == '테이블수정' || str == '테이블수정하기'|| str == '테이블수정하는법'|| str == '테이블수정하는방법'
				)
			// 같을 경우
			$(".liList").append("<div class='chatBotBox1'><img class='chatBotImg' title='chatBot' alt='chatBot' src='/resources/img/chatbot.png'><div class='chatBotBox2'><span class='chatBotSpan'>챗봇</span><div class='chatBotResult'><p class='pchatBot'>"+tableAlt+"</p></div></div></div>")

		//데이터
		else if(str == '데이터' || str== 'data' ){
			$(".liList").append("<div class='chatBotBox1'><img class='chatBotImg' title='chatBot' alt='chatBot' src='/resources/img/chatbot.png'><div class='chatBotBox2'><span class='chatBotSpan'>챗봇</span><div class='chatBotResult'><p class='pchatBot'>"+questionData+"</p></div></div></div>")
		}			
		// 데이터 입력
		else if(str== 'data입력' || str== 'data입력하기' || str== 'data입력하는법' || str== 'data입력하는방법'
				|| str== 'data넣기' || str== 'data넣는법' || str== 'data넣는방법' || str== '데이터입력'
				|| str== '데이터입력하기' || str== '데이터입력하는법' || str== '데이터입력하는방법' || str== '데이터넣기'
				|| str== '데이터넣는법' || str== '데이터넣는방법'
				)
			// 같을 경우
			$(".liList").append("<div class='chatBotBox1'><img class='chatBotImg' title='chatBot' alt='chatBot' src='/resources/img/chatbot.png'><div class='chatBotBox2'><span class='chatBotSpan'>챗봇</span><div class='chatBotResult'><p class='pchatBot'>"+dataInsert+"</p></div></div></div>")
			
		// 데이터 수정
		else if(str== 'data수정' || str== 'data수정하기' || str== 'data수정하는법' || str== 'data수정하는방법'
				|| str== 'data변경' || str== 'data변경하기' || str== 'data변경하는법' || str== 'data변경하는방법' 
				|| str== '데이터변경' || str== '데이터변경하기' || str== '데이터변경하는법' || str== '데이터변경하는방법'
				|| str== '데이터수정' || str== '데이터수정하기' || str== '데이터수정하는법' || str== '데이터수정하는방법'
				)
			// 같을 경우
			$(".liList").append("<div class='chatBotBox1'><img class='chatBotImg' title='chatBot' alt='chatBot' src='/resources/img/chatbot.png'><div class='chatBotBox2'><span class='chatBotSpan'>챗봇</span><div class='chatBotResult'><p class='pchatBot'>"+dataUpdate+"</p></div></div></div>")
			
		// 데이터 삭제
		else if(str== 'data삭제' || str== 'data삭제하기' || str== 'data삭제하는법' || str== 'data삭제하는방법'
				|| str== 'data지우기' || str== 'data지우는법' || str== 'data지우는방법' 
				|| str== '데이터삭제' || str== '데이터삭제하기' || str== '데이터삭제하는법' || str== '데이터삭제하는방법'
				|| str== '데이터지우기' || str== '데이터지우는법' || str== '데이터지우는방법'
				)
			// 같을 경우
			$(".liList").append("<div class='chatBotBox1'><img class='chatBotImg' title='chatBot' alt='chatBot' src='/resources/img/chatbot.png'><div class='chatBotBox2'><span class='chatBotSpan'>챗봇</span><div class='chatBotResult'><p class='pchatBot'>"+dataDelete+"</p></div></div></div>")
			
		// 데이터 조회
		else if(str== 'data조회' || str== 'data조회하기' || str== 'data조회하는법' || str== 'data조회하는방법'
				|| str== 'data찾기' || str== 'data찾는법' || str== 'data찾는방법' 
				|| str== '데이터조회' || str== '데이터조회하기' || str== '데이터조회하는법' || str== '데이터조회하는방법'
				|| str== '데이터찾기' || str== '데이터찾는법' || str== '데이터찾는방법'
				)
			// 같을 경우
			$(".liList").append("<div class='chatBotBox1'><img class='chatBotImg' title='chatBot' alt='chatBot' src='/resources/img/chatbot.png'><div class='chatBotBox2'><span class='chatBotSpan'>챗봇</span><div class='chatBotResult'><p class='pchatBot'>"+dataSelect+"</p></div></div></div>");

		// 회원 탈퇴
		else if(str== '회원탈퇴' || str== '회원삭제' || str== '회원탈퇴하기' || str== '회원탈퇴하는법'
			|| str== '회원탈퇴하는방법' || str== '회원삭제하기' || str== '회원삭제하는법'|| str== '회원삭제하는방법'
				|| str== '탈퇴' || str== '탈퇴하기' || str== '탈퇴하는법' || str== '탈퇴하는방법')
			// 같을 경우
			$(".liList").append("<div class='chatBotBox1'><img class='chatBotImg' title='chatBot' alt='chatBot' src='/resources/img/chatbot.png'><div class='chatBotBox2'><span class='chatBotSpan'>챗봇</span><div class='chatBotResult'><p class='pchatBot'>"+memOut+"</p></div></div></div>");
		// SQL 에디터
		else if(str== 'sql에디터' || str== 'SQL에디터사용' || str== 'SQL에디터이용' || str== '에스큐엘에디터'
			|| str== '에스큐엘에디터사용' || str== '에스큐엘에디터이용' || str== 'sql')
			// 같을 경우
			$(".liList").append("<div class='chatBotBox1'><img class='chatBotImg' title='chatBot' alt='chatBot' src='/resources/img/chatbot.png'><div class='chatBotBox2'><span class='chatBotSpan'>챗봇</span><div class='chatBotResult'><p class='pchatBot'>"+sqlEditor+"</p></div></div></div>");
		
		// 쿼리 매니저
		else if(str.includes("query") == true || str.includes("쿼리") == true )
			// 같을 경우
			$(".liList").append("<div class='chatBotBox1'><img class='chatBotImg' title='chatBot' alt='chatBot' src='/resources/img/chatbot.png'><div class='chatBotBox2'><span class='chatBotSpan'>챗봇</span><div class='chatBotResult'><p class='pchatBot'>"+qrManager+"</p></div></div></div>");
		
		// DB계정
		else if(str== '계정' || str== 'db계정' || str== '디비계정')
			// 같을 경우
			$(".liList").append("<div class='chatBotBox1'><img class='chatBotImg' title='chatBot' alt='chatBot' src='/resources/img/chatbot.png'><div class='chatBotBox2'><span class='chatBotSpan'>챗봇</span><div class='chatBotResult'><p class='pchatBot'>"+owner+"</p></div></div></div>");
		
		else{
			$(".liList").append("<div class='chatBotBox1'><img class='chatBotImg' title='chatBot' alt='chatBot' src='/resources/img/chatbot.png'><div class='chatBotBox2'><span class='chatBotSpan'>챗봇</span><div class='chatBotResult'><p class='pchatBot'>"+defined+"</p></div></div></div>");
		}
			

		// textArea 비우기
		$("#userInput").val("");
		
		// 테이블 생성 버튼
		$(".createTableBtn").click(function(){
			$(".liList").append("<div class='chatBotBox1'><div></div><img class='chatBotImg' title='chatBot' alt='chatBot' src='/resources/img/chatbot.png'><div class='chatBotBox2'><span class='chatBotSpan'>챗봇</span><div class='chatBotResult'><p class='pchatBot'>"+tableCreate+"</p></div></div></div>");
			$(".chatBotArticle").scrollTop($(".chatBotArticle")[0].scrollHeight);
		})
		
		// 테이블 삭제 버튼
		$(".dropTableBtn").click(function(){
			$(".liList").append("<div class='chatBotBox1'><div></div><img class='chatBotImg' title='chatBot' alt='chatBot' src='/resources/img/chatbot.png'><div class='chatBotBox2'><span class='chatBotSpan'>챗봇</span><div class='chatBotResult'><p class='pchatBot'>"+tableDrop+"</p></div></div></div>")
			$(".chatBotArticle").scrollTop($(".chatBotArticle")[0].scrollHeight);
		})
		
		// 테이블 변경 버튼
		$(".updateTableBtn").click(function(){
			$(".liList").append("<div class='chatBotBox1'><img class='chatBotImg' title='chatBot' alt='chatBot' src='/resources/img/chatbot.png'><div class='chatBotBox2'><span class='chatBotSpan'>챗봇</span><div class='chatBotResult'><p class='pchatBot'>"+tableAlt+"</p></div></div></div>")
			$(".chatBotArticle").scrollTop($(".chatBotArticle")[0].scrollHeight);
		})
		
		// 데이터 생성 버튼
		$(".createDataBtn").click(function(){
			$(".liList").append("<div class='chatBotBox1'><img class='chatBotImg' title='chatBot' alt='chatBot' src='/resources/img/chatbot.png'><div class='chatBotBox2'><span class='chatBotSpan'>챗봇</span><div class='chatBotResult'><p class='pchatBot'>"+dataInsert+"</p></div></div></div>")
			$(".chatBotArticle").scrollTop($(".chatBotArticle")[0].scrollHeight);
		})
		
		// 데이터 수정 버튼
		$(".updateDataBtn").click(function(){
			$(".liList").append("<div class='chatBotBox1'><img class='chatBotImg' title='chatBot' alt='chatBot' src='/resources/img/chatbot.png'><div class='chatBotBox2'><span class='chatBotSpan'>챗봇</span><div class='chatBotResult'><p class='pchatBot'>"+dataUpdate+"</p></div></div></div>")
			$(".chatBotArticle").scrollTop($(".chatBotArticle")[0].scrollHeight);
		})
		
		// 데이터 삭제 버튼
		$(".dropDataBtn").click(function(){
			$(".liList").append("<div class='chatBotBox1'><img class='chatBotImg' title='chatBot' alt='chatBot' src='/resources/img/chatbot.png'><div class='chatBotBox2'><span class='chatBotSpan'>챗봇</span><div class='chatBotResult'><p class='pchatBot'>"+dataDelete+"</p></div></div></div>")
			$(".chatBotArticle").scrollTop($(".chatBotArticle")[0].scrollHeight);
		})
		
		// 데이터 조회 버튼
		$(".searchDataBtn").click(function(){
			$(".liList").append("<div class='chatBotBox1'><img class='chatBotImg' title='chatBot' alt='chatBot' src='/resources/img/chatbot.png'><div class='chatBotBox2'><span class='chatBotSpan'>챗봇</span><div class='chatBotResult'><p class='pchatBot'>"+dataSelect+"</p></div></div></div>");
			$(".chatBotArticle").scrollTop($(".chatBotArticle")[0].scrollHeight);
		})
		
		
		// 스크롤 자동 하단 이동
		$(".chatBotArticle").scrollTop($(".chatBotArticle")[0].scrollHeight);
	});
	
	// 엔터키 이벤트 전송
	
	$('#userInput').keypress(function(event){
	     if ( event.which == 13 ) {
	         $('#questionBtn').click();
	         return false;
	     }
	});
	
	
	
});

