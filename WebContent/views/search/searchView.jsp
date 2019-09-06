<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="karaoke.model.vo.*, java.util.*, common.PageInfo" %>
<%
	ArrayList<Karaoke> list = (ArrayList<Karaoke>)request.getAttribute("list");
	ArrayList<Attachment> at = (ArrayList<Attachment>)request.getAttribute("at");
	ArrayList<Review> review = (ArrayList<Review>)request.getAttribute("review");
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	
	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();

%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>

.customoverlay {position:relative;bottom:85px;border-radius:6px;border: 1px solid #ccc;border-bottom:2px solid #ddd;float:left;}
.customoverlay:nth-of-type(n) {border:0; box-shadow:0px 1px 2px #888;}
.customoverlay a {display:block;text-decoration:none;color:#000;text-align:center;border-radius:6px;font-size:14px;font-weight:bold;overflow:hidden;background: #d95050;background: #d95050 url(http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png) no-repeat right 14px center;}
.customoverlay .title {display:block;text-align:center;background:#fff;margin-right:35px;padding:10px 15px;font-size:14px;font-weight:bold;}
.customoverlay:after {content:'';position:absolute;margin-left:-12px;left:50%;bottom:-12px;width:22px;height:12px;background:url('http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')}
body{
	background-color: rgb(40, 44, 52) !important;
}
.list-group li {
	background-color: #363645;
}
#mainArea{
	width:100%;
	margin-left: auto;
	margin-right: auto;
	
}

/* 폼 관련  */ 
.searchBar{
	padding: 1em 1em 2em 1em;
}

/*** 리스트    ***/

.listTextArea{
	float: right;
    min-height: 16.3rem;
    position: relative;
    width: calc(60% - 1rem);
}

.listArea{
    padding: 0;
    position: relative;
}

.listArea::after {
    clear: both;
    content: '';
    display: table;
}


/* 리스트 이미지  */
.imgArea{
	border-radius: 0.8rem;
    bottom: 0;
    left: 0;
    overflow: hidden;
    padding: 0;
    position: absolute;
    top: 0;
    width: 40%;
}

.thumb{
	height: 100%;
    position: relative
}

.thumbimg{
	width: 100%;
    height: 100%;
    background: 50% no-repeat;
    display: -webkit-flex;
    display: -ms-flexbox;
    display: flex;
    overflow: hidden;
    position: absolute;
    top: 0;
    left: 0;
}

/** 별  **/
.col-sm-6 .fa fa-star{
	margin-bottom: 10px;
}
.checked {
  color: orange;
}

/** 폰트 **/
.review-SubTitle {
    color: #999;
    font-size: 1.4rem;
    font-weight: 600;
    padding-left: 1rem;
}

.location {
    color: #999;
    font-size: 1.1rem;
    font-weight: 600;
}

.review-count {
    color: #fff;
    font-size: 1.4rem;
    font-weight: 600;
    margin-left: 0.5rem;
}

.location-text{
	margin-top: 0.5rem;
}
.btn btn-info{
	font-size: 1.5em;
}

hr.hr-style {
	width: 100%;
    border: 0;
    height: 1px;
    background-image: linear-gradient(to right, rgba(255, 255, 255, 0.05), rgba(255, 255, 255, 0.75), rgba(255, 255, 255, 0.05));
}

/*   지도    */
#mapapi {
	width: 100%;
	height: 100%;
}

@media ( min-width : 481px) and (max-width: 768px) {
	#mapapi {
		width: 100%;
		height: 100%;
	}
	#mainArea{
		width:100%;
	}
	
}

@media ( min-width : 769px) {
	#mapapi {
		width: 100%;
		height: 900px;
	}
	#mainArea{
		width:70%;
		margin: auto;
	}
	.searchBar{
		padding: 1em 0em 2em 0em;
	}
	
}
</style>
<meta charset="UTF-8">

