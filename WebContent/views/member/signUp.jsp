<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/coinheader.jsp"%>
<% String msg = (String)request.getAttribute("msg"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/admin/admin.css">
<body>
	<script>
		if(<%=msg != null%>){
			alert("<%=msg%>");
			location.href="<%=request.getContextPath()%>/index.jsp";
		}
	</script>
	<div class="content">
		<form action="<%=request.getContextPath()%>/member.signUp" method="post">
			<table class="signUpTable">
				<tr>
					<td class="rowTitle">아이디</td>
					<td colspan="2"><input type="text" name="id" id="id"></td>
				</tr>
				<tr class="resultLabel" id="idResultTr">
					<td></td>
					<td id="idResultTd"></td>
				</tr>
				<tr>
					<td class="rowTitle">비밀번호</td>
					<td colspan="2"><input type="password" name="pw" id="pw"></td>
				</tr>
				<tr class="resultLabel" id="pwResultTr">
					<td></td>
					<td id="pwResultTd" colspan="2"></td>
				</tr>
				<tr>
					<td class="rowTitle">비밀번호 확인</td>
					<td colspan="2"><input type="password" name="pwCheck" id="pwCheck"></td>
				</tr>
				<tr class="resultLabel" id="pwCheckResultTr">
					<td></td>
					<td id="pwCheckResultTd" colspan="2"></td>
				</tr>
				<tr>
					<td class="rowTitle">닉네임</td>
					<td colspan="2"><input type="text" name="nickName" id="nickName"></td>
				</tr>
				<tr class="resultLabel" id="nickResultTr">
					<td></td>
					<td id="nickResultTd" colspan="2"></td>
				</tr>
				<tr>
					<td class="rowTitle">이름</td>
					<td colspan="2"><input type="text" name="name"></td>
				</tr>
				<tr>
					<td class="rowTitle">연락처</td>
					<td colspan="2">
						<input type="text" id="phone1" name="phone" class="phone" maxlength="3">
						<label>-</label>
						<input type="text" id="phone2" name="phone" class="phone" maxlength="4">
						<label>-</label>
						<input type="text" id="phone3" name="phone" class="phone" maxlength="4">
					</td>
				</tr>
				<tr class="resultLabel" id="phoneResultTr">
					<td></td>
					<td id="phoneResultTd" colspan="2"></td>
				</tr>
				<tr>
					<td class="rowTitle">생년월일</td>
					<td colspan="2">
						<script type="text/javascript">
							var today = new Date();
							var toyear = parseInt(today.getFullYear());
							var start = toyear;
							var end = toyear - 100;
	
							document.write("<select name='birth'> ");
							for (i = start; i >= end; i--) {
								document.write('<option value="' + i + '">' + i + '</option>');
							}
							document.write("</select><label>년 </label>");
	
							document.write("<select name='birth'>");
							for (i = 1; i <= 12; i++) {
								document.write('<option value="' + i + '">' + i + '</option>');
							}
							document.write("</select><label>월 </label>  ");
	
							document.write("<select name='birth'>");
							for (i = 1; i <= 31; i++) {
								document.write('<option value="' + i + '">' + i + '</option>');
							}
							document.write("</select><label>일</label>");
						</script>
					</td>
				</tr>
				<tr>
					<td class="rowTitle">성별</td>
					<td class="genderTd" colspan="2">
						<input id="genderM" class="gender" type="radio" name="gender" value="M" style="background-image: url('<%=request.getContextPath()%>/img/memberImg/M.png');"><label>남</label>
						<input id="genderW" class="gender" type="radio" name="gender" value="W" style="background-image: url('<%=request.getContextPath()%>/img/memberImg/W.png');"><label>여</label>
					</td>
				</tr>
				<tr>
					<td class="rowTitle">이메일</td>
					<td colspan="2"><input id="signUpEmail" type="text" name="email"></td>
				</tr>
				<tr id="emailResultTr" class="resultLabel">
					<td></td>
					<td id="emailResultTd" colspan="2"></td>
				</tr>
				<tr>
					<td class="rowTitle">뉴스메일</td>
					<td colspan="2">
						<input class="newsAgree" type="radio" name="news" value="0" style="background-image: url('<%=request.getContextPath()%>/img/memberImg/V.png');">
						<label>동의</label>
						<input class="newsDisagree" type="radio" name="news" value="1" style="background-image: url('<%=request.getContextPath()%>/img/memberImg/X.png');">
						<label>동의하지 않음</label>
					</td>
				</tr>
				<tr>
					<td class="rowTitle">SMS안내<br>(이벤트)</td>
					<td colspan="2">
						<input class="smsAgree" type="radio" name="sms" value="0" style="background-image: url('<%=request.getContextPath()%>/img/memberImg/V.png');">
						<label>동의</label>
						<input class="smsDisagree" type="radio" name="sms" value="1" style="background-image: url('<%=request.getContextPath()%>/img/memberImg/X.png');">
						<label>동의하지 않음</label>
					</td>
				</tr>
				<tr>
					<td class="rowTitle">주소<br>(선택사항)</td>
					<td>
						<input id="addressActive" class="adminButton" type="button" onclick="sample4_execDaumPostcode();" value="주소 찾기"><br><br>
					</td>
					<td id="addressDetail" class="none">
						<input type="text" style="background: none; color: white;" name="postNum" id="sample4_postcode" placeholder="우편번호" readonly><br><br>
						<input type="text" name="roadAddress" id="sample4_roadAddress" placeholder="도로명주소"><br><br>
						<input type="text" name="jibunAddress" id="sample4_jibunAddress" placeholder="지번주소"><br><br>
						<input type="text" name="address_detail" id="sample4_detailAddress" placeholder="상세주소"><br><br>
						<input type="text" style="background: none; color: white;" id="sample4_extraAddress" placeholder="참고항목" readonly><br>
						<span id="guide" style="color:#999;display:none"></span>
				
						<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
						<script src="<%=request.getContextPath()%>/js/address/address.js"></script>
					</td>
				</tr>
				
				<tr>
					<td colspan="3" class="center">
						<button class="adminButton" id="submit" type="submit" >회원가입</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<script>
	var url = document.location.href;
	console.log(url);
	console.log(location.href);
<%-- 라디오 버튼 이미지  css 변경 --%>
	$('#addressActive').click(function(){
		$('#addressDetail').css('display', 'inherit');
	});
	
<%-- 라디오 버튼 이미지  css 변경 --%>
	$('#genderM').click(function(){
		$('#genderM').css("background", "url('<%=request.getContextPath()%>/img/memberImg/MChecked.png')");
		$('#genderW').css("background", "url('<%=request.getContextPath()%>/img/memberImg/W.png')");
	});
	
	$('#genderW').click(function(){
		$('#genderW').css("background", "url('<%=request.getContextPath()%>/img/memberImg/WChecked.png')");
		$('#genderM').css("background", "url('<%=request.getContextPath()%>/img/memberImg/M.png')");
	});
	$('.newsAgree').click(function(){
		$('.newsAgree').css("background", "url('<%=request.getContextPath()%>/img/memberImg/VChecked.png')");
		$('.newsDisagree').css("background", "url('<%=request.getContextPath()%>/img/memberImg/X.png')");
	});
	$('.newsDisagree').click(function(){
		$('.newsDisagree').css("background", "url('<%=request.getContextPath()%>/img/memberImg/XChecked.png')");
		$('.newsAgree').css("background", "url('<%=request.getContextPath()%>/img/memberImg/V.png')");
	});
	$('.smsAgree').click(function(){
		$('.smsAgree').css("background", "url('<%=request.getContextPath()%>/img/memberImg/VChecked.png')");
		$('.smsDisagree').css("background", "url('<%=request.getContextPath()%>/img/memberImg/X.png')");
	});
	$('.smsDisagree').click(function(){
		$('.smsDisagree').css("background", "url('<%=request.getContextPath()%>/img/memberImg/XChecked.png')");
		$('.smsAgree').css("background", "url('<%=request.getContextPath()%>/img/memberImg/V.png')");
	});
	
	
<%-- 모든 조건 충족 시 버튼 활성화 --%>
	function buttonActive() {
		if (idUsable && pwUsable && nickUsable && phoneUsable && emailUsable) {
			$('#submit').removeAttr('disabled').css({'background' : 'green'});
		} else {
			$('#submit').prop('disabled', 'disabled').css({'background' : 'gray'});

		}
	}
	
<%-- submit시 radio버튼 체크여부 확인 --%>
	function checkAttr(){
		if(!$('input:radio[name=gender]').is(':checked')){
			alert('성별을 체크해주세요');
		} else if(!$('input:radio[name=news]').is(':checked')){
			alert('뉴스메일 수신여부를 체크해주세요');
		} else if(!$('input:radio[name=sms]').is(':checked')){
			alert('sms 수신여부를 체크해주세요');
		}
	}
	
<%-- 아이디 사용 가능 여부 (정규식 및 중복확인) --%>
	var $id = $('#id');
	// 페이지 입장 시 id태그에 focus
	$id.focus();
	
	// 정규식
	// 영어로 시작
	var regId = /^[a-zA-Z]/
	// 특수문자 불가
	var regId2 = /[a-zA-Z0-9_]+$/;
	
	var idUsable = false;
	var idChecked = false;
	$id.change(function() {
		// 태그에서 벗어날 때마다 전부 false로 변환
		idUsable = false;
		idChecked = false;
		if($id.val().length == 0){
			$('#idResultTr').css({ 'color' : 'red', 'display' : 'table-row' });
			$('#idResultTd').text('아이디를 입력해주세요.');
			$id.focus();
		} else if ($id.val().length < 6) {
			$('#idResultTr').css({ 'color' : 'red', 'display' : 'table-row' });
			$('#idResultTd').text('아이디는 최소 6자리 이상이어야 합니다.');
			$id.focus();
		} else if (!regId.test($id.val())){
			$('#idResultTr').css({ 'color' : 'red', 'display' : 'table-row' });
			$('#idResultTd').text('아이디는 숫자로 시작 할 수 없습니다.');
			$id.focus();
		} else if (!regId2.test($id.val())){
			$('#idResultTr').css({ 'color' : 'red', 'display' : 'table-row' });
			$('#idResultTd').text('아이디에 사용 불가능한 문자가 포함되어 있습니다.');
			$id.focus();
		} else if(!idChecked) {
			// 중복확인 ajax
			$.ajax({
				url: "<%=request.getContextPath()%>/member.idCheck",
				type: 'post',
				data:{id:$id.val()},
				success: function(data){
					if(data != "success"){
						$('#idResultTr').css({ 'color' : 'red', 'display' : 'table-row' });
						$('#idResultTd').text('이미 사용중인 아이디입니다.');
						$id.focus();
					} else {
						idChecked = true;
						idUsable = true;
						$('#idResultTr').css({ 'color' : 'white', 'display' : 'table-row'});
						$('#idResultTd').text('사용 가능한 아이디입니다.');
						buttonActive();
					}
				}
			});
		}
	});
<%-- 아이디 끝--%>
<%-- 비밀번호 사용 가능 여부 (정규식, 일치) : js파일에 있음--%>
	var $pw = $('#pw');
	var $pwCheck = $('#pwCheck');
	var regPw = /^.*(?=^.{6,}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
	
	var pwUsable = false;
	var pwUsable1 = false;
	var pwUsable2 = false;

	$pw.on("change paste keyup", function() {
		pwUsable = false;
		pwUsable1 = false;
		if ($pw.val().length == 0) {
			$('#pwResultTr').css({'color' : 'red', 'display' : 'table-row'});
			$('#pwResultTd').text('비밀번호를 입력해주세요.');
			$pw.focus();
		} else if ($pw.val().length < 6) {
			$('#pwResultTr').css({'color' : 'red', 'display' : 'table-row'});
			$('#pwResultTd').text('6자리 이상의 비밀번호를 입력해주세요.');
			$pw.focus();
		} else if (!regPw.test($pw.val())) {
			$('#pwResultTr').css({'color' : 'red', 'display' : 'table-row'});
			$('#pwResultTd').text('영문과 숫자, 특수문자가 각 1회 이상 사용되어야합니다.');
			$pw.focus();
		} else {
			$('#pwResultTr').css({'color' : 'white','display' : 'table-row'});
			$('#pwResultTd').text('사용 가능한 비밀번호입니다.');
			pwUsable1 = true;
			if (pwUsable2) {
				pwUsable = true;
				buttonActive();
			}
		}
	});
	// 두 입력 비밀번호가 같은지 확인
	$pwCheck.on('change paste keyup', function() {
		pwUsable = false;
		pwUsable2 = false;
		if (pwUsable1 == true && $pw.val() == $pwCheck.val()) {
			$('#pwCheckResultTd').text('사용 가능한 비밀번호입니다.');
			$('#pwCheckResultTr').css({ 'color' : 'white', 'display' : 'table-row'});
			pwUsable2 = true;
			if (pwUsable1) {
				pwUsable = true;
				buttonActive();
			}
		} else if($pw.val().length < 1) {
			$('#pwCheckResultTd').text('비밀번호를 입력해주세요.');
			$('#pwCheckResultTr').css({ 'color' : 'red', 'display' : 'table-row'});
			$pwCheck.focus();
		} else {
			$('#pwCheckResultTd').text('입력하신 두 비밀번호가 같지 않습니다.');
			$('#pwCheckResultTr').css({ 'color' : 'red', 'display' : 'table-row'});
			$pwCheck.focus();
		}
	});
<%-- 비밀번호 끝 --%>

<%-- 닉네임 사용 가능 여부 (중복확인 및 정규식) --%>
	var $nickName = $('#nickName');
	// 정규식
	var regNick = /^[a-zA-Z가-힣]/
	var regNick2 = /[a-zA-Z가-힣0-9_]+$/;
	var nickUsable = false;
	$nickName.change(function() {
		// 사용가능여부 : 사용 가능 할 때 true 반환
		var nickChecked = false;
		nickUsable = false;
		// 중복체크 : 사용 가능 할 때 true 반환
		// 둘 중 하나라도 false 라면
		if($nickName.val().length == 0){
			$('#nickResultTr').css({ 'color' : 'red', 'display' : 'table-row'});
			$('#nickResultTd').text('닉네임을 입력해주세요.');
			$nickName.focus();
		} else if ($nickName.val().length < 3) {
			$('#nickResultTr').css({ 'color' : 'red', 'display' : 'table-row'});
			$('#nickResultTd').text('닉네임은 최소 3자리 이상이어야 합니다.');
			$nickName.focus();
		} else if (!regNick.test($nickName.val())){
			$('#nickResultTr').css({ 'color' : 'red', 'display' : 'table-row'});
			$('#nickResultTd').text('닉네임은 숫자로 시작 할 수 없습니다.');
			$nickName.focus();
		} else if (!regNick2.test($nickName.val())){
			$('#nickResultTr').css({ 'color' : 'red', 'display' : 'table-row'});
			$('#nickResultTd').text('닉네임에 사용 불가능한 문자가 포함되어 있습니다.');
			$nickName.focus();
		} else if(!nickChecked) {
			// 중복확인 ajax
			$.ajax({
				url: "<%=request.getContextPath()%>/member.nickCheck",
				type: 'post',
				data:{nickName:$nickName.val()},
				success: function(data){
					if(data != "success"){
						$('#nickResultTr').css({ 'color' : 'red', 'display' : 'table-row'});
						$('#nickResultTd').text('이미 사용중인 닉네임입니다.');
						$nickName.focus();
					} else {
						nickChecked = true;
						nickUsable = true;
						$('#nickResultTr').css({ 'color' : 'white', 'display' : 'table-row'});
						$('#nickResultTd').text('사용 가능한 닉네임입니다.');
						buttonActive();
					}
				}
			});
		}
	});
<%-- 닉네임 끝 --%>
	
<%-- 연락처 정규식 --%>
	var $phone1 = $('#phone1');
	var $phone2 = $('#phone2');
	var $phone3 = $('#phone3');
	
	var regPhone1 = /(01[0-9])/;
	var regPhone2 = /\d{3,4}/;
	var regPhone3 = /\d{4}/;
	
	var phoneUsable = false;
	var phoneUsable1 = false;
	var phoneUsable2 = false;
	var phoneUsable3 = false;
	
	$phone1.change(function() {
		phoneUsable1 = false;
		if (!regPhone1.test($phone1.val())) {
			$('#phoneResultTr').css({'color' : 'red', 'display' : 'table-row'});
			$('#phoneResultTd').text('연락처를 확인해주세요.');
			$phone1.val('');
			$phone1.focus();
		} else {
			phoneUsable1 = true;
			phoneUsableF();
		}
	});
	$phone2.change(function() {
		phoneUsable2 = false;
		if (!regPhone2.test($phone2.val())) {
			$('#phoneResultTr').css({'color' : 'red', 'display' : 'table-row'});
			$('#phoneResultTd').text('연락처를 확인해주세요.');
			$phone2.val('');
			$phone2.focus();
		} else {
			phoneUsable2 = true;
			phoneUsableF();
		}
	});
	$phone3.change(function() {
		phoneUsable3 = false;
		if (!regPhone3.test($phone3.val())) {
			$('#phoneResultTr').css({'color' : 'red', 'display' : 'table-row'});
			$('#phoneResultTd').text('연락처를 확인해주세요.');
			$phone3.val('');
			$phone3.focus();
		} else {
			phoneUsable3 = true;
			phoneUsableF();
		}
	});
	function phoneUsableF(){
		if(phoneUsable1 && phoneUsable2 && phoneUsable3) {
			$('#phoneResultTr').css({ 'color' : 'white', 'display' : 'table-row'});
			$('#phoneResultTd').text('사용 가능한 연락처입니다.');
			phoneUsable = true;
			buttonActive();
		}
	}
<%-- 연락처 끝 --%>
	
<%-- 이메일 중복확인, 정규식 --%>
	var $email = $('#signUpEmail')
	var regEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	var emailUsable = false;
	var emailCheck = false;
	$email.change(function(){
		emailUsable = false;
		emailChecked = false;
		if ($email.val().length < 1){
			$('#emailResultTr').css({ 'color' : 'red', 'display' : 'table-row'});
			$('#emailResultTd').text('이메일을 입력해주세요.');
			$email.focus();
		} else if (!regEmail.test($email.val())){
			$('#emailResultTr').css({ 'color' : 'red', 'display' : 'table-row'});
			$('#emailResultTd').text('이메일 형식이 아닙니다.');
			$email.focus();
		} else if (!emailUsable || !emailChecked){
			$.ajax({
				url: "<%=request.getContextPath()%>/member.emailCheck",
				type: 'post',
				data:{email:$email.val()},
				success: function(data){
					if(data != "success"){
						$('#emailResultTd').text('이미 사용중인 이메일입니다.');
						$email.focus();
					} else {
						emailUsable = true;
						emailChecked = true;
						$('#emailResultTr').css({ 'color' : 'white', 'display' : 'table-row'});
						$('#emailResultTd').text('사용 가능한 이메일입니다.');
						buttonActive();
					}
				}
			});
		}
	});
		
	</script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.4.1.min.js"></script>
</body>
</html>
