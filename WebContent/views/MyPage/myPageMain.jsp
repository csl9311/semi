	<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko" dir="ltr">
<head>
<meta charset="utf-8">
<title>마이페이지</title>
<script src="../../js/jquery-3.4.1.min.js"></script>
<%@ include file="Form.jsp"%>
<style>
	body{
	
	background: rgb(40, 44, 52);
		
}
#ssee{
min-height:60vh;
}
img{
	cursor: pointer;
}

</style>
</head>
<body>
<div id="ssee">
<div id="mypage">
<!-- 아이콘  -->
<div id="Index">
	  <div style="display:inline-block">
	  <label>개인정보수정</label><br>
      <img src="../../img/mypageImg/personal.png" onclick="update()" width="300px" height="300px">  
      </div>
      <div style="display:inline-block">
	  <label>구매내역</label><br>
      <img src="../../img/mypageImg/Purchase.png" onclick="purchase();" width="300px" height="300px">
      </div>
      <div style="display:inline-block">
	  <label>장바구니</label><br>
      <img src="../../img/mypageImg/cart.png" onclick="cart()" width="300px" height="300px">
      </div><br>
      

  <!-- 아이콘 끝 -->
</div>
</div>
   </div>
   <script>
      function update(){
         location.href="<%=request.getContextPath()%>/views/MyPage/check.jsp";
      }
      function cart(){
         location.href="<%=request.getContextPath()%>/selectcart.ca;
      }
  		function purchase(){
  			location.href="<%=request.getContextPath()%>/selectPayment.do";
	  }

   
   </script>


</body>
   <%@ include file="/views/common/coinfooter.jsp"%>
</html>