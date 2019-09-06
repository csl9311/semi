<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ include file="/views/common/coinheader.jsp"%> --%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main/mainbanner.css">
</head>
<style>
#slider-wrap img{
cursor:pointer;
}

</style>
<body>
<jsp:include page="/views/common/coinheader.jsp"></jsp:include>

	<div id="main" >
		<!--*****************배너광고 영역  ************** -->
		<div id="Index" style="min-height:80vh">
			<div id="slider-wrap">
				<ul id="slider">
					<li>
						<div>
						<!-- 	<h3>배너 #1</h3>
							<span>Sub-title #1</span> -->
						</div>

						<img onclick="location.href='<%=request.getContextPath()%>/list.bo';" src="<%=request.getContextPath()%>/img/banner/ban3.jpg">

					</li>

					<li>
						<div>
						<!-- 	<h3>배너 #2</h3>
							<span>Sub-title #2</span> -->
						</div>
						<img  src="<%=request.getContextPath()%>/img/banner/ban5.jpg">
					</li>

					<li>
						<div>
							<!-- <h3>배너 #3</h3>
							<span>Sub-title #3</span> -->
						</div>
						<img src="<%=request.getContextPath()%>/img/banner/ban1.jpg">
					</li>

					<li>
						<div>
						<!-- 	<h3>배너 #4</h3>
							<span>Sub-title #4</span> -->
						</div>
						<img src="<%=request.getContextPath()%>/img/banner/ban4.jpg">
					</li>

					<li>
						<div>
							<!-- <h3>배너 #5</h3>
							<span>Sub-title #5</span> -->
						</div>
						<img src="<%=request.getContextPath()%>/img/banner/ban2.jpg">
					</li>
				</ul>

				<div class="slider-btns" id="next">
					<span>▶</span>
				</div>
				<div class="slider-btns" id="previous">
					<span>◀</span>
				</div>

				<div id="slider-pagination-wrap"><ul></ul></div>
			</div>

			<div class="mainMenu" style="margin-top:50px;">
				<img src="<%=request.getContextPath()%>/img/cmboard/find.png" onclick="location.href='<%=request.getContextPath()%>/list.ko';" >
				<img src="<%=request.getContextPath()%>/img/cmboard/board.png" onclick="location.href='<%=request.getContextPath()%>/list.tb';" >
				<img src="<%=request.getContextPath()%>/img/cmboard/video.png" onclick="location.href='<%=request.getContextPath()%>/list.bo';" >
				<img src="<%=request.getContextPath()%>/img/cmboard/shopcart.png"  onclick="location.href='<%=request.getContextPath()%>/shopMain.do';" >
				<%-- <%if(loginUser!=null){ %> --%>
				<img src="<%=request.getContextPath()%>/img/cmboard/mypage.png"  onclick="location.href='<%=request.getContextPath()%>/views/MyPage/myPageMain.jsp';" >
				<%-- <%} %> --%>



			</div>

		</div>


		<script>
			//slide-wrap
			var slideWrapper = document.getElementById('slider-wrap');
			//current slideIndexition
			var slideIndex = 0;
			//items
			var slides = document.querySelectorAll('#slider-wrap ul li');
			//number of slides
			var totalSlides = slides.length;
			//get the slide width
			var sliderWidth = slideWrapper.clientWidth;
			//set width of items
			slides.forEach(function(element) {
				element.style.width = sliderWidth + 'px';
			})
			//set width to be 'x' times the number of slides
			var slider = document.querySelector('#slider-wrap ul#slider');
			slider.style.width = sliderWidth * totalSlides + 'px';

			//next, prev
			var nextBtn = document.getElementById('next');
			var prevBtn = document.getElementById('previous');
			nextBtn.addEventListener('click', function() {
				plusSlides(1);
			});
			prevBtn.addEventListener('click', function() {
				plusSlides(-1);
			});

			//hover
			slideWrapper.addEventListener('mouseover', function() {
				this.classList.add('active');
				clearInterval(autoSlider);
			});
			slideWrapper.addEventListener('mouseleave', function() {
				this.classList.remove('active');
				autoSlider = setInterval(function() {
					plusSlides(1);
				}, 3000);
			});

			function plusSlides(n) {
				showSlides(slideIndex += n);
			}

			function currentSlides(n) {
				showSlides(slideIndex = n);
			}

			function showSlides(n) {
				slideIndex = n;
				if (slideIndex == -1) {
					slideIndex = totalSlides - 1;
				} else if (slideIndex === totalSlides) {
					slideIndex = 0;
				}

				slider.style.left = -(sliderWidth * slideIndex) + 'px';
				pagination();
			}

			//pagination
			slides.forEach(function() {
				var li = document.createElement('li');
				document.querySelector('#slider-pagination-wrap ul').appendChild(li);
			})

			function pagination() {
				var dots = document.querySelectorAll('#slider-pagination-wrap ul li');
				dots.forEach(function(element) {
					element.classList.remove('active');
				});
				dots[slideIndex].classList.add('active');
			}

			pagination();
			var autoSlider = setInterval(function() {
				plusSlides(1);
			}, 3000);


		</script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/main/header.js"> </script>
	</div>
</body>

		<%@ include file="/views/common/coinfooter.jsp"%>
</html>
