<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="member.model.vo.*"%>
<%
	Member loginUser = (Member) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>메인페이지</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/main/header.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.4.1.min.js"></script>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/main/loginForm.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/main/main.css">
<link rel="stylesheet"
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
<!-- //트위터 아이콘 -->
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!--부트스트랩  -->
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
</head>


<body>
	<div id="main">
		<div id="mySidenav" class="sidenav">
			<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
			<a href="<%=request.getContextPath()%>/list.ko">코인노래방
				찾기</a> <a href="<%=request.getContextPath()%>/shopMain.do">쇼핑몰</a> <a
				href="<%=request.getContextPath()%>/list.bo">영상게시판</a> <a
				href="<%=request.getContextPath()%>/list.tb">자유게시판</a>
			<%-- 로그인 시 --%>
			<%
				if (loginUser != null) {
			%>
			<a href="<%=request.getContextPath()%>/views/MyPage/myPageMain.jsp">마이페이지</a>
			<%-- 관리자 로그인 시 --%>
			<%
				if (loginUser.getGrade().equals("관리자")) {
			%>
			<a href="<%=request.getContextPath()%>/views/admin/adminMain.jsp">관리자페이지</a>
			<%
				}
				}
			%>
		</div>

		<header>
			<nav class="navbar navbar-fixed-top" style="z-index: 121;">
				<span style="font-size: 30px; cursor: pointer" onclick="openNav()"
					class=" top left">&#9776; Menu</span>
				<div class="right">
					<%
						if (loginUser != null) {
					%>
					<span
						style="font-size: 20px; cursor: pointer; position: absolute; right: 50px; top: 12px; width: 10vw;"
						class="login" onclick="logout();">로그아웃</span>
					<div class="profile"
						onclick='location.href="<%=request.getContextPath()%>/views/MyPage/myPageMain.jsp"'></div>
					<script>
						function logout(){
							$.ajax({
								url: '<%=request.getContextPath()%>/member.logout',
								type : 'get',
								success : function(data) {
									alert("로그아웃되었습니다.");
									location.href = "<%=request.getContextPath()%>/index.jsp";
										}
									});
						}
					</script>
					<%
						} else {
					%>
					<span
						style="font-size: 20px; cursor: pointer; position: absolute; top: 12px; right: 4px; width: 10vw;"
						class="login" data-toggle="modal" data-target="#login-modal">로그인</span>
					<%
						}
					%>
				</div>
				<%-- 로고 --%>
				<div class="logo"
					onclick="location.href='<%=request.getContextPath()%>/views/common/mainPage.jsp'"
					style="cursor: pointer; display: inline-block;width:80px;height:50px;opacity:0.8;">
					<img src="<%=request.getContextPath()%>/img/cmboard/singsing.png"
						style=" top: 0; left: 0; width:80px;height:50px" />
				</div>
			</nav>

			<!--******************로그인폼 ***************************  -->
			<form action="<%=request.getContextPath()%>/member.login"
				method="post" onsubmit="return validate();">
				<div class="modal fade" id="login-modal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true"
					style="display: none;">
					<div class="modal-dialog" style="text-align: center">
						<div class="loginmodal-container">
							<h1>로그인</h1>
							<br> <input type="text" name="userId" id="userId"
								placeholder="아이디"> <input type="password" name="userPwd"
								id="userPwd" placeholder="비밀번호"> <input type="submit"
								name="login" class="button loginmodal-submit" value="로그인">
							<div class="login-help">
								<a href="<%=request.getContextPath()%>/views/member/signUp.jsp">회원가입</a>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="#" class="login"
									data-toggle="modal" data-target="#pwd-modal">아이디 / 비밀번호 찾기</a>
							</div>
						</div>
					</div>
				</div>
			</form>

			<form action="<%=request.getContextPath()%>/member.find"
				method="post">
				<div class="modal fade" id="pwd-modal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true"
					style="display: none;">
					<div class="modal-dialog" style="text-align: center">
						<div class="pwdmodal-container">
							<h1>아이디 / 비밀번호 찾기</h1>
							<br> <br> <br> <br> <br> <br> <input
								type="text" name="email" id="email" style="line-height: 30px;"
								placeholder="이메일"> <input type="submit" class="button"
								style="font-size: 12px; line-height: 30px;" value="이메일 인증">
						</div>
					</div>
				</div>
			</form>
		</header>
	</div>
</body>

</html>
