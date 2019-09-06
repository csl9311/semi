<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>



</style>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/main/Footer-with-button-logo.css">
<title>Insert title here</title>
</head>
<body>

<div id="myFooter" >

	<div class="containerbg">
        <div class="container" >
            <div class="row">
                <div class="col-sm-3 footerlogo">
                    <h2 class="logoimg"><a href="#"><img src="<%= request.getContextPath() %>/img/cmboard/singsing.png" style="widht:130px;height:130px"> </a></h2>
                </div>
                <div class="col-sm-2 footerstart">
                    <h5>Get started</h5>
                    <ul>
                        <li><a href="<%= request.getContextPath() %>/views/common/mainPage.jsp">홈으로</a></li>
                        <li><a href="<%= request.getContextPath() %>/views/MyPage/upDate.jsp">회원가입</a></li>
                        <li><a href="#">사이트소개</a></li>
                    </ul>
                </div>
                <div class="col-sm-2 1 footerdelete">
                    <h5>About us</h5>
                    <ul>
                        <li><a href="#">회사 소개</a></li>
                        <li><a href="#">인재 채용</a></li>
                        <li><a href="#">제휴 제안</a></li>
                    </ul>
                </div>
                <div class="col-sm-2 1 footerdelete">
                    <h5>Support</h5>
                    <ul>
                        <li><a href="#">FAQ</a></li>
                        <li><a href="#">운영 정책</a></li>
                        <li><a href="#">고객센터</a></li>
                    </ul>
                </div>
                <div class="col-sm-3 2 footerdelete">
                    <div class="social-networks">
                        <a href="#" class="twitter"><i class="fa fa-twitter"></i></a>
                        <a href="#" class="facebook"><i class="fa fa-facebook"></i></a>
                        <a href="#" class="google"><i class="fa fa-google-plus"></i></a>
                    </div>
                    <button type="button" class="btn btn-default">Contact us</button>
                </div>
            </div>
        </div>
        </div>
        <div class="footer-copyright">
            <p>© 2019 Copyright 코노차자조 Corp. </p>
        </div>

    </div>
</body>

</html>
