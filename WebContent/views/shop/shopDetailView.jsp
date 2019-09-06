<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="product.model.vo.*, java.util.*, shop.model.vo.*"%>
<%@ include file="/views/common/coinheader.jsp"%>
<%
	Product p = (Product) request.getAttribute("p"); // 상품
	int pId = p.getpId();
	PAttachment thumbP = (PAttachment)request.getAttribute("thumbP");

	String option = (String) request.getAttribute("option"); // 옵션
	String[] opArr = option.split("\\/"); // 옵션 배열로 나눔

	ArrayList<Review> rList = (ArrayList<Review>) request.getAttribute("rList"); // 유저의 리뷰와 QnA질문
	ArrayList<Answer> aList = (ArrayList<Answer>) request.getAttribute("aList"); // 관리자의 리뷰 답변과 QnA질문 답변
	ArrayList<RAttachment> attList = (ArrayList<RAttachment>) request.getAttribute("attList"); // 리뷰 사진 리스트
	ArrayList<PAttachment> pAttList = (ArrayList<PAttachment>) request.getAttribute("pAttList"); // 상품 사진 리스트
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ShopDetailView</title>

<link href="<%=request.getContextPath()%>/css/shop/shopDetailView.css?ver=1" rel="stylesheet">
</head>
<body>
	<!--
      해야할 것 :

		1) 상품평 ROWNUM 있는 VIEW 만들어서 컬럼 교체
		2) input [type=text]로 건 CSS 고치기
        3) 배송정보 칸의 안내창(물음표) 이미지 hover이벤트(시간남으면)
   -->
	<!-- 상세 페이지 전체 감싸는 div -->
	<div id="Index" class="flex column" style="height:auto">
		<!-- 소분류 카테고리 -->
		<nav id="shopCategory">
			<ul id="sct_ul">
				<%
					for (int i = 1; i <= 5; i++) {
						String[] str = {"스피커", "헤드셋", "헤드폰 / 이어폰", "블루투스 사운드", "마이크"};
				%>
				<li>
					<div class="sct_btn">
						<a href="<%=request.getContextPath()%>/shopList.do?cid=<%=i * 10%>"><span><%=str[i - 1]%></span></a>
					</div>
				</li>
				<%
					}
				%>
			</ul>
		</nav>
		<!-- 상단 nav 제외 전체 감싸는 div -->
		<div id="content">
			<!-- 사진, 상품정보 영역 감싸는 div -->
			<div id="content_top">
				<!-- 사진 영역 -->
				<div id="ct_col_lft">
					<div id="bigImg">
						<img alt="" src="<%=thumbP.getFilePAth()%>">
					</div>
				</div>
				<!-- // 사진영역 끝 -->
				<!-- 상품정보 영역 -->
				<div id="ct_col_rgt">
					<table>
						<thead>
							<tr>
								<td colspan='2' id="title"><%=p.getpName()%></td>
							</tr>
							<tr>
								<td colspan='2' id="oneOrdPrice"><%=p.getPrice()%>원
								</td>
							</tr>
						</thead>
						<tbody id="itemTableTbody">
							<tr>
								<td>배송정보</td>
								<td>
									<span>택배배송</span> 평균 2일 이내 배송(토,일,공휴일제외) <img alt="안내창" src="<%=request.getContextPath()%>/img/shopImg/question-mark.png">
								</td>
							</tr>
							<!-- 옵션이 있을 시 -->
							<%
								if (!option.isEmpty()) {
							%>
							<tr>
								<td>OPTION</td>
								<td>
									<select required id="select" class="group">
										<option value="" disabled selected>SELECT YOUR OPTION</option>
										<%
											for (int i = 0; i < opArr.length; i++) {
										%>
										<option value="<%=i%>*10"><%=opArr[i]%></option>
										<%
											}
										%>
									</select>
								</td>
							</tr>
							<!-- 옵션이 없을 시 -->
							<%
								} else {
							%>
							<tr>
								<td>수량</td>
								<td>
									<div id="noOption" class="amount_Btns">
										<button class="amount_Btn group" type="button" onclick="plus(); totalPrice(); selectOp();">▲</button>
										<input type="text" value="1">
										<button class="amount_Btn group" type="button" onclick="minus(); totalPrice(); selectOp();">▼</button>
									</div>
								</td>
							</tr>

							<%
								}
							%>
						</tbody>
						<tr>
							<td>합계</td>
							<td id="totPrice"><%=p.getPrice()%></td>
						</tr>
					</table>
					<!-- 장바구니/구매 -->
					<div class="ct_btn">
						<ul id="ct_btn_ul">
							<li>
								<div id="ct_btn_cart">
								<form action="" id="itemform" name="itemForm" method="get">
										<input type="hidden" id="pId" name="pId" value="<%=p.getpId()%>">
										<input type="hidden" id="pName" name="pName" value="<%=p.getpName()%>">
										<input type="hidden" id="price" name="price" value="<%=p.getPrice()%>">
										<input type="hidden" id="brand" name="brand" value="<%=p.getBrand()%>">
										<input type="hidden" id="category" name="category" value="<%=p.getCategory()%>">
										<input type="hidden" id="subCategory" name="subCategory" value="<%=p.getSubCategory()%>">
										<input type="hidden" id="stock" name="stock" value="<%=p.getStock()%>">
										<input type="hidden" id="sellCount" name="sellCount" value="<%=p.getSellCount()%>">
										<input type="hidden" id="option" name="option" value="">
										<input type="hidden" id="regDate" name="regDate" value="<%=p.getRegDate()%>">
										<input type="hidden" id="amount" name="amount" value="1">
										<input type="hidden" id="modifyDate" name="modifyDate" value="<%=p.getModifyDate()%>">
										<button id="cartbtn" style="width: 100%; height: 100%;">CART</button>
									</form>
								</div>
							</li>
							<li>
								<div id="ct_btn_buy">

									<button id="buybtn" style="width: 100%; height: 100%;" form="itemForm">BUY</button>
								</div>
							</li>
						</ul>
					</div>

					<!-- 옵션 선택하면 추가될 tr -->
					<table style="display: none">
						<tbody id="addTr">
							<tr class="amountTr">
								<td colspan="2" class="amount">
									<div class="amount_div">
										<div class="amount_name"></div>
										<div class="amount_dltBtn group" onclick="deleteItem();">X</div>
										<div class="amount_Btns">
											<button class="amount_Btn group" type="button" onclick="plus(); totalPrice(); selectOp();">▲</button>
											<input type="text" value="1">
											<button class="amount_Btn group" type="button" onclick="minus(); totalPrice(); selectOp();">▼</button>
										</div>
										<div class="itemPrice">
											<span class="itemPrice_span"><%=p.getPrice()%></span>원
										</div>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 상품정보 영역 끝 -->
			</div>
			<!-- 사진, 상품정보 감싸는 top영역 끝 -->
			<!-- 하단영역 시작 -->
			<div id="content_bottom">
				<div id="cb_detail">
					<div class="cb_cate">
						<ul>
							<li><a href="#cb_detail" class="clicked_category">DETAIL</a></li>
							<li><a href="#cb_info">INFO</a></li>
							<li><a href="#cb_review">REVIEW </a></li>
							<li><a href="#cb_review">Q&#38;A </a></li>
						</ul>
					</div>
					<%
						for(PAttachment pat : pAttList){ %>
							<img alt="상세정보" src="<%=pat.getFilePAth()%>">
					<%}
					%>
				</div>
				<div id="cb_info">
					<div class="cb_cate">
						<ul>
							<li><a href="#cb_detail">DETAIL</a></li>
							<li><a href="#cb_info" class="clicked_category">INFO</a></li>
							<li><a href="#cb_review">REVIEW </a></li>
							<li><a href="#cb_review">Q&#38;A </a></li>
						</ul>
					</div>
						<div class="cb_info_inner">
							<h3 class="h3_title">상품결제정보</h3>
							<br>
							<div class="text_box">
								고액결제의 경우 안전을 위해 카드사에서 확인전화를 드릴 수도 있습니다.<br> 확인 과정에서 도난 카드의 사용이나 타인 명의의 주문등 정상적인 주문이 아니라고 판단될 경우 임의로 주문을 보류 또는 취소할 수 있습니다.<br> 무통장 입금은 상품 구매 대금은 PC뱅킹, 인터넷뱅킹, 텔레뱅킹 혹은 가까운 은행에서 직접 입금하시면 됩니다.<br> 주문시 입력한 입금자명과 실제 입금자의 성명이 반드시 일치하여야 하며, 7일 이내로 입금을 하셔야 하며 입금되지 않은 주문은 자동취소 됩니다.<br>
							</div>
						</div>
						<div class="cb_info_inner">
							<h3 class="h3_title">배송정보</h3>
							<br>
							<div class="text_box">
								배송 방법: 택배<br> 배송 지역: 전국지역<br> 배송 비용: 3,000원<br> 배송 기간: 2일 ~ 5일<br> 배송 안내: 산간 벽지나 도서지방은 별도의 추가금액을 지불하셔야 하는 경우가 있습니다<br> 고객님께서 주문하신 상품은 입금 확인 후 배송해 드립니다. 다만, 상품 종류에 따라서 상품의 배송이 다소 지연될 수 있습니다.
							</div>
						</div>
						<div class="cb_info_inner">
							<h3 class="h3_title">교환 및 반품정보</h3>
							<br>
							<div class="text_box">
								<b>교환 및 반품정보 교환 및 반품이 가능한 경우</b> <br> - 상품을 공급 받으신 날로부터 10일이내 교환 및 반품이 가능합니다. <br>- 공급받으신 상품 및 용역의 내용이 표시.광고 내용과 다르거나 다르게 이행된 경우에는 공급받은 날로부터 3월이내, 그사실을 알게 된 날로부터 30일이내 <br> <br> <b>교환 및 반품이 불가능한 경우</b> <br> - 고객님의 책임있는 사유로 상품등이 멸실 또는 훼손된 경우. 단, 상품의 내용을 확인하기 위하여 포장 등을 훼손한 경우는 제외 <br>- 포장된 상품의 일부를 소모하거나 훼손되어 상품가치가 하락된 경우(주문하신 상품 외 기타 동봉된 내용물은 해당사항 없음) <br>- 고객님의 사용 또는 일부 소비에 의하여 상품의 가치가 현저히 감소한 경우 <br>- 시간의 경과에 의하여 재판매가 곤란할 정도로 상품등의 가치가 현저히 감소한 경우 <br>- 복제가 가능한 상품등의 포장을 훼손한 경우 (자세한 내용은 카카오톡 1:1 / E-MAIL / 전화 상담을 이용해 주시기 바랍니다.) <br> <br>※ 고객님의 마음이 바뀌어 교환, 반품을 하실 경우 상품반송 비용은 고객님께서 부담하셔야 합니다.
							</div>
						</div>
				</div>
				<div id="cb_review">
					<div class="cb_cate">
						<ul>
							<li><a href="#cb_detail">DETAIL</a></li>
							<li><a href="#cb_info">INFO</a></li>
							<li class="switch"><a href="#cb_review" class="clicked_category">REVIEW </a></li>
							<li class="switch"><a href="#cb_qna">Q&#38;A </a></li>
						</ul>
					</div>

					<span id="write" class="write"> <a>WRITE</a></span>
					<div class="cb_review_inner">
						<table class="cb_table">
							<tr class="table_header">
								<td>No</td>
								<td>TITLE</td>
								<td>WRITER</td>
								<td>DATE</td>
								<td>VIEW</td>
							</tr>
							<%
								if (rList.isEmpty()) {
							%>
							<tr>
								<td colspan="5">게시글이 없습니다.</td>
							</tr>
							<!-- DB 데이터 수에 따라 추가될 tr 부분 시작
								// 유저글	-->
							<%
								} else {
									for (int i = 0; i < rList.size(); i++) {
										if (rList.get(i).getrType() == 1) {
							%>
							<tr class="reviewTitle" id="reviewTitle<%=i%>">
								<td id="rId<%=i%>" class="rId"><%=rList.get(i).getrId()%></td>
								<td id="rTitle<%=i%>" class="rTitle"><%=rList.get(i).getrTitle()%></td>
								<td id="rWriter<%=i%>" class="writer"><%=rList.get(i).getrWriter()%></td>
								<td id="modifyDate<%=i%>"><%=rList.get(i).getModifyDate()%></td>
								<td id="rcount<%=i%>" class="rCount"><%=rList.get(i).getrCount()%></td>
							</tr>
							<tr class="reviewContent" style="display: none;">
								<td colspan="5">
									<div class="text_box">
										<blockquote id="rContent<%=i%>" class="fromUser">
											<div class="fromUserImg">
												<%
													// 리뷰 이미지가 있을 시
																for (int k = 0; k < attList.size(); k++) {
																	if (!attList.isEmpty() && rList.get(i).getrId() == attList.get(k).getrId()) {
												%>
												<div id="hidden<%=k%>" class="hidden" style="display: none;"><%=attList.get(k).getChangeName()%></div>
												<div id="reviewImg_div<%=k%>" class="reviewImg_div">
													<img id="reviewImg<%=k%>" class="reviewImg" src="<%=request.getContextPath()%>/review_uploadFiles/<%=attList.get(k).getChangeName()%>">
												</div>
												<%
													}
																}
												%>
											</div>
											<textArea id="rtArea<%=i%>" class="tArea" readonly="readonly"><%=rList.get(i).getrContent()%></textArea>
										</blockquote>
										<span id="updateR<%=i%>" class="update" style="display: none"> <a>UPDATE</a></span>
									</div>

									<%
										// 관리자 답변
													for (int j = 0; j < aList.size(); j++) {
														if (!aList.isEmpty() && rList.get(i).getrId() == aList.get(j).getaRId()) {
									%>
									<input type="hidden" id="aId<%=j%>" value="<%=aList.get(j).getaId()%>">
									<input type="hidden" id="a_rId<%=j%>" value="<%=aList.get(j).getaRId()%>">
									<div class="text_box_title">
										<blockquote>
											<b id="aWriter<%=j%>" class="writer"><%=aList.get(j).getaWriter()%></b>&nbsp;&nbsp;
											<p id="aModifyDate<%=j%>"><%=aList.get(j).getModifyDate()%></p>
										</blockquote>
									</div>
									<div class="text_box">
										<blockquote id="aContent<%=j%>">
											<textArea id="tArea<%=j%>" class="tArea" readonly="readonly"><%=aList.get(j).getaContent()%></textArea>
										</blockquote>
										<span id="updateA<%=j%>" class="update" style="display: none"> <a>UPDATE</a>
										</span>
									</div>
									<%
										}
													}
									%>
								</td>
							</tr>
							
							<%
								}
									}
								}
							%>
							<!-- DB 데이터 수에 따라 추가될 tr 부분 끝 -->
						</table>
						
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		// 장바구니 SUBMIT
			$('#cartbtn').click(function(){
		   $('#itemform').attr('action', '<%=request.getContextPath()%>/cart.ca');
		   $('#itemform').submit();
		});
		
		// 팝업
			$('.write').on('click', function(){
				var pId = $('#pId').val()
				console.log(pId);
				
				var str = $(event.currentTarget).prev().find('.clicked_category').attr('href');
				var str2 = '#cb_review';
				var type;
				var title;
								
				if(str == str2){ // type == 1(리뷰작성)
					title = "상품평 등록";
					type = 1;
				} else {
					title = "QNA 작성";
					type = 2;
				}
				
				var url = "<%=request.getContextPath()%>/views/shop/insertReview.jsp?pId="+pId+"&type="+type; 
				
				var option = "width=500, height=550, left=850, top=150, scrollbars, resizable, toolbar = no"
				var win = window.open(url, "title", option);
				
			});

		// 옵션이 선택되면 td추가
		$('#select').on("change", function() {
			var item = $(this).children('option:selected').text();
			$('#addTr .amount_name').text(item);
			for (i = 0; i <= $('#itemTableTbody .amount_name').length; i++) {
				if (item == $('#itemTableTbody .amount_name').eq(i).text()) {
					alert('이미 동일한 항목이 선택되어 있습니다.');
					return;
				}
			}
			var tr = $('#addTr').html();
			$('#itemTableTbody').append(tr);
		});
		// X누르면 td 제거
		function deleteItem() {
			$(event.target).closest('.amountTr').remove();
		}
		// puls
		function plus() {
			var currVal = parseInt($(event.target).next().val()) + 1;

			if (currVal <= 5) {
				$(event.target).next().val(currVal);
				$(event.target).parent().next().children().text(<%=p.getPrice()%>* currVal);
			} else {
				alert('최대 주문가능수량은 5개 입니다.');
			}
		}

		// minus
		function minus() {
			var currVal = parseInt($(event.target).prev().val()) - 1;
			if (currVal > 0) {
				$(event.target).prev().val(currVal);
				$(event.target).parent().next().children().text(<%=p.getPrice()%> * currVal);
			}
		}

		// 합계금액
		function totalPrice() {
			var totalPrice = 0;
			var eleCount = $('.itemPrice_span').length - 1;

			<%if (!option.isEmpty()) {%>
			for (var i = 0; i < eleCount; i++) {
				totalPrice += parseInt($('#itemTableTbody .itemPrice_span').eq(i).text());
			}
			<%} else {%>
				totalPrice = <%=p.getPrice()%> * $('#noOption input[type=text]').val();
			<%}%>
			$('#totPrice').text(totalPrice);
			$('#totalPrice').val(totalPrice);
			console.log(totalPrice);
		}

		$('.group').on('click', function() {
			totalPrice();
			selectOp();
		});

		// 옵션
		function selectOp() {
			var selectOption = "";
			console.log(1234);
			var eleCount = $('.itemPrice_span').length - 1;
			var amount = 0;
			<%if (!option.isEmpty()) {%>
			for (var i = 0; i < eleCount; i++) {
				if (i == eleCount - 1) {
					selectOption += $('.amount_name').eq(i).text() + ","
							+ $('#Index input[type=text]').eq(i).val();
					amount += parseInt($('#Index input[type=text]').eq(i).val());
				} else {
					selectOption += $('.amount_name').eq(i).text() + ","
							+ $('#Index input[type=text]').eq(i + 1).val() + ",";
					amount += parseInt($('#Index input[type=text]').eq(i).val());
				}
			}
			<%} else {%>
				amount = $('#noOption input[type=text]').val();
			<%}%>
			console.log(selectOption);
			console.log(amount);
			$('#option').val(selectOption);
			$('#amount').val(amount);
		}

		// 리뷰 수정
			$(document).on('click', '.update', function(e){
			<%if (loginUser != null) {%>
			var pId = "<%=p.getpId()%>";
			var i = e.currentTarget.id.match(/\d+/); // 몇번째인지 구해오기
			var letter = e.currentTarget.id.substr(6,1); // 리뷰글인지 qna인지 구분하기 위해서
			var rContent; // 글내용
			var rId; // 글번호
			var a_rId;
			var imgName = "";
			console.log(letter);
			var pName = "<%=p.getpName()%>";

			if(letter == 'R') { // 유저글이면
					$('#rtArea'+i).attr('readonly', false);
					$('#updateR'+i).children('a').css('color', '#E55451')

					$(this).on('click', function(){
						var rId = $('#rId'+i).text(); // 글번호(테이블 시퀀스넘버)
						var content = $('#rtArea'+i).val();
						var rContent = content.trim();

						$.ajax({
							url: "updateReview.do",
							type: "post",
							data: {rId:rId, rContent:rContent},
							success:function(data){
								console.log(data.result);
								if(data.result>0){
									$('#updateR'+i).children('a').css('color', '');
									$('#rtArea'+i).text(data.rContent);
									$('#rModifyDate'+i).text(data.modify_date);
									alert("상품평 수정이 완료되었습니다.");
								} else {
									alert("상품평 수정에 실패했습니다.");
								}
									updateBtn();
							}
						});
					});

			} else {						// 관리자글이면
				$('#tArea'+i).attr('readonly', false);
				$('#updateA'+i).children('a').css('color', '#E55451')

				$(this).on('click', function(){
					var a_rId = $('#a_rId'+i).val(); // 글번호(테이블 시퀀스넘버)
					var content = $('#tArea'+i).val();
					var aContent = content.trim();
					var aId = $('#aId'+i).val();

					$.ajax({
						url: "updateAnswer.do",
						type: "post",
						data: {a_rId:a_rId, aContent:aContent, aId:aId},
						success:function(data){
							console.log(data.result);
							if(data.result>0){
								$('#updateA'+i).children('a').css('color', '');
								$('#tArea'+i).text(data.aContent);
								$('#aModifyDate'+i).text(data.modify_date);
								alert("답변 수정이 완료되었습니다.");
							} else {
								alert("답변 수정에 실패했습니다.");
							}
								updateBtn();
						}
					});
				});
			}
			<%}%>
		});
		console.log( $('.table_header'));

		// review <=> qna switch
		// qna랑 review ajax로 가져오는거
		$('.switch').on('click', function (){
			var type;
			var str = $(event.target).text().length;
			var str2 = "QNA".length + 1;
			if(str != str2){
				type = 1;
				$(event.currentTarget).next().children().attr('class', '');
				$(event.target).attr('class', 'clicked_category');
			} else{
				type = 2;
				$(event.currentTarget).prev().children().attr('class', '');
				$(event.target).attr('class', 'clicked_category');
			}
			reviewPrint(type);
		});
		
		function reviewPrint(type){
			var pId = '<%=pId%>';
			$.ajax({
				url: "shopReview.do",
				type: "post",
				data: {pId:pId, type:type},
				success:function(data){
					console.log("헤더 찍힘");
					$cb_table = $(".cb_table");
					$table_header = $('.table_header').html();
					$cb_table.html("");
					var $headerTr = $("<tr>").attr('class','table_header');
					$headerTr.append($table_header);
					$cb_table.append($headerTr);

					// 사용자 글이 있을 시 찍어내는 for문
					for(var i = 0; i < data.rList.length; i++) {
						var $tr = $("<tr>").attr({'class':'reviewTitle','id':'reviewTitle'+i});
						var $rIdTd = $("<td>").text(data.rList[i].rId).attr({'class':'rId','id':'rId'+i});
						var $rtitleTd = $("<td>").text(data.rList[i].rTitle).attr({'class':'rTitle','id':'rTitle'+i});
						var $rWriterTd = $("<td>").text(data.rList[i].rWriter).attr({'class':'rWriter','id':'rWriter'+i});
						var $rModityDateTd = $("<td>").text(data.rList[i].rModifyDate).attr({'class':'rModifyDate','id':'rModifyDate'+i});
						var $rCountTd = $("<td>").text(data.rList[i].rCount).attr({'class':'rCount','id':'rCount'+i});

					 	$tr.append($rIdTd);
						$tr.append($rtitleTd);
						$tr.append($rWriterTd);
						$tr.append($rModityDateTd);
						$tr.append($rCountTd);
						$cb_table.append($tr);

						var $tr2 = $('<tr>').attr('class', 'reviewContent').css('display', 'none');
						var $td = $('<td>').attr('colspan', '5');
						var $div = $('<div>').attr('class','text_box');
						var $blockquote = $('<blockquote>').attr({'id':'rContent'+i,'class':'fromUser'});
						var $fromUserImgDiv = $('<div>').attr('class', 'fromUserImg');
						// 리뷰 사진이 있다면 찍는 for문
						for(var k = 0; k < data.attList.length; k++){
							if(data.attList[k].rId == data.rList[i].rId) {
								var $hiddenDiv = $('<div>').attr('id','hidden'+k).css('display','none').text(data.attList[k].changeName);
								var $div2 = $('<div>').attr({'id':'reviewImg_div'+k,'class':'reviewImg_div'});
								var $img = $('<img>').attr({'id':'reviewImg'+k, 'class':'reviewImg', 'src':'<%=request.getContextPath()%>/review_uploadFiles/'+data.attList[k].changeName});
								$div2.append($img);
								$fromUserImgDiv.append($hiddenDiv);
								$fromUserImgDiv.append($div2);
							}
							if(k >= 2) break;
						}
						var $textDiv = $('<textArea>').attr({'class':'tArea','readonly':'true','id':'rtArea'+i}).val(data.rList[i].rContent);
						var $span = $('<span>').css('display', 'none').attr({'class':'update','id':'updateR'+i});
						var $a = $('<a>').text('UPDATE');

						$span.append($a);
						$blockquote.append($fromUserImgDiv);
						$blockquote.append($textDiv);
						$div.append($blockquote);
						$div.append($span);
						$td.append($div);

							// 사용자 글 하나 끝
							// 관리자 답변이 있을 시에 대한 for문 시작
							for(var j = 0; j < data.aList.length; j++ ){
									console.log('들어옴?2');
									console.log(data.rList[i].rId);
									console.log(data.aList.length);
								if(data.rList[i].rId == data.aList[j].aRId) {
									var $div3 = $('<div>').attr('class', 'text_box_title');
									var $hidden = $('<input type=hidden>').attr('id', 'aId'+i).val(data.aList[j].aId);
									var $hidden2 = $('<input type=hidden>').attr('id', 'a_rId'+i).val(data.aList[j].aRId);
									var $blockquote2 = $('<blockquote>');
									var $b = $('<b>').attr({'class':'writer','id':'aWriter'+j}).text(data.aList[j].aWriter);
									var $p = $('<p>').attr('id', 'aModifyDate'+j).text(data.aList[j].aModifyDate);
									var $div4 = $('<div>').attr('class', 'text_box');
									var $blockquote3 = $('<blockquote>').attr('id', 'aContent'+j);
									var $textArea = $('<textArea>').attr({'class':'tArea','readonly':'true','id':'tArea'+j}).val(data.aList[j].aContent);
									var $span2 = $('<span>').attr({'class':'update','id':'updateA'+j}).css('display', 'none');
									var $a2 = $('<a>').text('UPDATE');

									$blockquote2.append($b);
									$blockquote2.append('&nbsp;&nbsp;');
									$blockquote2.append($p);
									$div3.append($hidden);
									$div3.append($hidden2);
									$div3.append($blockquote2);

									$span2.append($a2);
									$blockquote3.append($textArea);
									$div4.append($blockquote3);
									$div4.append($span2);

									$td.append($div3);
									$td.append($div4);
								}
							}

						$tr2.append($td);
						$cb_table.append($tr2);

					}
				}
			});
		};

		// 리뷰 제목 누르면 펼쳐지는 이벤트
	/* 	function countPlus(e){
			if(e == $('.reviewTitle')){
				$(e.target).next('tr').toggle();
			}
		} */
		
		$(document).on("click",".reviewTitle",function() {
			var rId = $(this).children('.rId').text();
			var reviewTitle = $(this);
			console.log("누구?:" + reviewTitle);
			
		 	$.ajax({
				url: "reviewCount.do",
				type: "get",
				data: {rId:rId},
		 		success:function(data){
					if(data.result > 0) {
						reviewTitle.children('.rCount').text(data.count);
						console.log(data.count);
						reviewTitle.next('tr').toggle();
					} else {
					}
		 		}
		 	});
		 });	
		
		// review와 QnA에서 로그인 유저의 아이디와 일치하면 수정하는 버튼 보이기
		 $(function(){
			 updateBtn();
		 });
		 $(window).ajaxComplete(function(){
			 updateBtn();
		 });

		function updateBtn(){
			console.log("updateBtn");
			for(var i = 0; i < <%=rList.size()%>; i++) {
				<%if (loginUser != null) {%>
					var rWriter = $('#rWriter'+i); /* 리뷰 작성자 */
					var aWriter = $('#aWriter'+i); /* 리뷰 답변인 */
					var userId = "<%=loginUser.getId()%>";
				if (userId == $('#rWriter' + i).text()) {
					$('#updateR' + i).css('display','inline-block');
				} else if (userId == $('#aWriter' + i).text()) {
					$('#updateA' + i).css('display','inline-block');
				}
	<%}%>
		}
		}
		// 창 사이즈 줄어들면 화면 css변경
		var windowWidth = $(window).width();
		function cssResize() {
			if (windowWidth <= 900) {
				$('#content_top').css('flex-direction', 'column');
				$('#ct_col_lft').css({
					'width' : '100%',
					'margin-bottom' : '3vh'
				});
				$('#ct_col_rgt').css('width', '100%')
						.css('font-size', '1.2rem');
			} else {
				$('#content_top').css({
					'flex-direction' : '',
				});
				$('#ct_col_lft').css({
					'width' : '',
					'margin-bottom' : ''
				});
				$('#ct_col_rgt').css('width', '').css('font-size', '');
			}
		}
		$(function() {
			cssResize();
		});
		$(window).resize(function() {
			windowWidth = $(window).width();
			cssResize();
		});
		$('#cartbtn').click(function(){
			   $('#itemform').attr('action', '<%=request.getContextPath()%>/cart.ca');
			   $('#itemform').submit();
			});
		$('#buybtn').click(function(){
			   $('#itemform').attr('action', '<%=request.getContextPath()%>/cart.ca');
			   $('#itemform').submit();
			});
	</script>
	<div >	</div>

</body>
<%@ include file="/views/common/coinfooter.jsp"%>
</html>
