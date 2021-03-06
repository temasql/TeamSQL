<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- 폰트 -->
<link href="https://fonts.googleapis.com/css?family=Alegreya+Sans&display=swap" rel="stylesheet">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="${cp}/resources/chatBot/js/chatBotJS.js"></script>
<link href="${cp}/resources/chatBot/css/chatBotStyle.css" rel="stylesheet">
<link href="${cp}/resources/basicLib/css/basicLibStyle.css" rel="stylesheet">
<meta charset="UTF-8">
<title>TeamSQL 챗봇</title>
<head>
</head>
<body>

<section class="chatBotRoom">
  <header class="chatBotHeader">
  	<div class="chatBotHeaderDiv">
  			<span>챗봇</span>
  	</div>
  </header>
  
  <article class="chatBotArticle">
	<div class="boxChatroom">
	  <ul class="chatBotUl">
	  	<li class="liList">
	  	<div class="chatBotBox1">
	  	   <img class="chatBotImg" title="chatBot" alt="chatBot" src="${cp }/resources/img/chatbot.png">
	  	 <div class="chatBotBox2">
	  	   <span class="chatBotSpan">챗봇</span>
	  	  <div class="chatBotResult">
	  	  	<p>안녕하세요! 저는 TeamSQL 챗봇입니다.
	  	  	<br>
		 	무엇을 도와드릴까요?
		 	</p>
	  	  </div>
	  	  </div>
	  	  </div>
	  	</li>
	  </ul>
  	</div>
  </article>
  
	  <footer class="chatBotFooter">
	   <div class="chatBotFooterContainer">
	     <div class="chatBotFooterBox">
	      <button class="questionBtn" id="questionBtn" type="button" class="btn btn-secondary"></button>
	     <textarea class="userInput" id="userInput"></textarea>
	     </div>
	   </div>
	  </footer>
</section>

<div id='questionTable' hidden='hidden'>
<p>테이블에 대하여 궁금하신 점을 질문해주세요.
<br>
아래 버튼을 클릭 하시면 테이블에 대한 정보를 답해드립니다.
<br>
크게 생성과 수정, 삭제가 가능합니다.
<br>
아래 버튼을 클릭하시면 자세한 내용을 확인 하실 수 있습니다.
<br>
<button type="button" class="createTableBtn" id="createTableBtn" >테이블 생성</button>
<button type="button" class="dropTableBtn" id="dropTableBtn" >테이블 삭제</button>
<button type="button" class="updateTableBtn" id="updateTableBtn" >테이블 수정</button>
</p>
</div> 

<!-- 테이블 생성 -->
<div id='tableCreate' hidden="hidden">
<p>
테이블 생성 예제로 알려드리겠습니다.
<br>
-----------------------------------
<br>
CREATE TABLE 테이블명 (
<br>
	컬럼명1 데이터타입() NOT NULL,
<br>
	컬럼명2 데이터타입(),
<br>
	컬럼명3 데이터타입,
<br>
	컬럼명4 데이터타입
<br>
);
<br>
테이블 이름을 지정하고 각 컬럼들은 괄호 "()" 로 묶어 지정합니다.
<br>
컬럼뒤에 데이터 타입은 꼭 지정되어야 하고 null값 여부는 NOT NULL 또는 생략,
<br>
각 컬럼들은 콤마 ","로 구분합니다.
<br>
한 테이블 안에서 컬럼 이름은 중복이 불가능합니다.
</p>
</div>

<!-- 테이블 삭제 -->
<div id='tableDrop' hidden="hidden">
<p>
테이블 삭제하기
<br>
--------------------------------------
<br>
DROP TABLE 테이블명;
<br>
-----------------------------------
<br>
DROP TABLE 테이블명 CASCADE CONSTRAINT;
<br>
외래키에 의해 참조되는 기본키를 포함한 테이블일 경우
<br> 
기본키를 참조하던  외래 키 조건도 같이 삭제 합니다.
</p>
</div>

