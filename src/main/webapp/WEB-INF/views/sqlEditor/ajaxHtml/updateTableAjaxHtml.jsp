<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<br><br>
   <img class="plustBtn" id="updateAppendData" style="margin : 5px;" src="${cp}/resources/img/add.png"/>&nbsp;&nbsp;
  <img class="minusBtn" id="updateRemoveData" src="${cp}/resources/img/delete.png"/>
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
		<c:set value="100" var="i"></c:set>
		<c:forEach items="${columnDataList}" var="columnData">
			<tr class='tableDataTr' id='tableDataTr${i}'>
			<c:set value="${i+1}" var="i"></c:set>
				<c:choose>
					<c:when test="${columnData.pk eq 'true'}">
					 	<td><input  name='colPKChecked' class='col' value='${columnData.pk }' type='checkbox' checked></td>
					</c:when>
					<c:otherwise>
					 	<td><input  name='colPKChecked' class='col' value='${columnData.pk }' type='checkbox'></td>
					</c:otherwise>
			 	</c:choose>
			 	<td><input  name='colName' class='tableManagerText colName col' value='${columnData.column_name }' type='text'/> </td>
				<td>
					<select class='tableManagerSelectBox col'>
					<!-- 데이터 타입 받는 리스트 추가해야함 -->
					<c:forEach items="${dataTypeList }" var="select">
						<c:choose>
							<c:when test="${select eq columnData.data_type }">
								<option value='${select}' selected>${select}</option>
							</c:when>				
							<c:otherwise>
								<option value='${select}'>${select}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					</select>
				</td>
				<td><input name='colSize' class='tableManagerText col' value='${columnData.data_length }' type='text'/> </td>
				<c:choose>
					<c:when test="${columnData.nullable eq 'Y'}">
						<td><input name='colNullCheck' class='col' value='false' type='checkbox'></td>
					</c:when>
					<c:otherwise>
						<td><input name='colNullCheck' class='col' value='true' type='checkbox' checked></td>
					</c:otherwise>
			 	</c:choose>
				<c:choose>
					<c:when test="${columnData.data_default eq null}">
						<td><input name='colDefaultVal' class='tableManagerText col' type='text'/> </td>
					</c:when>
					<c:otherwise>
						<td><input name='colDefaultVal' class='tableManagerText col' value='${columnData.data_default}' type='text'/> </td>
					</c:otherwise>
			 	</c:choose>
				<c:choose>
					<c:when test="${columnData.comments eq null}">
						<td><input name='colComment' class='tableManagerText col' type='text'/> </td>
					</c:when>
					<c:otherwise>
						<td><input name='colComment' class='tableManagerText col' value='${columnData.comments}' type='text'/> </td>
					</c:otherwise>
			 	</c:choose>
			</tr>
		</c:forEach>
		</tbody>
</table>
