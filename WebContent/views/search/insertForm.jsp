<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<meta charset="UTF-8">
<style>
body{
	background-color: rgb(40, 44, 52) !important;
}

.py-5 img{
	width:50%;
	margin-left: 3em;
}

.formArea {
		width:60%;
		margin:auto;
		padding-top: 50px;
		padding-bottom: 50px;
} 

.st {
	width:80%;
	background-color: #635e6e;
	height:100%;
	margin-left: auto;
	margin-right: auto;
	border-radius: 20px;
}
.s1{
	margin-top:2em;
	width:50%;
}


.sm {
	width: 150px !important; 
	display: inline !important;
	margin-left: 10px;
	margin-right: 10px;
	font-size: 1em;
}
.bm {
	padding-bottom: 200px;
	color: #fff;
}
.subfont{
	color: #dbdbdb;
	font-size: 12px;
}
.form-control hr{
	width: 80%;
	color: #c8c8c8;
}

@media (min-width: 768px) {
	.formArea {
		width:80%;
	} 
	.py-5 img{
		width:50%;
		margin-left: 5em;
	}
}

@media (min-width: 960px) {
	.formArea {
		width:80%;
	} 
	.py-5 img{
		width:30%;
		margin-left: 10em;
	}
}
</style>
<title>Insert title here</title>
</head>
<%@include file="../common/coinheader.jsp" %>
<body>
	<div class="container bm">
		<div class="py-5 text-center">
			<img src="write.png">
	        <h2>노래방 정보 입력</h2>
	        <p class="lead">등록할 업체의 정보를 입력해주세요!</p>
	    </div>
	    	<div class="st">
		      	<div class="formArea">
			      	<form action="<%= request.getContextPath() %>/insert.ko" method="post" encType="multipart/form-data">
					  <div class="form-group">
					  <% if(loginUser != null) { %>
					  	<input name="id" type="hidden" value="<%= loginUser.getId() %>">
					  <% } %>
					    <label><strong>업체명</strong></label>
					    <input type="text" class="form-control" id="kname" name="kname">
					  </div>
					  <div class="form-group">
					    <button type="button" class="btn btn-primary" onclick="sample4_execDaumPostcode();">주소 검색</button>
					    <br><span class="s1">우편번호<input type="text" class="form-control" id="sample4_postcode" name="sample4_postcode" placeholder="우편번호" readonly></span>
					    <br><span class="s1">도로명 주소<input type="text" class="form-control" id="sample4_roadAddress" name="sample4_roadAddress" readonly/></span>
					    <br><span class="s1">지번 주소<input type="text" class="form-control" id="sample4_jibunAddress" name="sample4_jibunAddress" readonly/></span>
					    <span id="guide" style="color:#999;display:none"></span>
					    <br><span class="s1">상세 주소<input type="text" class="form-control" id="sample4_detailAddress" name="sample4_detailAddress" /></span>
					    <input type="hidden" id="sample4_extraAddress" placeholder="참고항목">
					   <!-- 주소 API -->
					    <script>
						    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
						    function sample4_execDaumPostcode() {
						        new daum.Postcode({
						            oncomplete: function(data) {
						                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
						
						                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
						                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						                var roadAddr = data.roadAddress; // 도로명 주소 변수
						                var extraRoadAddr = ''; // 참고 항목 변수
						
						                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
						                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
						                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
						                    extraRoadAddr += data.bname;
						                }
						                // 건물명이 있고, 공동주택일 경우 추가한다.
						                if(data.buildingName !== '' && data.apartment === 'Y'){
						                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
						                }
						                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
						                if(extraRoadAddr !== ''){
						                    extraRoadAddr = ' (' + extraRoadAddr + ')';
						                }
						
						                // 우편번호와 주소 정보를 해당 필드에 넣는다.
						                document.getElementById('sample4_postcode').value = data.zonecode;
						                document.getElementById("sample4_roadAddress").value = roadAddr;
						                document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
						                
						                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
						                if(roadAddr !== ''){
						                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
						                } else {
						                    document.getElementById("sample4_extraAddress").value = '';
						                }
						
						                var guideTextBox = document.getElementById("guide");
						                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
						                if(data.autoRoadAddress) {
						                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
						                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
						                    guideTextBox.style.display = 'block';
						
						                } else if(data.autoJibunAddress) {
						                    var expJibunAddr = data.autoJibunAddress;
						                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
						                    guideTextBox.style.display = 'block';
						                } else {
						                    guideTextBox.innerHTML = '';
						                    guideTextBox.style.display = 'none';
						                }
						            }
						        }).open();
						    }
						</script>
						<!-- 주소 API끝 -->
					  </div>
					  <div class="form-group">
					  	<label style="width:100%"><strong>가격 정보</strong></label>
					    <span>1곡</span><input type="number" class="form-control" style="width:200px;" min="0" step="100" id="one" name="one">
					    <span>3곡</span><input type="number" class="form-control" style="width:200px;" min="0" step="100" id="three" name="three">
					  </div>
					  <div class="form-group inline1">
					  	<strong>영업시간</strong><input type="time" class="form-control sm" name="startTime" id="startTime"><input type="time" class="form-control sm" name="endTime" id="endTime">
					  	 <script type="text/javascript">
						    $("input[name='startTime']").timeInput({min: "0:00", max: "24:00"}); // 15 min intervals from 6:00 am to 3:00 pm
						</script>
					  </div>
					  <div class="form-group">
					  	<p><strong>타이틀 사진 첨부</strong><span class="subfont">(선택된 사진이 대표 사진이 됩니다.)</span></p>
					    <input multiple="multiple" type="file" name="File1" id="kimg1">
					    <p class="help-block"></p>
					  </div>
					  <div class="form-group">
					  	<p><strong>사진 첨부</strong></p>
					    <input type="file" name="file2" id="kimg2">
					    <input type="file" name="file3" id="kimg2">
					    <input type="file" name="file4" id="kimg2">
					    <p class="help-block"></p>
					  </div>
					<input type="submit" id="insertBtn" class="btn btn-success" value="등록">
				</form>
			</div>
		</div>
	</div>
	<script>
	$('#insertBtn').click(function(){
      
      if($('#kimg1').val() != "" && $('#kimg2').val()!="" && $('#kname').val()!="" && $('#sample4_postcode').val()!="" && $('#sample4_detailAddress').val()!="" && $('#one').val()!="" && $('#three').val()!="" && $('#startTime').val()!="" && $('#endTime').val()!=""){
       $('#insertBtn').prop("type","submit"); 
      }else{
      alert("모든 항목을 채워주세요!");
      }
   });
	</script>
</body>
<%@ include file="../common/coinfooter.jsp"%>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/header.js"></script>
</html>