<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="templateModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
  	<span class="close">&times;</span>
  	<br><br>
	  <fieldset>
	    <legend>템플릿</legend>
	    <br><br>
	    <div id="templateTableDiv">
		    <table class="table table-hover">
				<colgroup>
					<col width="25%">
					<col width="85%" >
				</colgroup>
				<thead>
					<tr>
						<th scope="col">약어</th>
						<th scope="col" class="tmpOriginal">원문</th>
					</tr>
				</thead>
				<tbody id="templateTbody"></tbody>
			</table>
	    </div>
	    
	    <div id="templateBtnDiv">
		    <div id="templateAdd"><button class="btn btn-secondary" style="background: black; color: white;">추가</button></div>
		    <div id="templateUpdate"><button class="btn btn-secondary" style="background: black; color: white;">수정</button></div>
		    <div id="templateDelete"><button class="btn btn-secondary" style="background: black; color: white;">삭제</button></div>
		    <input id="inputTmpId" type="hidden">
		    <input id="inputTmpUpdateAbb" type="hidden">
		    <input id="inputTmpUpdateOriArea" type="hidden">
	    </div>
  	 </fieldset>
  </div>
</div>


<!-- template 추가 모달창 -->
<div id="templateAddModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
  	<span class="addClose">&times;</span>
  	<br><br>
	  <fieldset>
	    <legend>템플릿 추가</legend>
	    <br><br>
	    <div id="tempAddView">
	    	<form id="templateFrm">
		    	<div id="Text">약어 : <input id="inputAbb" name="utemplate_abb" class="form-control" placeholder="약어 입력란(영문만 가능)"  onkeyup="this.value=this.value.replace(/[\ㄱ-ㅎㅏ-ㅣ가-힣-; , 0-9]/g, '');"><br>
		    		  원문 : <textarea id="inputOriArea" name="utemplate_original" class="form-control" rows="5" placeholder="원문 입력란(영문만 가능)" onkeyup="this.value=this.value.replace(/[\ㄱ-ㅎㅏ-ㅣ가-힣-; , 0-9]/g, '');"></textarea>
		    	</div>
	    	</form>
	    </div>
	    
	    <div id="tempBtnDiv">
		    <div id="tempAdd"><button class="btn btn-secondary" style="background: black; color: white;">추가</button></div>
		    <div id="tempCancle"><button class="btn btn-secondary" style="background: black; color: white;">취소</button></div>
	    </div>
  	 </fieldset>
  </div>
</div>


<!-- template 수정 모달창 -->
<div id="templateUpdateModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
  	<span class="addClose">&times;</span>
  	<br><br>
	  <fieldset>
	    <legend>템플릿 수정</legend>
	    <br><br>
	    <div id="tempAddView">
	    	<form id="templateUpdateFrm">
	    		<input type="hidden" id="tempId" name="utemplate_id">
		    	<div id='Text'>약어 : <input id='inputUpdateAbb' name='utemplate_abb' class='form-control' onkeyup="this.value=this.value.replace(/[\ㄱ-ㅎㅏ-ㅣ가-힣-; , 0-9]/g, '');" placeholder="약어 입력란"><br>
		    	 원문 : <textarea id='inputUpdateOriArea' name='utemplate_original' class='form-control' rows='5' placeholder='원문 입력란'></textarea>
		    	 </div>
	    	</form>
	    </div>
	    
	    <div id="tempBtnDiv">
		    <div id="tempUpdate"><button class="btn btn-secondary" style="background: black; color: white;">수정</button></div>
		    <div id="tempUpdateCancle"><button class="btn btn-secondary" style="background: black; color: white;">취소</button></div>
	    </div>
  	 </fieldset>
  </div>
</div>