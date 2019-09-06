<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="product.model.vo.*, member.model.vo.*, java.util.*, shop.model.vo.*, payment.model.vo.*"%>
<%@ include file="Form.jsp"%>	
<%	
	request.setCharacterEncoding("UTF-8");
	ArrayList<Cart> pay = (ArrayList<Cart>) request.getAttribute("pay");
	Address adr = (Address)request.getAttribute("adr");
	String phone = loginUser.getPhone();
	int point= loginUser.getPoint();
	String name = loginUser.getName();
	System.out.println(loginUser.getName());
	String email= loginUser.getEmail();

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js" type="text/javascript"></script>
<style>
.menu{
	text-align: right;
}
.payadr {
	
	width: 30vw;
}

.payphone {
	width: 5vw;
}

.delrequest {
	width: 30vw;
}
#index{
	min-height: 110vh;	
}
</style>
</head>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/mypage/cart.css">
<body>
    <div id="index">
	<div id="mypage">
		<!-- 상품목록 -->
		<div>
		<table class="tab-1">
					<tr>
						<td>이미지</td>
						<td>상품정보</td>
						<td>옵션</td>
						<td width="50px">수량</td>
						<td>상품 금액</td>
						<td>합계</td>

					</tr>
					<%
						for (int i = 0; i < pay.size(); i++) {
							Cart p = pay.get(i);
							if (!p.getpOption().equals("없음")) {
								String[] arr = p.getpOption().split(",");
								for (int j = 0; j < arr.length / 2; j++) {
									if (j % 2 == 0) {
					%>					
					<tr>
						<td>이미지</td>
						<td >
						<input id="firstName<%=i%>" type="hidden" value="<%=p.getpName()%>">
						<%=p.getpName()%></td>
						<%
							for (int k = 0; k < arr.length; k++) {
												if (k % 2 == 0) {
						%>
						<td><%=arr[k]%></td>
						<td><%=arr[k + 1]%></td>
						<td><%=p.getPrice()%>원</td>
						<td><%=p.getPrice() * p.getAmount()%>원</td>

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
					<tr>
						<td>이미지</td>
						<td><%=p.getpName()%></td>						
						<td><%=p.getpOption()%></td>
						<td><%=p.getAmount()%></td>
						<td><%=p.getPrice()%>원</td>
						<td><%=p.getPrice() * p.getAmount()%>원</td>
					</tr>
					<%
						}
						}
					%> 
				</table>
			</div>
		<br> <br>
		<!-- 주문자 정보 -->
		<div id="pay-div1" style="text-align:left">
			<br>
			<p>주문 정보</p>
			<table class="pay-tab">
				<tr>
					<td class="menu">주문하시는 분&nbsp;&nbsp;&nbsp;</td>
					<td><input type="text" id="name" style="width: 10vw" value="<%=name%>"></td>
				</tr>
				<tr>
					<td class="menu">주소&nbsp;&nbsp;&nbsp;</td>
					<td><input type="text" class="payadr" value="<%=adr.getPostNum()%>" readonly>&nbsp;&nbsp;&nbsp;
				</tr>
				<tr>
					<td></td>
					<td><input type="text" class="payadr" value="<%=adr.getRoadAddress()%>" readonly></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="text" class="payadr" value="<%=adr.getJibunAddress()%>" readonly></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="text" class="payadr" value="<%=adr.getAddress_detail()%>" readonly></td>
				</tr>
				<tr>
					<td class="menu">휴대전화&nbsp;&nbsp;&nbsp;</td>
					<td><input type="text" name="phone" id="phone" value="<%=phone %>"></td>
				</tr>
				<tr>
					<td class="menu">이메일&nbsp;&nbsp;&nbsp;</td>
					<td><input type="text" name="email1" id="email1" value="<%=email %>">
				</tr>
			</table>
			

			<!-- 주문자정보 끝 -->
			<br> <br>
			<!-- 배송지 정보 -->
			
			<p>배송지 정보</p>
			<table class="pay-tab">
				<tr>
					<td class="menu"></td>
					<td><input id="butn" type="button" value="주문자 정보와 동일">&nbsp;&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td class="menu">받는사람&nbsp;&nbsp;&nbsp;</td>
					<td><input type="text" name="recipient" id="recipient" style="width: 10vw" value=""></td>					
				</tr>
				<tr>
					<td class="menu">주소&nbsp;&nbsp;&nbsp;</td>
					<td><input type="text" class="payadr" id="postNum">
						<button>주소 찾기</button></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="text" class="payadr" id="roadAddress"></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="text" class="payadr" id="jibunAddress"></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="text" class="payadr" id="address_detail"></td>
				</tr>
				<tr>
					<td class="menu">휴대전화&nbsp;&nbsp;&nbsp;</td>
					<td><input type="text" name="recipientPhone" id="recipientPhone" value=""></td>
				</tr>
				
				<tr>
					<td class="menu">배송시 요청사항&nbsp;&nbsp;&nbsp;</td>
					<td><input type="text" class="delrequest" id="req" value=""><br>
					<label>ex)경비실에 맡겨주세요</label></td>
				</tr>
			</table>
		
			<!-- 배송지정보 끝 -->
			
		</div>

		<div id="pay-div1">
			<br>
			<!-- 할인,포인트 -->
			<p style="text-align:left; font-size:larger">할인 및 포인트</p>
			<table>
				<tr>
					<td>포인트 사용</td>
					<td><input type="text" id="po">원</td>
					<td><input type="button" onclick="all();" value="전액사용"></td>
					<td>(보유 포인트 : <%=point %> 포인트)</td>
				</tr>
			</table>
			<!-- 할인 및 포인트 끝 -->
		</div>
		<div id="pay-div1">
			<!-- 결제정보 -->
			<br> <br>
			<p style="text-align:left; font-size:larger">결제 정보</p>
			<table class="pay-tab">
				<tr>
					<td>총 주문 금액</td>
					<td>포인트 사용</td>
					<td>총 결제예정 금액</td>
				</tr>
				<tr>
					<td class="menu">					
					<%int total = 0;
					for (int i = 0; i < pay.size(); i++) {
							Cart p = pay.get(i);
							total += p.getPrice()*p.getAmount();						
						}%>
						<%= total %>원
						<input id="total" type="hidden" value="<%= total %>"></td>
					<td class="menu">원</td>
					<td class="menu">=원</td>
				</tr>
			</table>
			<br>
			<button id="payment">결제하기</button>
		</div>
		<!-- 결제정보끝 -->
		
	</div>
	</div>
	
	<script>
	$('#butn').click(function(){
		$('#recipient').attr('value', '<%=name%>');
		$('#recipientPhone').attr('value', '<%=phone%>');
		$('#postNum').attr('value', '<%=adr.getPostNum()%>');
		$('#roadAddress').attr('value', '<%=adr.getRoadAddress()%>');
		$('#jibunAddress').attr('value', '<%=adr.getJibunAddress()%>');
		$('#address_detail').attr('value', '<%=adr.getAddress_detail()%>');
	});
	function all(){
		$('#po').attr('value', "<%=point%>");		
	}

var list = "<%for(int i = 0 ; i < pay.size() ; i ++) {
		Cart p = pay.get(i);
		if(i < pay.size()-1){
	%><%=p.getCrId()%>,<%
		} else {
	%><%=p.getCrId()%><%
		}
	}%>";
	console.log(list);

	var IMP = window.IMP; // 생략가능
	IMP.init('imp03747157');
	
	var name= $('#firstName0').val();
	var total= $('#total').val();
	var totalamount =$('#totalamount').val();
	
	<%-- $('#payment').click(function(){
		var recipient = $('#recipient').val();
		var recipienPhone = $('#recipientPhone').val();
		var address1 = $('#postNum').val();
		var address2 = $('#roadAddress').val();
		var address3 = $('#jibunAddress').val();
		var address4 = $('#address_detail').val();		
		var address = address1 +" " + address2 + " "+ address3 + " "+ address4;
		var req = document.getElementById('req').value;
		console.log(address);
		console.log(recipient);
		console.log(req);
		var ship = recipient +","+ recipienPhone +","+ address +","+ req;
		
	
		location.href="<%=request.getContextPath()%>/purchase.ca?list="+list+"&ship="+ship;
	}); --%>
	
	$('#payment').click(function(){
	IMP.request_pay({
	    pg : 'inicis', // version 1.1.0부터 지원.
	    pay_method : 'card',
	    merchant_uid : 'merchant_' + new Date().getTime(),
	    name : name,
	    amount : '100',
	    buyer_email : '<%=loginUser.getEmail()%>',
	    buyer_name : '<%=loginUser.getName()%>외',
	    buyer_tel : '<%=loginUser.getPhone()%>',
	    buyer_addr : '서울특별시 강남구 삼성동',
	    buyer_postcode : '123-456',
	    m_redirect_url : 'https://www.yourdomain.com/payments/complete'
	}, function(rsp) {
	    if ( rsp.success ) {
	    	var recipient = $('#recipient').val();
			var recipienPhone = $('#recipientPhone').val();
			var address1 = $('#postNum').val();
			var address2 = $('#roadAddress').val();
			var address3 = $('#jibunAddress').val();
			var address4 = $('#address_detail').val();		
			var address = address1 +" " + address2 + " "+ address3 + " "+ address4;
			var req = document.getElementById('req').value;
			console.log(address);
			console.log(recipient);
			console.log(req);
			var ship = recipient +","+ recipienPhone +","+ address +","+ req;
			
		
			
	        var msg = '결제가 완료되었습니다.';
	        msg += '결제 금액 : ' + rsp.paid_amount;
	        
	        location.href="<%=request.getContextPath()%>/purchase.ca?list="+list+"&ship="+ship;
	        
	        
	    } else {
	        var msg = '결제에 실패하였습니다.';
	        msg += '에러내용 : ' + rsp.error_msg;
	    }
	    alert(msg); 
		});
	});
	
	
	</script>
</body>
<%@ include file="/views/common/coinfooter.jsp"%>
</html>
