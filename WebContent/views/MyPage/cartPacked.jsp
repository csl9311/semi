<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta content="user-scalable=yes, initial-scale = 1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" name="viewport" />
	<title>장바구니</title>
	<style>
		* {margin: 0;padding: 0;font-size: 12px;-webkit-box-sizing: border-box;-moz-box-sizing: border-box;box-sizing: border-box;list-style: none;border: none;background: none;}
		table{border-collapse: collapse; width: 100%;}
		#wrap {width: 300px;margin: 0 auto;padding: 10px;padding-bottom: 10px;}
		#header {margin-bottom: 20px;}
		#header h1,#header h2 {text-align: center;}
		#wrap .center {text-align:center; margin-top:10px;}
		.btns {text-align: center; margin-top:10px;}
		.mt20 {margin-top:20px;}

	</style>
	<script src="../../js/jquery-3.4.1.min.js"></script>
</head>

<body>

<div id="wrap">
	<div class="center">
		<p><img src="../../img/mypageImg/cartpacked.png" alt="선택한 상품이 장바구니에 담겼습니다."/></p>
		<div class="mt20">
			<a href="javascript:list_view();" class="btn_no"><img src="../../img/mypageImg/btn_go_shop.png" alt="쇼핑 계속하기"/></a>&nbsp; 
			<a href="javascript:cart_view();"><img src="../../img/mypageImg/btn_go_basket.png" alt="장바구니 가기"/></a>
		</div>
	</div>
</div>
	<script>
		jQuery(document).ready(function(){
			
		});

		function list_view(){
			opener.parent.location.reload();
			window.close();
		}
		function cart_view(){
			location.href="<%= request.getContextPath()%>/selectcart.ca";
			window.close();
		}
	</script>

</body>
</html>