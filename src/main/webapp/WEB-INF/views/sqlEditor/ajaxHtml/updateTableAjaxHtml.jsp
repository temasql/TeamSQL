<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<br><br>
   <a href="#" id="updateAppendData">플러스 이미지</a> <!-- 동적생성해야함 -->
   <a href="#" id="updateRemoveData">마이너스 이미지</a><!-- 동적생성해야함 -->
   <table class="table table-hover">
	<thead id="tableUpdateThead">
		<tr> <!-- 동적 생성해야함 -->
				<th scope="col">PK</th>
				<th scope="col">이름</th>
				<th scope="col">데이터 유형</th>
				<th scope="col">크기</th>
				<th scope="col">널이 아님</th>
				<th scope="col">기본 값</th>
				<th scope="col">설명</th>
			</tr>
		</thead>
		<tbody id="tableUpdateTbody">
<%-- 		<c:forEach items="${updateTable.columnList.dataList}" end="data"> --%>
			<tr>
			 	<td><input  name='colPKChecked' class='col' value='false' type='checkbox'></td>
			 	<td><input  name='colName' class='tableManagerText colName col' type='text'/> </td>
				<td>
					<select class='tableManagerSelectBox col'>
					<!-- 데이터 타입 받는 리스트 추가해야함 -->
<%-- 						<c:forEach items="${update.selectBox }" var="selectBox"> --%>
							<option value='${select}'>${select}</option>
<%-- 						</c:forEach> --%>
					</select>
				</td>
				<td><input name='colSize' class='tableManagerText col' type='text'/> </td>
				<td><input name='colNullCheck' class='col' value='false' type='checkbox'></td>
				<td><input name='colDefaultVal' class='tableManagerText col' type='text'/> </td>
				<td><input name='colComment' class='tableManagerText col' type='text'/> </td>
			</tr>
<%-- 		</c:forEach> --%>
		</tbody>
</table>