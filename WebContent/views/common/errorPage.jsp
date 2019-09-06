<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String msg = (String) request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>페이지에 문제가 있습니다.</title>
<style>
body {
	background-color: rgb(40, 42, 54);
	text-align: center;
	display: flex;
	justify-content: center;
}

#wrap {
	width: 1201px;
	height: 901px;
	position: relative;
	margin-top: 3vh;
}

#goBack, #goMain {
	display: flex;
	position: absolute;
	width: 200px;
	height: 70px;
	align-items: center;
	text-align: center;
	border-radius: 10px;
	justify-content: center;
	z-index: 999;
}

#goBack {
	background-color:  #FFEA96;
	/* background-color: rgb(120, 120, 120); */
	bottom: 180px;
	right: 100px;

}

#goMain {
	background-color: #ECECEC;
	bottom: 100px;
	right: 100px;
}

a {
	text-decoration: none;
	font-size: 2rem;
	font-weight: 600;
	font-family: "휴먼둥근헤드라인";
}

#goBack a {
	color: rgb(100, 100, 100);
}

#goMain a {
	color: rgb(100, 100, 100);
}
h1{
	display: flex;
	position: absolute;
	top: 0;
	align-self: center;
}
</style>
</head>
<body>
	<div id="wrap">
		<img alt="에러페이지" src="<%=request.getContextPath()%>/img/shopImg/error2.png">
		<div id="goBack">
			<a href="javascript:history.go(-1)">GO BACK</a>
		</div>
		<div id="goMain">
			<a href="<%=request.getContextPath()%>/views/common/mainPage.jsp">GO MAIN</a>
		</div>
	</div>
		<h1 align="center" style="color:rgb(210,210,210)">ERROR : <%=msg%></h1>

</body>
</html>
