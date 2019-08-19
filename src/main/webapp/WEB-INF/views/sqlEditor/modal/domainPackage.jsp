<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="domainModal" class="modal">
  <div class="modal-content">
  	<span class="close">&times;</span>
  	<br><br>
	  <fieldset>
	    <legend>도메인</legend>
	    <br><br>
	    <div id="domainTableDiv">
		    <table class="table table-hover">
				<colgroup>
					<col width="25%">
					<col width="85%" >
				</colgroup>
				<thead>
					<tr>
						<th scope="col">도메인명</th>
						<th scope="col" class="domainType">데이터 타입</th>
					</tr>
				</thead>
				<tbody id="domainTbody"></tbody>
			</table>
	    </div>
	    
	    <div id="domainBtnDiv">
		    <div id="domainAdd"><button class="btn" style="background: black; color: white;" >추가</button></div>
		    <div id="domainUpdate"><button class="btn" style="background: black; color: white;" >수정</button></div>
		    <div id="domainDelete"><button class="btn" style="background: black; color: white;" >삭제</button></div>
		    <input id="inputDomainId" type="hidden">
		    <input id="inputDomainUpdateName" type="hidden">
		    <input id="inputDomainUpdateType" type="hidden">
	    </div>
  	 </fieldset>
  </div>
</div>


<!-- 도메인 추가 모달창 -->
<div id="domainAddModal" class="modal">
  <div class="modal-content">
  	<span class="addClose">&times;</span>
  	<br><br>
	  <fieldset>
	    <legend>도메인 추가</legend>
	    <br><br>
	    <div id="domainAddView">
	    	<form id="domainFrm">
		    	<div id="Text">도메인명 : <input id="inputName" name="udomain_name" class="form-control"><br>
		    		  데이터 타입 : <input id="inputType" name="udomain_type" class="form-control" >
		    	</div>
	    	</form>
	    </div>
	    
	    <div id="domBtnDiv">
		    <div id="domAdd"><button class="btn" style="background: black; color: white;" >추가</button></div>
		    <div id="domCancel"><button class="btn" style="background: black; color: white;" >취소</button></div>
	    </div>
  	 </fieldset>
  </div>
</div>


<!-- 도메인 수정 모달창 -->
<div id="domainUpdateModal" class="modal">
  <div class="modal-content">
  	<span class="addClose">&times;</span>
  	<br><br>
	  <fieldset>
	    <legend>도메인 수정</legend>
	    <br><br>
	    <div id="domainAddView">
	    	<form id="domainUpdateFrm">
	    		<input type="hidden" id="domId" name="udomain_id">
	    		<div id="Text">도메인명 : <input id="inputUpdateName" name="udomain_name" class="form-control"><br>
		    		  데이터 타입 : <input id="inputUpdateType" name="udomain_type" class="form-control" >
		    	</div>
	    	</form>
	    </div>
	    
	    <div id="domBtnDiv">
		    <div id="domUpdate"><button class="btn" style="background: black; color: white;" >수정</button></div>
		    <div id="domUpdateCancel"><button class="btn" style="background: black; color: white;" >취소</button></div>
	    </div>
  	 </fieldset>
  </div>
</div>