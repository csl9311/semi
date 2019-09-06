<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="product.model.vo.*, java.util.*,common.PageInfo, shop.model.vo.*"%>
<%@ include file="/views/common/coinheader.jsp"%>
<%
	ArrayList<Product> list = (ArrayList<Product>) request.getAttribute("list");
	PageInfo pi = (PageInfo) request.getAttribute("pi");

	ArrayList<Product> newList = (ArrayList<Product>) request.getAttribute("newList");
	ArrayList<Product> rankList = (ArrayList<Product>) request.getAttribute("rankList");
	ArrayList<PAttachment> pAttList = (ArrayList<PAttachment>) request.getAttribute("pAttList");
	
	System.out.println("pAttList: " + pAttList);
	
	String key = request.getParameter("key");
	String sort = request.getParameter("sort");

	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>shopMainView</title>
<link href="<%=request.getContextPath()%>/css/shop/shopMainView.css" rel="stylesheet">
</head>
<body>

	<!-- 상세 페이지 전체 감싸는 div -->
	<div id="Index">
		<!-- 소분류 카테고리 -->
		<nav id="shopCategory">
			<ul id="sct_ul">
			<% for(int i = 1; i <= 5; i++) { 
				String[] str = {"스피커", "헤드셋", "헤드폰 / 이어폰", "블루투스 사운드", "마이크"};
			%>
				<li>
					<div class="sct_btn">
						<a href="<%=request.getContextPath()%>/shopList.do?cid=<%=i*10%>"><span><%=str[i-1] %></span></a>
					</div>
				</li>
			<% } %>
			</ul>
		</nav>
		<!-- 상단 nav 제외 전체 감싸는 div -->
		<div id="content">
			<%-- <div id="main_top" class="flex column">
				<!-- 광고나 탑3 걸 곳 -->
				<div id="main_top_1" class="flex">
					<% for(int i = 0; i < 3; i++) {%>
						<div class="main_top_1_img">
							<a href=""><img alt="" src="<%=request.getContextPath()%>/img/shopImg/다운로드.jpg"></a>
						</div>
					<% } %>
				</div>
				<!-- 유명 브랜드 -->
				<div id="main_top_2" class="flex">
					<% for(int i = 0; i < 5; i++) {%>
						<div class="main_top_2_img">
							<a href=""><img alt="" src="<%=request.getContextPath()%>/img/shopImg/다운로드.jpg"></a>
						</div>
					<% } %>
				</div>
			</div> --%>
			<!-- 중간 -->

			<div id="main_middle">
				<!-- 신상품 -->
				<div id="new_item" class="flex column">
					<span class="mb_text"><b>NEW ARRIVAL</b></span>
					<ul class="flex">
						<%
							int count = 0;
							for (Product p : newList) {
								count++;
								if (count > 5)
									break;
						%>
						<li>
							<div class="item_border flex">
								<div class="item flex column">
								<div class="item_top">
											<img class="pointer" id="newImg<%=count%>" alt="" src="<%=request.getContextPath()%>/img/shopImg/다운로드.jpg">
										<%
										for(PAttachment pat : pAttList){ %>
											<%if(p.getpId() == pat.getpId()){ %>
												<script>
													$('#newImg'+<%=count%>).attr("src",'<%=pat.getFilePAth()%>');
												</script>	
										<%	}}
									%>
									</div>
									<div class="item_bottom">
									<input type="hidden" id="pId" name="pId" value="<%=p.getpId()%>">
										<span><b>[<%=p.getBrand()%>]</b></span><br> <span class="pointer"><%=p.getpName()%></span><br> 
										<span><%=p.getPrice()%></span>
									</div>

								</div>
							</div>
						</li>
						<%
							}
						%>
					</ul>
				</div>
				<!-- 베스트 상품 -->

				<div id="best_item" class=" flex column">
					<span class="mb_text"><b>RANKING</b></span>
					<ul class="flex">
						<%
							int count2 = 0;
							for (Product p : rankList) {
								count2++;
								if (count2 > 5)
									break;
						%>
						<li>
							<div class="item_border flex">
								<div class="item flex column">
									<div class="item_top">
											<img class="pointer" id="bestImg<%=count2%>" alt="" src="<%=request.getContextPath()%>/img/shopImg/다운로드.jpg">
										<%
										for(PAttachment pat : pAttList){ %>
											<%if(p.getpId() == pat.getpId()){ %>
												<script>
													$('#bestImg'+<%=count2%>).attr("src",'<%=pat.getFilePAth()%>');
												</script>	
										<%	}}
									%>
									</div>
									<div class="item_bottom">
									<input type="hidden" id="pId" name="pId" value="<%=p.getpId()%>">
										<span><b>[<%=p.getBrand()%>]</b></span><br> <span class="pointer"><%=p.getpName()%></span><br> 
										<span><%=p.getPrice()%></span>
									</div>

								</div>
							</div>
						</li>
						<%
							}
						%>
					</ul>
				</div>
			</div>
			<div id="main_bottom" class="flex column">
				<div id="search_bar_top">
					<span>Total: <b><%=listCount%></b> items
					</span>
					<div style="text-align: right; flex:1;">
					품절포함&nbsp;
					</div>
					<ul class="flex" id="sortBy">
					<li id="stock"><input type="checkbox" id="stock" class="pointer"></li>
					<li id="regdate" class="pointer">신상품순</li>
					<li id="sellCount" class="pointer">판매순</li>
					<li id="lowPrice" class="pointer">낮은가격</li>
					<li id="highPrice" class="pointer">높은가격</li>
					<li id="pName" class="pointer">상품명</li>
					</ul>
					<script>
						$('#sortBy li').on('click', function(e){
							var sort = e.target.id;
							location.href="<%=request.getContextPath()%>/shopMain.do?key=<%=key%>&sort="+sort;
						});
				</script>
				</div>
				<hr>
				<div id="search_bar_bottom" class="flex">
					<input type="text" placeholder="s e a r c h" onkeydown="Enter_Check();">
					<script>
					function Enter_Check(){
			    		if(event.keyCode == 13){
			    			var key = $(event.target).val();
			    			console.log(key);
			    			location.href="<%=request.getContextPath()%>/shopMain.do?sort=<%=sort%>&key="+key;
			    		}
					}	
				</script>
				<!-- 	<select id="selectBox">
						<option value="8">8개씩 정렬</option>
						<option value="12">12개씩 정렬</option>
						<option value="16">16개씩 정렬</option>
					</select> -->
				</div>
				<div id="items">
					<ul class="flex">
						<%
								int count3 = 0;
							for (Product p : list) {
						%>
						<li>
							<div class="item_border flex">
								<div class="item flex column">
									<div class="item_top">
											<img class="pointer" id="img<%=count3%>" alt="" src="<%=request.getContextPath()%>/img/shopImg/다운로드.jpg">
										<%
										for(PAttachment pat : pAttList){ %>
											<%if(p.getpId() == pat.getpId()){ %>
												<script>
													$('#img'+<%=count3%>).attr("src",'<%=pat.getFilePAth()%>');
												</script>	
										<%	}}
									%>
									</div>
									<div class="item_bottom">
										<input type="hidden" id="pId" name="pId" value="<%=p.getpId()%>"> 
										<span><b>[<%=p.getBrand()%>]</b></span><br> 
										<span class="pointer"><%=p.getpName()%></span><br> <br> 
										<span><%=p.getPrice()%></span>
									</div>
								</div>
							</div>
						</li>
						<%
						count3++;
							}
						%>
					</ul>
					<script>
				$('.item .pointer').on('click', function(){
					var pId = $(this).parents('.item').children().children('input').val();
					location.href="<%=request.getContextPath()%>/shopDetail.do?pId="+pId;
				});
			</script>
				</div>
				<div id="paging" class="flex">
					<%
						if (!list.isEmpty()) {
					%>
					<p>
						<a href="<%=request.getContextPath()%>/shopMain.do?currentPage=1&sort=<%=sort%>&key=<%=key%>">&lt;&lt;</a>
					</p>
					<p id="bfBtn">
						<a href="<%=request.getContextPath()%>/shopMain.do?currentPage=<%=currentPage - 1%>&sort=<%=sort%>&key=<%=key%>">&lt;</a>
					</p>
					<script>
						if (
					<%=currentPage%>
						<= 1) {
							$('#bfBtn').css('display', 'none');
						}
					</script>
					<%
						for (int i = startPage; i <= endPage; i++) {
								if (i == currentPage) {
					%>
					<ol class="flex">
						<li><a href="#" class="this"><span><%=i%></span></a></li>
						<%
							} else {
						%>
						<li><a href="<%=request.getContextPath()%>/shopMain.do?currentPage=<%=i%>&sort=<%=sort%>&key=<%=key%>" class="other"><%=i%></a></li>
					</ol>
					<%
						}
							}
					%>
					<p id="afBtn">
						<a href="<%=request.getContextPath()%>/shopMain.do?currentPage=<%=currentPage + 1%>&sort=<%=sort%>&key=<%=key%>">&gt;</a>
					</p>
					<script>
						if (
					<%=currentPage%>
						>=
					<%=maxPage%>
						) {
							$('#afBtn').css('display', 'none');
						}
					</script>
					<p>
						<a href="<%=request.getContextPath()%>/shopMain.do?currentPage=<%=maxPage%>&sort=<%=sort%>&key=<%=key%>">&gt;&gt;</a>
					</p>
					<%
						}
					%>
				</div>
			</div>
		</div>
	</div>
	<script>
	$(function(){
		var str = "<%=sort%>";
		var str2 = "stock";
		if (str == str2) {
			$('#stock input').prop('checked', true);
		}
	});
	</script>
	<%@ include file="/views/common/coinfooter.jsp"%>
</body>
</html>