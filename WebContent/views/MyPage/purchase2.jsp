<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="Form.jsp"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/mypage/cart.css">
<style>
.nav {
	margin: auto;
	width: 80%;
	font-size: 20px;
}

.p {
	font-size: small;
}
#index{
	min-height : 70vh;

</style>
</head>
<body>
	<div id="index">
		<div id="mypage">
			<div>
				<ul class="nav nav-pills">
					<li class="nav-item"><a class="nav-link active"
						href="purchase.jsp">구매내역</a></li>
					<li class="nav-item"><a class="nav-link" href="purchase2.jsp">반품,교환내역</a>
					</li>
				</ul>
			</div>
			<div id="pay-div1">
				<p class="p">주문번호를 클릭하시면 해당 주문에 대한 상세내역을 확인하실 수 있습니다.</p>
				<p class="p">정상적으로 결제후 주문취소 및 반품/교환의 경우 게시글로 문의를 남겨주셔야 처리가능합니다.</p>
				<p class="p">위 사항 관련하여, 게시판 문의없이 요청하신 경우 교환/반품처리지연, 출고된 이 후에
					발생되는 배송비는 고객님 부담이오니 교환/반품 접수시에 유의하시기 바랍니다.</p>
				<br> <br>
				<table class="tab-1">
				
					<tr>
						<td>주문번호</td>
						<td>상품</td>
						<td colspan="3">상품정보</td>
						<td width="50px">수량</td>
						<td>상품 금액</td>
						<td>주문처리상태</td>
					</tr>
					<tr>
						<td></td>
						<td><img src=""></td>
						<td colspan="3">z</td>
						<td>1</td>
						<td>10000원</td>
						<td></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
<%@ include file="/views/common/coinfooter.jsp"%>
</html>
