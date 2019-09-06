<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko" dir="ltr">
<head>
<meta charset="utf-8">
<title>마이페이지</title>
<%@ include file="/views/common/coinheader.jsp"%>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/mypage/sub.css">
</head>
<body>
<div id="mypage">

	<div class="search">
		<button class=btn-primary onclick="update()">개인정보수정</button>
		<button class=btn-primary onclick="purchase()">구매내역</button>
		<button class=btn-primary onclick="cart()">장바구니</button>
		
	</div>
	
	<script>
		function update(){
			location.href="<%= request.getContextPath()%>/views/MyPage/check.jsp";
		}
		function cart(){
			location.href="<%= request.getContextPath()%>/selectcart.ca"
		}
		function purchase(){
			location.href="<%= request.getContextPath()%>/views/MyPage/purchase.jsp";
		}
	
	</script>
	</div>
</body>
</html>