<!-- 테이블 변경 -->
<div id='tableAlt' hidden="hidden">
<p>
테이블 수정하기
<br>
------ 테이블에 컬럼 추가하기 -----------
<br>
ALTER TABLE 현재 테이블명 RENAME 바꿀 테이블명
<br>
------ 테이블의 컬럼 타입 변경하기 --------
<br>
ALTER TABLE 테이블명 MODIFY COLUMN 컬럼명 컬럼타입
<br>
------ 테이블의 컬럼 이름 변경하기---------
<br>
ALTER TABLE 테이블명 CHANGE COLUMN 현재 컬럼명 바꿀 컬럼명 바꿀 컬럼타입
<br>
------ 테이블의 컬럼 삭제하기------------
<br>
ALTER TABLE 테이블명 DROP COLUMN 컬럼명
<br>
------ 테이블에 PRIMARY KEY 만들기----
<br>
ALTER TABLE 테이블명 ADD PRIMARY KEY(컬럼명 현재테이블)
<br>
------ 테이블에 PRIMARY KEY 삭제하기---
<br>
ALTER TABLE 테이블명 DROP PRIMARY KEY
</p>
</div>

<div id='questionData' hidden='hidden'>
<p>데이터에 대하여 궁금하신 점을 질문해주세요.
<br>
아래 버튼을 클릭 하시면 데이터에 대한 정보를 답해드립니다.
<br>
크게 생성과 수정, 삭제, 조회가 가능합니다.
<br>
아래 버튼을 클릭하시면 자세한 내용을 확인 하실 수 있습니다.
<br>
<button type="button" class="createDataBtn" id="createDataBtn" >데이터 생성</button>
<button type="button" class="dropDataBtn" id="dropDataBtn" >데이터 삭제</button>
<button type="button" class="updateDataBtn" id="updateDataBtn" >데이터 수정</button>
<button type="button" class="searchDataBtn" id="searchDataBtn" >데이터 조회</button>
</p>
</div> 
<!-- 데이터 입력 -->
<div id='dataInsert' hidden="hidden">
<p>
데이터 입력하기
<br>
--------------------------------------
<br>
INSERT INTO 테이블명 ( 컬럼1, 컬럼2, 컬럼3)
<br> 
       VALUES ( '값1', '값2', '값3' );
       <br>
--------------------------------------
<br>
INSERT INTO 테이블명
<br>
       VALUES ( '값1', '값2', '값3' );
       <br>
아래의 INSERT문은 테이블의 모든 컬럼의 값을 입력하는 것입니다.
<br>
3개의 컬럼이 있다고 가정하여 값을 3개를 입력했습니다.
<br>
!! DML(INSERT,DELETE,UPDATE)은 DDL처럼 AUTO COMMIT
<br>
      기능이 없기 때문에 COMMIT 명령어를 입력하여야 합니다.
</p>
</div>

<!-- 데이터 수정 -->
<div id='dataUpdate' hidden="hidden">
<p>
데이터 수정하기
<br>
--------------------------------------
<br>
UPDATE 테이블명
<br>
	   SET 수정할 컬럼명 = '수정 할 값'
	   <br>
WHERE 수정 할 대상 조건 컬럼 = '값'
<br>
--------------------------------------
<br>
!! WHERE 절을 사용하지 않으면 해당 컬럼의 모든 데이터가 변경 됩니다.
<br>
!! DML(INSERT,DELETE,UPDATE)은 DDL처럼 AUTO COMMIT
<br>
기능이 없기 때문에 COMMIT 명령어를 입력하여야 합니다.
</p>
</div>

