<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<% 
	   Member loginInfo = (Member) session.getAttribute("loginUser");
    
   %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Insert title here</title>
<%@include file="/views/common/coinheader.jsp"%>
</head>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/community/freeBoardView.css">
	
<body>


	<div class="main">
		<div class="aviCommonBoard">
		<form action="<%= request.getContextPath() %>/insert.bo" method="post" encType="multipart/farm-date">
			<table>
				<tr>
					<td><input type="text"
						style="width: 60vw; margin-bottom: 5px; border: 0;"
						name="title" placeholder="Title입력란입니다.">
						<hr style="border: 0.7px solid gray"> <br></td>

				</tr>

				<tr>
					<td>
						<div style="overflow: hidden">
							<div class="youtubeInsert" id="youtubeInsert"
								style="width: 60vw; height: 40vh; display: none;"></div>
						</div>
						<div class="aviBoard"
							style="width: 60vw; height: 70vh; text-align: left; margin: 5px; display: inline-block;">

							<textarea style="height: 100%; width: 100%"
								name="content"></textarea>
						</div>
					</td>
				</tr>

				<tr>
					<td>
						<!-- <span style="font-size:20px; cursor:pointer"   data-toggle="modal" data-target="#insert-avi">영상등록</span> -->
						<span style="float: left; cursor: pointer; margin-bottom: 5px;"
						 id="opendiv">영상 삽입 하기</span>

						<div class="insertAddress fulldiv" style="display: none;">
							<input type="text"
								style="float: left; width: 70%; border: 0; margin-right: 30px;"
								class="inputYoutube" name="inputYoutube"
								placeholder="youtube 소스코드 붙여넣기">
							<button type="button" style="float: left;width:4vw;height:3vh;display:block; " 
								class="btn-primary btnYoutube">삽입</button>
							<hr style="border: 0.7px solid gray; width: 70%">
							<!-- <button class="inputAddress" style="float:right" >등록 </button> -->
						</div>
					</td>
				</tr>

				<tr>
					<td>
						
					<button type="button" style="width: 10vw; height: 7vh; float: right"
							class="btn-primary" id="insertAviBoard">등록하기</button>
						
							</td>
				</tr>

			</table>


		</form>
		</div>


	</div>

	<script>
	
	var count =0;
		$('#opendiv').click(function() {
			if ($(".insertAddress").css("display") == "none") {
				$('.insertAddress').css("display", "inline-block");
			} else {
				$('.insertAddress').css("display", "none");
			}
		});

		$('.btnYoutube')
				.click(
						function() {
						
							document.getElementById("youtubeInsert").innerHTML = $(
									'.inputYoutube').val();
							console.log("ddd" + $('iframe').attr('src'));

							if (/^https?:\/\/www.youtube.com\/embed\//g.test($(
									'iframe').attr("src"))) {
								console.log("성공");
								count++;

							if ($(".youtubeInsert").css("display") == "none") {

								$(window).resize(function() {
									resizeYoutube();
								});
								$(function() {
									resizeYoutube();
								});

								function resizeYoutube() {
									$("iframe")
											.each(
													function() {
														if (/^https?:\/\/www.youtube.com\/embed\//g
																.test($(this)
																		.attr(
																				"src"))) {

															$(this).css(
																	"width",
																	"100%");
															$(this)
																	.css(
																			"height",
																			Math
																					.ceil(parseInt($(
																							this)
																							.css(
																									"width")) * 480 / 854)
																					+ "px");
														}
													});
								}

								$('.youtubeInsert').css("display",
										"inline-block");
								
								  document.getElementById("youtubeInsert").scrollIntoView(); 
							} else {
								$('.youtubeInsert').css("display", "none");
							}
							} else {
								console.log("실패");
								document.getElementById("youtubeInsert").innerHTML="";
								alert("올바른 유튜브 소스코드를 입력해주세요.");
								$('.inputYoutube').val("");
								$('.inputYoutube').focus();
							
							}
							

						});
		
				$('#insertAviBoard').click(function(){
					if(count==0){
						console.log("dd")
						alert("삽입된 영상이 없습니다.");
					}else{
						$('#insertAviBoard').prop("type","submit");
						
					}
				});
	</script>



</body>

<%@ include file="/views/common/coinfooter.jsp"%>
</html>