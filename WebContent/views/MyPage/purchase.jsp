<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.sun.xml.internal.bind.v2.runtime.Location, 
		member.model.vo.*, shop.model.vo.*, java.util.ArrayList,
		payment.model.vo.*"%>
<%
	ArrayList<Payment> pList = (ArrayList<Payment>)request.getAttribute("payList");
	ArrayList<PAttachment> picList = (ArrayList<PAttachment>)request.getAttribute("picList");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="Form.jsp"%>
<link rel="stylesheet"	href="<%=request.getContextPath()%>/css/mypage/cart.css">
<style>
.nav {
	margin: auto;
	width: 80%;
	font-size: 20px;
}

.p {
	font-size: small;
}
#empty{
	height: 30vh;
}
#index{
	min-height : 70vh;
}
img{
	width: 100px; height: 100px;
}
p{
	margin-top: 3%; 
}
button{
	background-color: rgb(210,210,210); 
	border: none;
	height: 30px;
	font-weight: 800;
	color: rgb(40,40,40);
}
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
						<td>상품평등록</td>
					</tr>
						<% 
						int i = 0;
						for(Payment p : pList) { 
						%>
							
					<tr>
						<td class="oNo">
						<%=p.getoNo() %>
						<input type="hidden" value="<%=p.getpId()%>">
						</td>
						<% for(PAttachment pic : picList) {
							if(pic.getpId() == p.getpId()){ %>
							<td><img class="pic" src="<%=pic.getFilePAth()%>"></td>
						<% }}%>
						<td colspan="3">
						<p class="pName"><%=p.getpName()%><p>
						<% if(p.getpOption() != null) {%>
						<p class="pOption" id="pOption<%=i%>">
						<%=p.getpOption()%></p>
						<%} %>
						</td>	
						<td class="pAmount"><%=p.getAmount()%></td>
						<td class="pPrice"><%=(int)p.getAmount()*p.getPrice()%></td>
						<td class="pHistory"><%=p.getHistory()%></td>
						<td><button onclick="review();">상품평 등록</button></td>
					</tr>
					<% i++; }%>
				</table>
			</div>
		</div>
	</div>
	<script>
	function review(){
		var oNo;	// 장바구니 번호
		var pId; // 상품 아이디
		var pName; // 상품명
		var pic; // 사진
		var pOption;  // 옵션
		var e = $(event.target).parent(); // 맨 마지막 td
		
		console.log(e);
		
		oNo = $(e).prevAll().eq(5).text().trim();
		pName = $(e).prevAll().find('.pName').text();
		pic = $(e).prevAll().find('.pic').attr('src');
		pOption = $(e).prevAll().find('.pOption').text().trim();
		pId = $(e).prevAll().find('input').val();
		
		console.log("pId : " +  pId);
		console.log("oNo : " +  oNo);
		console.log(pName);
		console.log(pic);
		console.log("pOption : " + pOption);
		
		window.open("<%=request.getContextPath()%>/views/shop/InsertReviewView.jsp?pName="+pName+"&pic="+pic+"&pOption="+pOption+"&pId="+pId,
				"상품평 입력창","width=600px, height=680px, left=500, top=50");
	}
	</script>
	

</body>
<%@ include file="/views/common/coinfooter.jsp"%>
</html>