<!-- 데이터 삭제 -->
<div id='dataDelete' hidden="hidden">
<p>
데이터 삭제하기
<br>
--------------------------------------
<br>
DELETE 테이블명
<br>
WHERE 컬럼 = '삭제할 값'
<br>
--------------------------------------
<br>
FROM 절은 생략이 가능한 키워드이며, WHERE 절을 사용하지 않는다면
<br>
테이블의 전체 데이터가 삭제됩니다.
<br>
!! DML(INSERT,DELETE,UPDATE)은 DDL처럼 AUTO COMMIT
<br>
기능이 없기 때문에 COMMIT 명령어를 입력하여야 합니다.
</p>
</div>

<!-- 데이터 조회 -->
<div id='dataSelect' hidden="hidden">
<p>
데이터 조회하기
<br>
--------------------------------------
<br>
SELECT *
<br>
FROM 테이블명
<br>
--------------------------------------
<br>
SELECT절 뒤에 *를 사용하면 모든 데이터를 조회합니다.
<br>
원하는 컬럼의 데이터를 조회할 때는 SELECT절 뒤에 컬럼명, 컬럼명
<br>
을 사용하시면 됩니다.
</p>
</div>

<!-- 회원 탈퇴 -->
<div id='memOut' hidden="hidden">
<p>
우측 상단에 프로필 사진을 클릭하여 마이페이지 버튼을 클릭합니다.
<img class="chatImg" alt="회원탈퇴1" src="${cp}/resources/img/memOut.png">
<br>
그 후 회원탈퇴 버튼을 클릭하시면 회원탈퇴를 정상적으로 
<br>
완료하실 수 있습니다..
<br>
<img class="chatImg" alt="회원탈퇴2" src="${cp}/resources/img/memOut2.png">
<br> 
</p>
</div>

<!-- SQL 에디터 -->
<div id='sqlEditor' hidden="hidden">
<p>
SQL 에디터 이용 방법을 설명 드리겠습니다.
<br>
상단 메뉴에서 SQL에디터를 클릭 하시면 이용하실 수 있습니다.
<img class="chatImg_sqlEditor" alt="SQL에디터" src="${cp}/resources/img/sqlEditor.png">
<br>
<img id="sqlEditor" alt="SQL에디터" src="${cp}/resources/img/sqlEditor2.png">
</p>
</div>

<!-- DB계정 -->
<div id='owner' hidden="hidden">
<p>
DB계정에 대해 설명 드리겠습니다.
<br>
DB계정 생성은 아래  + 아이콘을 클릭하여 생성하실 수 있습니다.
<img class="editors" alt="SQL에디터" src="${cp}/resources/img/create.png">
<br>
DB계정 , 비밀번호, 채팅방명을 입력후 생성 버튼을 클릭하여 생성을 완료 합니다.
<br>
<img class="editors" alt="SQL에디터" src="${cp}/resources/img/ownerCreate.png">
<br>
DB계정 삭제, PW찾기, PW변경, 팀 일정관리를 사용하실 수 있습니다.
<br>
단 DB계정 삭제, PW찾기, PW변경은 생성자만 가능합니다.
<br>
<img class="rightClick" alt="SQL에디터" src="${cp}/resources/img/editorRightClick.png">
</p>
</div>

<!-- 쿼리 매니저 -->
<div id='qrManager' hidden="hidden">
<p>
쿼리 매니저에 대해 설명 드리겠습니다.
<br>
아래 아이콘이 쿼리 매니저입니다.
<br>
<img class="qr1" alt="쿼리매니저" src="${cp}/resources/img/qrmanager.png">
<br>
쿼리 매니저를 실행하기 전 쿼리를 작성하여 드래그 해주십시오.
<br>
<br>
<img class="qr2" alt="쿼리매니저" src="${cp}/resources/img/qrmanager2.png">
<br>
쿼리매니저가 쿼리를 검사하여 개선 사항 유무를 확인합니다.
<br>
개선 사항이 있을 시 그에 관한 설명과 수정 쿼리를
<br>
에디터에 추가할 수 있습니다.
<br>
<br>
<img class="qr3" alt="쿼리매니저" src="${cp}/resources/img/qrmanager3.png">
</p>
</div>


</body>
</html>