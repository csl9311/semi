<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, product.model.vo.Product"%>
<%@ include file="/views/common/coinheader.jsp"%>
<%
	ArrayList<Product> list = (ArrayList<Product>) request.getAttribute("list");
	String alert = (String) request.getAttribute("alert");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품목록</title>
<link rel="stylesheet" href='<%=request.getContextPath()%>/css/admin/admin.css'>
</head>
<body>
	<%
		if (loginUser.getGrade().equals("관리자")) {
	%>
	<div class="emptyHeader"></div>
	<div class="content">
		<div id="productUpdate" class="center none">
			<form action="<%=request.getContextPath()%>/admin.productInsert" method="post">
				<table>
					<tr>
						<td><label>상품명</label></td>
						<td colspan="3" style="width: 300px;"><input name="name" type="text"></td>
					</tr>
					<tr>
						<td><label>브랜드</label></td>
						<td colspan="3">
							<select id="brand" name="brandNo" class="pSelect">
								<option value="10">아이리버</option>
								<option value="20">브리츠</option>
								<option value="30">삼성전자</option>
								<option value="40">ABKO</option>
								<option value="50">APPLE</option>
							</select>
						</td>
					</tr>
					<tr>
						<td><label>카테고리</label></td>
						<td colspan="3">
							<select id="category" name="categoryNo" class="pSelect">
								<option value="10">스피커</option>
								<option value="20">헤드셋</option>
								<option value="30">헤드폰이어폰</option>
								<option value="40">블루투스사운드</option>
								<option value="50">마이크</option>
							</select>
						</td>
					</tr>
					<tr>
						<td><label>상세카테고리</label></td>
						<td colspan="3"><select id="subCate" name="subCategoryNo" class="pSelect"><option>카테고리를 먼저 선택해주세요.</option></select></td>
					</tr>
					<tr>
						<td><label>가격</label></td>
						<td colspan="3"><input name="price" type="number" min="0" step="100"><label>원</label></td>
					</tr>
					<tr>
						<td><label>재고</label></td>
						<td colspan="3"><input name="stock" type="number" min="0"></td>
					</tr>
					<tr>
						<td><label>옵션</label></td>
						<td>
							<input name="option" id="optionCheckY" type="radio" value="Y" style="width: 15px; height: 15px; background: white;">
							<label>있음</label>
						</td>
						<td colspan="2">
							<input name="option" id="optionCheckN" type="radio" value="N" style="width: 15px; height: 15px; background: white;">
							<label>없음</label>
						</td>
					</tr>
					<tr class="option1 none">
						<td class="option1Detail"><label>옵션1</label></td>
						<td class="option1Detail"><input id="input_option1" name="option1" type="text" class="inputOpt"></td>
						<td class="option1Detail"><input id="addOption1" class="adminButton" type="button" value="옵션 추가"></td>
						<td class="option1Detail"><input id="delOption1" class="adminButton" type="button" value="옵션 삭제"></td>
					</tr>
					<tr class="option2 none">
						<td class="option2Detail"><label>옵션2</label></td>
						<td class="option2Detail"><input id="input_option2" name="option2" type="text" class="inputOpt"></td>
						<td class="option2Detail"><input id="addOption2" class="adminButton" type="button" value="옵션 추가"></td>
						<td class="option2Detail"><input id="delOption2" class="adminButton" type="button" value="옵션 삭제"></td>
					</tr>
					<tr class="option3 none">
						<td class="option3Detail"><label>옵션3</label></td>
						<td class="option3Detail"><input id="input_option3" name="option3" type="text" class="inputOpt"></td>
						<td colspan="2" class="option3Detail"><input id="delOption3" class="adminButton" type="button" value="옵션 삭제"></td>
					</tr>
				</table>
				<input class="adminButton" style="margin: 10px;" type="submit" value="등록">
			</form>
		</div>
		<script>
			var $select = $('#category');
			$select.change(function(){
				$.ajax({
					url: "<%=request.getContextPath()%>/product.getSubCategory",
					type : 'post',
					data : {select : $select.val()},
					success : function(data) {
						var $select = $('#subCate');
						$select.find('option').remove();
						for ( var i in data) {
							var $option = $('<option>');
							$option.val(data[i].subCategoryNo);
							$option.text(data[i].subCategory);
							$select.append($option);
						}
					}
				});
			});
		</script>
		<div class="center">
			<button class="center adminButton" id="areaActive_productUpdate">상품 추가</button>
		</div>

		<div class="emptyHeader"></div>
		<%-- 상품정보조회 --%>
		<% if (list == null) { %>
			<h3>등록된 상품정보가 없습니다.</h3>
		<% } else { %>
		<table class="resultList" style="color: white; width: 90vw;">
			<tr>
				<th>상품 명</th>
				<th>브랜드</th>
				<th>카테고리</th>
				<th>세부카테고리</th>
				<th>가격</th>
				<th>재고</th>
				<th>월 판매량</th>
				<th>월 판매금액</th>
				<th>상품 등록일</th>
				<th>옵션유무</th>
				<th>수정</th>
			</tr>
		</table>
		<%
				for (int i = 0; i < list.size(); i++) {
					Product p = list.get(i);
		%>
		<form action="<%=request.getContextPath()%>/admin.productSelect" method="post">
			<table class="resultList" style="color: white; width: 90vw;">
				<tr>
					<td><input type="hidden" name="pId" value="<%=p.getpId()%>"><%=p.getpName()%></td>
					<td><%=p.getBrand()%></td>
					<td><%=p.getCategory()%></td>
					<td><%=p.getSubCategory()%></td>
					<td><%=p.getPrice()%></td>
					<td><%=p.getStock()%></td>
					<td><%=p.getSellCount()%></td>
					<td><%=p.getSellCount() * p.getPrice()%></td>
					<td><%=p.getRegDate()%></td>
					<td><input type="hidden" name="option" value="<%=p.getUseOption()%>"><%=p.getUseOption()%></td>
					<td style="padding-bottom: 10px;"><input class="adminButton" type="submit" value="수정"></td>
				</tr>
			</table>
		</form>
		<%			} // for(list.size) : end
				} // list != null : end
			} else { // !loginUser.equals("관리자");
		%>
		<script>
			alert("관리자 계정으로 로그인해주세요.");
		</script>
		<% } %>
	</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/admin/adminProduct.js"></script>
</body>
</html>