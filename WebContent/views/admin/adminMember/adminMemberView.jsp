<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, member.model.vo.*"%>
<%@ include file="/views/common/coinheader.jsp"%>

<%
	ArrayList<Member> list = (ArrayList<Member>) request.getAttribute("list");
	HashMap<String, Integer> addressCountMap = (HashMap<String, Integer>) request.getAttribute("addressCountMap");
	String msg = (String) request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리</title>
<%-- css 호출 --%>
<link rel="stylesheet" href='<%=request.getContextPath()%>/css/admin/admin.css'>
</head>
<body>
	<div>
<%-- 헤더 여백 --%>
		<div class="emptyHeader"></div>

<%-- 검색 영역 --%>
<%-- 회원정보조회, 등급 수정, 관리자 권한부여 --%>
		<div>
		<% if(loginUser.getGrade().equals("관리자")) {%>
<%-- 회원정보조회 --%>
			<% if (list.isEmpty()) { %>
			<h3>조회 결과가 없습니다.</h3>
			<% } else { %>
			<table class="resultList">
				<tr>
					<th>ID</th>
					<th>이름</th>
					<th>생년월일</th>
					<th>연락처</th>
					<th>성별</th>
					<th>등급</th>
					<th>회원가입일</th>
					<th>포인트</th>
					<th>주소 개수</th>
					<th>정보 수정</th>
					<!-- 영역 클릭 시 정렬 -->
				</tr>
			</table>
			<%
				for (int i = 0; i < list.size(); i++) {
					Member member = list.get(i);
			%>
			<hr>
			<form action="<%=request.getContextPath()%>/selectMember" method="post">
				<table class="resultList">
					<tr>
						<td>
							<input name="id" type="hidden" value="<%=member.getId()%>">
							<%=member.getId()%>
						</td>
						<td><%=member.getName()%></td>
						<td><%=member.getBirth()%></td>
						<td><%=member.getPhone()%></td>
						<td><%=member.getGender()%></td>
						<td><%=member.getGrade()%></td>
						<td><%=member.getRegDate()%></td>
						<td><%=member.getPoint()%></td>
						<% if(addressCountMap == null){ %>
						<td>0</td>
						<%} else {%>
						<td><%=addressCountMap.getOrDefault(member.getId(), 0) %></td>
						<%}%>
						<td><input class="adminButton" type="submit" value="상세정보"></td>
						
						<%-- 클릭 시 상세정보조회, 회원정보수정, 등급변경, 관리자 권한부여 --%>
					</tr>
				</table>
			</form>
			<% }}} else {%>
				<script>
					alert("관리자 계정으로 로그인 해주세요.");
					location.href = "<%=request.getContextPath()%>/index.jsp";
				</script>
			<%} %>
		</div>
	<script>
		if(<%=msg != null%>){
			alert("<%=msg%>");
		}
	</script>
	</div>
</body>
</html>
