<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, product.model.vo.Product"%>
<%@ include file="/views/common/coinheader.jsp"%>
<%
	Product p = (Product)request.getAttribute("product");
	Product option = (Product) request.getAttribute("option");
	String[] optionArr = null;
	String[] selected = new String[2];
	if(option != null){
		optionArr = option.getOption().split("/");
		selected[0] = "selected";
	} else {
		selected[1] = "selected";
	}
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
		<%-- 상품정보조회 --%>
		<table class="resultList" style="color: white; width:40vw;">
			<tr>
				<th>상품 명</th>
				<td colspan="2">
					<input id="pId" type="hidden" name="pId" value="<%=p.getpId()%>">
					<input id="pName" class="center" type="text" name="pName" value="<%=p.getpName()%>">
				</td>
			</tr>
			<tr>
				<th>브랜드</th>
				<td colspan="2">
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
				<th>카테고리</th>
				<td colspan="2">
					<select id="categoryNo" name="categoryNo" class="pSelect category">
						<option value="10">스피커</option>
						<option value="20">헤드셋</option>
						<option value="30">헤드폰이어폰</option>
						<option value="40">블루투스사운드</option>
						<option value="50">마이크</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>세부카테고리</th>
				<td colspan="2"><select id="subCate" name="subCategoryNo" class="pSelect"><option>카테고리를 먼저 선택해주세요.</option></select></td>
			</tr>
			<tr>
				<th>가격</th>
				<td colspan="2"><input class="center" id="price" type="number" min="0" step="100" name="price" value="<%=p.getPrice()%>"></td>
			</tr>
			<tr>
				<th>재고</th>
				<td colspan="2"><input class="center" id="stock" type="number" min="0" name="stock" value="<%=p.getStock()%>"></td>
			</tr>
			<tr>
				<th>월 판매량</th>
				<td colspan="2"><%=p.getSellCount()%></td>
			</tr>
			<tr>
				<th>월 판매금액</th>
				<td colspan="2"><%=p.getSellCount() * p.getPrice()%></td>
			</tr>
			<tr>
				<th>상품 등록일</th>
				<td colspan="2"><input id="regDate" class="center" type="text" name="regDate" value="<%=p.getRegDate()%>" readonly></td>
			</tr>
			
			<tr>
				<td><label>옵션</label></td>
				<td>
					<input class="option" name="option" id="optionCheckY" type="radio" value="Y" style="width: 15px; height: 15px; background: white;" <%=selected[0]%>>
					<label>있음</label>
				</td>
				<td>
					<input class="option" name="option" id="optionCheckN" type="radio" value="N" style="width: 15px; height: 15px; background: white;" <%=selected[1]%>>
					<label>없음</label>
				</td>
			</tr>
		
			<% 
			if(optionArr != null) {
				for(int i = 0; i < optionArr.length; i ++) {%>
					<tr class="option<%=i+1%>">
						<td class="option<%=i+1%>Detail"><input id="delOption<%=i+1%>" class="adminButton" type="button" value="옵션 삭제"></td>
						<td class="option<%=i+1%>Detail"><input id="addOption<%=i+1%>" class="adminButton" type="button" value="옵션 추가"></td>
						<td class="option<%=i+1%>Detail"><input id="input_option<%=i+1%>" name="option<%=i+1%>" type="text" placeholder="옵션<%=i+1%>" value="<%=optionArr[i]%>"></td>
					</tr>
			<%	}
				if(optionArr.length == 2){ %>
					<tr class="option3 none">
						<td class="option3Detail"><input id="delOption3" class="adminButton" type="button" value="옵션 삭제"></td>
						<td></td>
						<td class="option3Detail"><input id="input_option3" name="option3" type="text" placeholder="옵션3"></td>
					</tr>
			<%	} else if(optionArr.length == 1) { %>
					<tr class="option2 none">
						<td class="option2Detail"><input id="delOption2" class="adminButton" type="button" value="옵션 삭제"></td>
						<td class="option2Detail"><input id="addOption2" class="adminButton" type="button" value="옵션 추가"></td>
						<td class="option2Detail"><input id="input_option2" name="option2" type="text" placeholder="옵션2"></td>
					</tr>
					<tr class="option3 none">
						<td class="option3Detail"><input id="delOption3" class="adminButton" type="button" value="옵션 삭제"></td>
						<td></td>
						<td class="option3Detail"><input id="input_option3" name="option3" type="text" placeholder="옵션3"></td>
					</tr>
			<%	}
			} else {
			%>
				<tr class="option1 none">
					<td class="option1Detail"><input id="delOption1" class="adminButton" type="button" value="옵션 삭제"></td>
					<td class="option1Detail"><input id="addOption1" class="adminButton" type="button" value="옵션 추가"></td>
					<td class="option1Detail"><input id="input_option1" name="option1" type="text" placeholder="옵션1"></td>
				</tr>
				<tr class="option2 none">
					<td class="option2Detail"><input id="delOption2" class="adminButton" type="button" value="옵션 삭제"></td>
					<td class="option2Detail"><input id="addOption2" class="adminButton" type="button" value="옵션 추가"></td>
					<td class="option2Detail"><input id="input_option2" name="option2" type="text" placeholder="옵션2"></td>
				</tr>
				<tr class="option3 none">
					<td class="option3Detail"><input id="delOption3" class="adminButton" type="button" value="옵션 삭제"></td>
					<td></td>
					<td class="option3Detail"><input id="input_option3" name="option3" type="text" placeholder="옵션3"></td>
				</tr>
			<%}%>
			
			<tr><td colspan="5"><br><br><br><br></td></tr>
			<tr>
				<td><input class="adminButton" type="button" value="삭제" onclick="deleteProduct();"></td>
				<td colspan="2"><input class="adminButton" type="button" value="수정" onclick="update();"></td>
			</tr>
		</table>

		<script>
			var $select = $('.category');
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
			
			function update(){
				var $pId = $('#pId').val();
				var $pName = $('#pName').val();
				var $brandNo = $('#brand').val();
				var $categoryNo = $('#categoryNo').val();
				var $subCate = $('#subCate').val();
				var $price = $('#price').val();
				var $stock = $('#stock').val();
				var $regDate = $('#regDate').val();
				var $option = $('input[name=option]')[0].value;
				console.log($('input[name=option]')[0].value);
				var $input_option1 = $('#input_option1').val();
				var $input_option2 = $('#input_option2').val();
				var $input_option3 = $('#input_option3').val();
				
				$.ajax({
					url: "<%=request.getContextPath()%>/admin.productUpdate",
					type : 'post',
					data : {
						pId : $pId,
						pName : $pName,
						brandNo : $brandNo,
						categoryNo : $categoryNo,
						subCate : $subCate,
						price : $price,
						stock : $stock,
						regDate : $regDate,
						option : $option,
						option1 : $input_option1,
						option2 : $input_option2,
						option3 : $input_option3
					},
					success : function(data) {
						alert("수정되었습니다.");
						history.go(-1);
					},
					error: function(data){
						alert("카테고리를 선택해주세요.");
					}
				});
			}
			function deleteProduct(){
				var check = confirm("정말 삭제하시겠습니까?");
				if(check){
					var $pId = $('#pId').val();
					$.ajax({
						url: "<%=request.getContextPath()%>/admin.productDelete",
						type : 'post',
						data : {pId : $pId},
						success : function(data) {
							alert("삭제되었습니다.");
							history.go(-1);
						}
					});
				} else {
					alert("취소하셨습니다.");
				}
			}
		</script>
		<%
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