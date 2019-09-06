<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="member.model.vo.*, java.util.ArrayList"%>
<%@ include file="/views/common/coinheader.jsp"%>
<%
	Member member = (Member) request.getAttribute("member");
	ArrayList<Address> addressList = (ArrayList<Address>)request.getAttribute("addressList");
	String[] genderChecked = new String[2];
	String[] smsChecked = new String[2];
	String[] newsChecked = new String[2];
	String[] selected = new String[5];
	String[] phone = {};
	if (member != null) {
		// phone
		phone = member.getPhone().split("-");
		// 성별
		if(member.getGender().equals("M")){
			genderChecked[0] = "checked";
		} else {
			genderChecked[1] = "checked";
		}
		// sms 수신 여부
		if(member.getSms() == 0){
			smsChecked[0] = "checked";
		} else {
			smsChecked[1] = "checked";
		}
		// news 수신 여부
		if(member.getNews() == 0){
			newsChecked[0] = "checked";
		} else {
			newsChecked[1] = "checked";
		}
		// 등급
		switch(member.getGrade()){
			case "일반": selected[0] = "selected"; break;
			case "사장님": selected[1] = "selected"; break;
			case "관리자": selected[2] = "selected"; break;
			case "휴면": selected[3] = "selected"; break;
			case "블랙": selected[4] = "selected"; break;
		}
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보조회</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/admin/admin.css">
</head>
<body>
	<div class="emptyHeader"></div>
	<div id="center">
	<% if (loginUser != null) { %>
<%-- 멤버 update --%>
		<form action="<%=request.getContextPath()%>/admin.MemberUpdate" method="post">
			<table border="1" style="border: 1px solid white;">
				<tr>
					<td class="rowTitle">아이디</td>
					<td colspan="2"><input name="id" class="adminInput readonly" type="text" value="<%=member.getId()%>" readonly></td>
					<td class="rowTitle" rowspan="2">등급</td>
					<td rowspan="2" colspan="2">
						<select class="grade" name="grade">
							<option <%=selected[0]%> value="1">일반</option>
							<option <%=selected[1]%> value="2">사장님</option>
							<option <%=selected[2]%> value="3">관리자</option>
							<option <%=selected[3]%> value="4">휴면</option>
							<option <%=selected[4]%> value="5">블랙</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="rowTitle">이름</td>
					<td colspan="2"><input class="adminInput" name="name" type="text"  value="<%=member.getName()%>"></td>
				</tr>
				<tr>
					<td class="rowTitle">생년월일</td>
					<td colspan="2"><input class="adminInput readonly" name="birth" type="text" value="<%=member.getBirth()%>" readonly></td>

					<td class="rowTitle" rowspan="2">회원가입일</td>
					<td rowspan="2" colspan="2"><input class="adminInput readonly" type="text" value="<%=member.getRegDate()%>" readonly></td>
				</tr>
				<tr>
					<td class="rowTitle">성별</td>
					<td class="center"><input id="genderM" class="adminRadio" type="radio" name="gender" value="M" <%=genderChecked[0]%>><label>남</label></td>
					<td class="center"><input id="genderW" class="adminRadio" type="radio" name="gender" value="W" <%=genderChecked[1]%>><label>여</label></td>
				</tr>
				<tr>
					<td class="rowTitle">연락처</td>
					<td colspan="2">
						<input class="adminInput phone" name="phone" type="text" value="<%=phone[0]%>">
						<label class="dash">-</label>
						<input class="adminInput phone" name="phone" type="text" value="<%=phone[1]%>">
						<label class="dash">-</label>
						<input class="adminInput phone" name="phone" type="text" value="<%=phone[2]%>">
					</td>
					<td class="rowTitle" rowspan="2">정보수정일</td>
					<td rowspan="2" colspan="2"><input class="adminInput readonly" name="modifyDate" type="text" value="<%=member.getModifyDate() %>" readonly></td>
				</tr>
				<tr>
					<td class="rowTitle">닉네임</td>
					<td colspan="2"><input class="adminInput" type="text" name="nickName" value="<%=member.getNickName()%>"></td>
				</tr>
				<tr>
					<td class="rowTitle">이메일</td>
					<td colspan="2"><input class="adminInput" type="email" name="email" value="<%=member.getEmail()%>"></td>
					
					<td class="rowTitle">포인트</td>
					<td colspan="2"><input class="adminInput readonly" name="point" type="text" value="<%=member.getPoint()%>" readonly></td>
				</tr>
				<tr>
					<td class="rowTitle">news 수신 여부</td>
					<td class="center">
						<input id="newsAgree" class="adminRadio" type="radio" name="news" <%=newsChecked[0]%> value="0">
						<label>O</label>
					</td>
					<td class="center">
						<input id="newsDisagree" class="adminRadio" type="radio" name="news" <%=newsChecked[1]%> value="1">
						<label>X</label>
					</td>
					<td class="rowTitle">sms 수신 여부</td>
					<td class="center">
						<input id="smsAgree" class="adminRadio" type="radio" name="sms" <%=smsChecked[0]%> value="0">
						<label>O</label>
					</td>
					<td class="center">
						<input id="smsDisagree" class="adminRadio" type="radio" name="sms" <%=smsChecked[1]%> value="1">
						<label>X</label>
					</td>
				</tr>
				<tr class="center">
					<td colspan="3"><button class="adminButton" type="submit">수정완료</button></td>
					<td colspan="3"><button class="adminButton" style="width: 180px;" type="button" onclick="location.href='javascript:history.go(-1)'">이전 페이지로</button></td>
				</tr>
			</table>
		</form>
<%-- 멤버 update --%>
		
<%-- 주소 insert --%>
		<div class="emptyHeader"></div>
		<div class="center">
			<button class="adminButton" id="insertButton" style="width:auto;" onclick="insertAddress();">새로운 주소 추가</button>
		</div>
		
		<div class="center none" id="insertDiv">
			<form action="<%=request.getContextPath()%>/address.insert" method="post">
				<input name="id" type="hidden" value="<%=member.getId()%>">
				<input name="page" type="hidden" value="/selectMember?id=<%=member.getId()%>">
				<input class="adminButton" type="button" onclick="sample4_execDaumPostcode()" value="주소 찾기"><br><br>
				<input type="text" style="background: none; color: white;" name="postNum" id="sample4_postcode" placeholder="우편번호" readonly><br><br>
				<input type="text" name="roadAddress" id="sample4_roadAddress" placeholder="도로명주소"><br><br>
				<input type="text" name="jibunAddress" id="sample4_jibunAddress" placeholder="지번주소"><br><br>
				<input type="text" name="address_detail" id="sample4_detailAddress" placeholder="상세주소"><br><br>
				<input type="text" style="background: none; color: white;" id="sample4_extraAddress" placeholder="참고항목" readonly><br>
				<span id="guide" style="color:#999;display:none"></span><br>
				<input type="submit" class="adminButton" value="주소 등록">
			
				<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
				<script src="<%=request.getContextPath()%>/js/address/address.js"></script>
			</form>
		</div>
<%-- 주소 update, delete --%>
		<div class="center">
		<%
			for (int i = 0 ; i < addressList.size(); i ++){ 
				Address address = addressList.get(i);
		%>
		<form class="updateAddress" id="modifyForm<%=i %>" action="<%=request.getContextPath()%>/address.update" method="post">
			<table border="1" style="border: 1px solid white;">
				<tr>
					<td colspan="2"><%=i+1 %> 번째 주소
					<input type="hidden" name="address_code" value="<%=address.getAddress_code()%>">
					<input name="page" type="hidden" value="/selectMember?id=<%=member.getId()%>">
					</td>
				</tr>
				<tr>
					<td>우편번호</td>
					<td><input class="adminInput" type="text" name="postNum" value="<%=address.getPostNum()%>"></td>
				</tr>
				<tr>
					<td>도로명주소</td>
					<td><input class="adminInput" type="text" name="roadAddress" value="<%=address.getRoadAddress()%>"></td>
				</tr>
				<tr>
					<td>지번주소</td>
					<td><input class="adminInput" type="text" name="jibunAddress" value="<%=address.getJibunAddress()%>"></td>
				</tr>
				<tr>
					<td>상세주소</td>
					<td><input class="adminInput" type="text" name="address_detail" value="<%=address.getAddress_detail()%>"></td>
				</tr>
				<tr>
					<td><input id="deleteButton" class="adminButton" type="button" onclick="deleteAddress<%=i %>();" value="삭제"></td>
					<td><input class="adminButton" type="submit" value="수정"></td>
				</tr>
			</table>
			<br><br>
		</form>
		
		
		<%}
		} else {
			%>
				<h2 class="center">관리자계정으로 로그인해주세요.</h2>
			<%
		}%>
		</div>
	</div>
	<script>
<%--주소 insert div 보이기--%>
	function insertAddress(){
		$('#insertDiv').css('display', 'block');
		$('#insertButton').css('display', 'none');
	}

<%-- 주소 delete --%>
<%
for (int i = 0 ; i < addressList.size(); i ++){ 
%>
	function deleteAddress<%=i%>(){
		var form = $('#modifyForm<%=i%>');
		form.attr('action', '<%=request.getContextPath()%>/address.delete');
		form.submit();
	}
<%}%>
<%-- 라디오버튼이 checked라면 스타일 변경 --%>
	if($('#genderM').is(':checked')){
		$('#genderM').css("background", "black");
	} else {
		$('#genderW').css("background", "black");
	}
	
	if($('#newsAgree').is(':checked')){
		$('#newsAgree').css("background", "black");
	} else {
		$('#newsDisagree').css("background", "black");
	}
	if($('#smsAgree').is(':checked')){
		$('#smsAgree').css("background", "black");
	} else {
		$('#smsDisagree').css("background", "black");
	}

<%-- 라디오 버튼 클릭 시  css 변경 --%>
	$('#genderM').click(function(){
		$('#genderM').css("background", "black");
		$('#genderW').css("background", "white");
	});
	
	$('#genderW').click(function(){
		$('#genderW').css("background", "black");
		$('#genderM').css("background", "white");
	});
	$('#newsAgree').click(function(){
		$('#newsAgree').css("background", "black");
		$('#newsDisagree').css("background", "white");
	});
	$('#newsDisagree').click(function(){
		$('#newsDisagree').css("background", "black");
		$('#newsAgree').css("background", "white");
	});
	$('#smsAgree').click(function(){
		$('#smsAgree').css("background", "black");
		$('#smsDisagree').css("background", "white");
	});
	$('#smsDisagree').click(function(){
		$('#smsDisagree').css("background", "black");
		$('#smsAgree').css("background", "white");
	});
	</script>
</body>
</html>