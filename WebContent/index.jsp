<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="member.model.vo.Member"%>
<%
	Member loginInfo = (Member) session.getAttribute("loginUser");
	String msg = (String) request.getAttribute("msg");
%>
<!DOCTYPE html>
<html lang="ko" dir="ltr">
<head>
<meta charset="utf-8">
<title>메인화면</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.min.js">


</script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/main/main.css" />
</head>
<body>
<%@include file="/views/common/mainPage.jsp" %>

</body>
</html>
