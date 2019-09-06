<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="product.model.vo.*, java.util.*, shop.model.vo.*"%>
<%
	ArrayList<Cart> info = (ArrayList<Cart>) request.getAttribute("info");
	

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#index{
	min-height: 80vh;	
}
img{
	cursor: pointer;
}
</style>
<%@  include file="Form.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/mypage/cart.css">
</head>
<body>
<% if(info.size() !=0 ) {%>
	<div id="Index">
		<div id="mypage">	
			<div>
				<!-- 상품목록 -->
				<p>장바구니</p>
				
				<table class="tab-1">
					<tr>
						<td>이미지</td>
						<td>상품정보</td>
						<td>옵션</td>
						<td width="50px">수량</td>
						<td>상품 금액</td>
						<td>합계</td>
						<td>선택</td>
					</tr>
					<%
						for (int i = 0; i < info.size(); i++) {
							Cart p = info.get(i);
							if (!p.getpOption().equals("없음")) {
								String[] arr = p.getpOption().split(",");
								for (int j = 0; j < arr.length / 2; j++) {
									if (j % 2 == 0) {
					%>
					
					<tr class="deleteone<%=i%>">
						<td>이미지</td>
						<td><%=p.getpName()%></td>
						<%
							for (int k = 0; k < arr.length; k++) {
												if (k % 2 == 0) {
						%>
						<td ><%=arr[k]%></td>
						<td><%=arr[k + 1]%></td>
						<td><%=p.getPrice()%>원</td>
						<td><%=p.getPrice() * p.getAmount()%>원</td>
						<td><br>

							<button class="cartBtn" onclick="deletecart(<%=i%>,<%=p.getCrId() %>);">삭제</button>
							<br>
							</td>
						<%
							break;
												}
											}
						%>
					</tr>

					<%
						}%>
					
					<%}
							} else {
					%>
					<tr class="delete<%=i%>">
						<td>이미지</td>
						<td ><%=p.getpName()%></td>						
						<td><%=p.getpOption()%></td>
						<td><%=p.getAmount()%></td>
						<td><%=p.getPrice()%>원</td>
						<td><%=p.getPrice() * p.getAmount()%>원</td>
						<td><br>
						<button class="cartBtn" onclick=deletecart(<%=i%>,<%=p.getCrId() %>)>삭제</button><br></td>
					</tr>
					<%
						}
						}
					%> 
					
				</table>
			</div>
			<!-- 상품목록 끝 -->
			<br> <br>
			<br>
			<div>
				<!-- 금액  -->
				<table class="tab-2">
					<tr>
						<td>총 상품금액</td>
					</tr>
					<tr>
						<td class="pay1">총 예상 상품 구매가격 =
						<% int totalprice=0;
						for(int i=0; i<info.size();i++){
							Cart p= info.get(i);
							totalprice +=p.getPrice()*p.getAmount();
							}%>
							<%=totalprice%>원</td>
						
					
					</tr>
				</table>
				<!-- 금액 끝 -->
				<br>
				
				<div>
				<form action="<%=request.getContextPath()%>/paylist.ca" method="post">		
				<input type="submit" value="전체상품주문">
				</form>				
				</div>
				
			</div>
			<div>
				<br> <br> 장바구니 이용안내
				<p>해외배송 상품과 국내배송 상품은 함께 결제하실 수 없으니 장바구니 별로 따로 결제해 주시기 바랍니다.</p>
				<p>해외배송 가능 상품의 경우 국내배송 장바구니에 담았다가 해외배송 장바구니로 이동하여 결제하실 수 있습니다.</p>
				<p>장바구니와 관심상품을 이용하여 원하시는 상품만 주문하거나 관심상품으로 등록하실 수 있습니다.</p>
				<p>무이자할부 이용안내</p>
				<p>상품별 무이자할부 혜택을 받으시려면 무이자할부 상품만 선택하여 [주문하기] 버튼을 눌러 주문/결제 하시면
					됩니다.</p>
				<p>[전체 상품 주문] 버튼을 누르시면 장바구니의 구분없이 선택된 모든 상품에 대한 주문/결제가 이루어집니다.</p>
				<p>단, 전체 상품을 주문/결제하실 경우, 상품별 무이자할부 혜택을 받으실 수 없습니다.</p>
			</div>
			
		
		</div>
	</div>
	<%} else{%>
	<div id="index">
		<div id="mypage">
			<div style="display: inline-block">
				<label>장바구니가 비어 있습니다.</label><br> <img src="<%=request.getContextPath() %>/img/mypageImg/empty_cart.jpg" width="1500vw" height="300vh">
			</div>
			<!-- 상품목록 끝 -->
			<br> <br>
			<br>
			<div>
				<!-- 금액  -->
				
				<!-- 금액 끝 -->
				<br>
				
				<div>	
				<button onclick="shop();" id="showbtn">쇼핑하기</button>							
				</div>
				
			</div>
			<div>
				<br> <br> 장바구니 이용안내
				<p>해외배송 상품과 국내배송 상품은 함께 결제하실 수 없으니 장바구니 별로 따로 결제해 주시기 바랍니다.</p>
				<p>해외배송 가능 상품의 경우 국내배송 장바구니에 담았다가 해외배송 장바구니로 이동하여 결제하실 수 있습니다.</p>
				<p>장바구니와 관심상품을 이용하여 원하시는 상품만 주문하거나 관심상품으로 등록하실 수 있습니다.</p>
				<p>무이자할부 이용안내</p>
				<p>상품별 무이자할부 혜택을 받으시려면 무이자할부 상품만 선택하여 [주문하기] 버튼을 눌러 주문/결제 하시면
					됩니다.</p>
				<p>[전체 상품 주문] 버튼을 누르시면 장바구니의 구분없이 선택된 모든 상품에 대한 주문/결제가 이루어집니다.</p>
				<p>단, 전체 상품을 주문/결제하실 경우, 상품별 무이자할부 혜택을 받으실 수 없습니다.</p>
			</div>
			
		
		</div>
	</div>
		<%} %>
	<script>
	function shop(){
		location.href="<%= request.getContextPath()%>/shopMain.do";
	}


		function deletecart(i,caid){
			console.log(i);
			var userId = '<%=loginUser.getId()%>';
			$('.delete'+i).remove();
			$('.deleteone'+i).remove();
		
			
			$.ajax({
				url: "cartdelete.ca",
				type: "get",
				data: {caid:caid,userId:userId},
				success: function(data){
					console.log("송신");
				
					
				}
			
				
				});
			
			location.reload();
			}
	
	
	</script>
	
	
</body>
 <%@ include file="/views/common/coinfooter.jsp"%>
</html>