<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
<title>노래방 찾기</title>
</head>
<%@include file="/views/common/coinheader.jsp"%>
<body>
<div id="mainArea">
	<!-- 탭 영역 -->
	<nav>
	  <div class="nav nav-tabs" id="nav-tab" role="tablist">
	    <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">노래방 검색</a>
	    <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false">지도보기</a>
	  </div>
	</nav>
	<!-- 탭 끝 -->
	<!-- 내용 -->
	<div class="tab-content" id="nav-tabContent">
		<div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
			<!-- 서치바 영역 -->
			<div class="searchBar">
				<form class="form-inline">
					<input class="form-control mr-sm-2" type="search" aria-label="Search">
			   	 	<button class="btn btn-outline-success my-2 my-sm-0" type="submit">검색</button>
			   	 <% if(loginUser!=null) { %>
					<% if(loginUser.getNickName().equals("관리자")) { %>
						<button type="button" id="insertbtn" class="btn btn-primary" onclick="insertko();">노래방 등록</button>
					<% } %>
				<% } %>
				</form>
				<script>
					$("#insertbtn").click(function(){
						location.href="<%=request.getContextPath()%>/views/search/insertForm.jsp";
					});
				</script>
				
			</div>
			<!-- 검색 끝 -->
			
			<!-- 목록 나타나는 부분 -->
			<ul class="list-group list-group-flush">
			<% if(list.isEmpty()){ %>
				<li class="list-group-item">목록이 없습니다.</li>
			<% } else { %>
				<% for(int i=0;i<list.size();i++) { 
					Karaoke k = list.get(i); %>
				<a href="<%=request.getContextPath()%>/detail.ko?kid=<%= k.getKid()%>">
			  		<li class="list-group-item">
			  			<div class="listArea">
			  			<input type="hidden" value="<%= k.getKid() %>">
			  			<%for(int j=0; j<at.size(); j++){
							Attachment a = at.get(j);%>
							<%if(k.getKid() == a.getKid()) { %>
					  		<div class="imgArea">
					  			<div class="thumb">
					  				<div class="thumbimg" style="background-image: url('<%=request.getContextPath()%>/img/karaoke/<%=a.getChangeName()%>');  background-size: cover;"></div>
					  			</div>
					  		</div>
					  		<% } %>
					  	<% } %>
				  			<div class="listTextArea">
	      						<h3 class="mb-2"><%= k.getKaraokeName() %></h3>
	      						<span class="fa fa-star checked"></span>
	      						<% int count=0; %>
	      						<% for(int o=0;o<review.size();o++){ %>
	      							<% if(k.getKid() == review.get(o).getId()) { %>
	      								<% count++; %>
	      							<% } %>
	      						<% } %>
	      						<% double avg =0.0; double sum = 0.0; %>
	      						<% for(int l=0;l<review.size();l++){ %>
	      							<% if(k.getKid() == review.get(l).getId()) { %>
	      								<% sum += review.get(l).getKrating(); %>
	      								<% avg = Math.round(((double)sum/(double)count)*100.0)/100.0; %>
	      							<% } %>
	      						<% } %>
	      						<%= avg %> 점
	      						<span class="review-SubTitle">리뷰</span>
	      						<%= count %>개
	      						<br>
	      						<span class="location"><%= k.getRoadAddress() %><%= k.getAddressDetail() %> 에 위치함</span>
	      					</div>
      					</div>
      				</li>
      			</a>
				<% } %>
			<% } %>
			</ul>	
			<!-- 목록 끝 -->
			
			<div class="text-center">
				<ul class="pagination">
					<% if(!list.isEmpty()){ %>
					<li><a href="<%= request.getContextPath() %>/list.ko?currentPage=1">&lt;&lt;</a></li>
					<li><a id="bp" href="<%= request.getContextPath() %>/list.tb?currentPage=<%=  currentPage - 1%>">&lt;</a></li>
					
					<% for(int p=startPage; p<=endPage; p++){ %>
						<% if(p == currentPage){ %>
							<li><a href="#"><%= p %></a></li>
						<% } else if(p > currentPage) { %>
							<li><a href="<%= request.getContextPath() %>/list.ko?currentPage=<%= currentPage + 1 %>"><%= p %></a></li>
						<% } else { %>
							<li><a href="<%= request.getContextPath() %>/list.ko?currentPage=<%= currentPage - 1 %>"><%= p %></a></li>
						<% } %>
					<% } %>
					<li><a id="np" href="<%= request.getContextPath() %>/list.ko?currentPage=<%= currentPage + 1 %>">&gt;</a></li>
					<li><a href="<%= request.getContextPath() %>/list.ko?currentPage=<%= maxPage %>">&gt;&gt;</a></li>
					<% } %>
				</ul>
			</div>
			<script>
				if(<%= currentPage %> <= 1){
					$('#bp').click(function() {
						return false;
					});
				}
				
				if(<%= currentPage %> >= <%= maxPage %>){
					$('#np').click(function() {
						return false
					});
				}
			</script>
			
		</div>
		<!-- 탭2 -->
		<div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
	  <!-- 지도 표시되는 부분 -->
	  
	  <div id="mapapi"></div>
	  <!-- 지도 스크립트 -->
	  <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1e8732c5397c277a3b26c4848c8209b8&libraries=services,clusterer,drawing"></script>
	  <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1e8732c5397c277a3b26c4848c8209b8&libraries=LIBRARY"></script>
	  <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1e8732c5397c277a3b26c4848c8209b8"></script>
	  
	  <script>
			var mapContainer = document.getElementById('mapapi'), // 지도를 표시할 div 
		    mapOption = {
		        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		        level: 4 // 지도의 확대 레벨
		    }; 
			$('#nav-profile-tab').on('click', function() {
				setTimeout(function() {	
					map.relayout();
				}, 300);
			});
			
			// 지도를 생성합니다    
			var map = new kakao.maps.Map(mapContainer, mapOption); 
			
			if (navigator.geolocation) {
			    
			    // GeoLocation을 이용해서 접속 위치를 얻어옵니다
			    navigator.geolocation.getCurrentPosition(function(position) {
			        
			        var lat = position.coords.latitude, // 위도
			            lon = position.coords.longitude; // 
			            	
				    // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
				    var markerPosition = new kakao.maps.LatLng(lat, lon); // 마커가 표시될 위치입니다
	
				    // 마커를 생성합니다
				    var marker = new kakao.maps.Marker({
				      position: markerPosition
				    });
	
				    // 마커가 지도 위에 표시되도록 설정합니다
				    marker.setMap(map);  
	
				    // 커스텀 오버레이에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
				    var content = '<div class="customoverlay">' +
				        '  <a href="#" target="_blank">' +
				        '    <span class="title">현재위치</span>' +
				        '  </a>' +
				        '</div>';
	
				    // 커스텀 오버레이가 표시될 위치입니다 
				    var position = new kakao.maps.LatLng(lat, lon);  
	
				    // 커스텀 오버레이를 생성합니다
				    var customOverlay = new kakao.maps.CustomOverlay({
				        map: map,
				        position: position,
				        content: content,
				        yAnchor: 0.2 
				    });
				    map.setCenter(markerPosition); 
			      });
			    
			} else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
			    
			    var locPosition = new kakao.maps.LatLng(33.450701, 126.570667),    
			        message = 'geolocation을 사용할수 없어요..'
			        
			    displayMarker(locPosition, message);
			}

			// 지도에 마커와 인포윈도우를 표시하는 함수입니다
			function displayMarker(locPosition, message) {

			    // 마커를 생성합니다
			    var marker = new kakao.maps.Marker({  
			        map: map, 
			        position: locPosition
			    }); 
			    
			    var iwContent = message, // 인포윈도우에 표시할 내용
			        iwRemoveable = true;

			    // 인포윈도우를 생성합니다
			    var infowindow = new kakao.maps.InfoWindow({
			        content : iwContent,
			        removable : iwRemoveable
			    });
			    
			    // 인포윈도우를 마커위에 표시합니다 
			    infowindow.open(map, marker);
			    
			    // 지도 중심좌표를 접속위치로 변경합니다
			    map.setCenter(locPosition);      
			}    
			
			var geocoder = new kakao.maps.services.Geocoder();
			
			<%for(int i=0;i<list.size();i++){%>
				geocoder.addressSearch('<%= list.get(i).getRoadAddress() %>', function(result, status) {
	
				    // 정상적으로 검색이 완료됐으면 
				     if (status === kakao.maps.services.Status.OK) {
				        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
				        
				    // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
				    var markerPosition = coords; // 마커가 표시될 위치입니다

				    // 마커를 생성합니다
				    var marker = new kakao.maps.Marker({
				      position: markerPosition,
				    });

				    // 마커가 지도 위에 표시되도록 설정합니다
				    marker.setMap(map);  
					var page=<%= list.get(i).getKid() %>
				        var content = '<div class="customoverlay">' +
				        '  <a href="detail.ko?kid=<%= list.get(i).getKid() %>" target="_self">' +
				        '    <span class="title"><%=list.get(i).getKaraokeName() %></span>' +
				        '  </a>' +
				        '</div>';

				    // 커스텀 오버레이가 표시될 위치입니다 
				    var position = coords;  
				    
				    } 
				     var customOverlay = new kakao.maps.CustomOverlay({
		        	        map: map,
		        	        position: position,
		        	        content: content,
		        	        yAnchor: 0.2 
		        	});
				        
				});   
			<%}%> 
	  </script>
		<!-- 지도 스크립트 끝 -->
		</div>
		<!-- 탭2 끝 -->
	</div>
	<!-- 내용 끝 -->
</div>
<%@ include file="../common/coinfooter.jsp"%>
</body>
</html>