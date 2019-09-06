<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/coinheader.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 메인</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/admin/admin.css" />
</head>
<body>
<% if(loginUser.getGrade().equals("관리자")){ %>
	<div class="emptyHeader"></div>
	<div class="center">
		<nav>
			<%-- 회원관리 : 회원정보 RUD, 등급 설정, 블랙리스트 등록 및 해제 --%>
			<div class="menu" onclick="location.href='<%=request.getContextPath()%>/admin.allMemberList'">회원관리</div>
			<%-- 상픔목록 CRUD --%>
			<div class="menu" onclick="location.href='<%=request.getContextPath()%>/admin.allProductList'">상품관리</div>
			<br>
			<%-- 배송관리 --%>
			<%-- <div class="menu" onclick="location.href='<%=request.getContextPath()%>/views/admin/admin_deliveryView.jsp'">배송관리</div> --%>
			<%-- 공지 CRUD, QNA CRUD --%>
			<%-- <div class="menu" onclick="location.href='<%=request.getContextPath()%>/views/admin/admin_noticeView.jsp'">공지,QNA</div> --%>
			<br>
			<%-- 신고 된 게시글 조회, 삭제 --%>
			<%-- <div class="menu" onclick="location.href='<%=request.getContextPath()%>/views/admin/admin_reportedView.jsp'">게시물 관리</div> --%>
			<%-- 통계 : 광고 재생 수, 기간 별 신규 가입 --%>
			<%-- 결제정보조회 --%>
			<%-- 사업장 정보 등록, 수정, 삭제 --%>
		</nav>
	</div>
<%-- jQuery --%>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<%} else {%>
	<script>
		alert("관리자 계정으로 로그인 해주세요.");
		location.href = "<%=request.getContextPath()%>/index.jsp";
	</script>
<%}%>
</body>
</html>