<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="textBoard.model.vo.*, java.util.*, common.PageInfo" %>
<%
	ArrayList<TextBoard> list = (ArrayList<TextBoard>)request.getAttribute("list");
	ArrayList<TextBoard> nlist = (ArrayList<TextBoard>)request.getAttribute("nlist");
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
<style>
body{
	background-color:rgb(40, 44, 52) !important;
	color: #fff;
}

.bm {
	padding-bottom: 200px;
	color: #000;	
}



.sortArea{
	margin: 24px 0;
	width:100%;
	display: block;
}


@media screen and (max-width: 1024px){
	.sortArea {
	    margin: 0;
	    padding: 0.625rem 1.25rem;
	}
}
.leftA {
	width:100px;
	height:50px;
	display:block;
	padding:0px;
	float:left;
	margin:0px;
	padding:0px;
}
.leftA select{
	width:100%;
	height:70% !important;
}


.notice {
	width: 100%;
	display:block;
}
.infoLine ol ul li {
	list-style:none !important;
	list-style-type: none !important;
}

.title{
    width: 50%;
    box-sizing: border-box;
    color: #000;
}
.infoLine{
	display: block;
}

.sortArea:after {
    display: block;
    content: '';
    height: 0;
    font-size: 0;
    opacity: 0;
    clear: both;
}



.contentArea{
	padding-top: 4em;
	padding-bottom: 4em;
	width: 100%;
	
}
.container{
	margin-right: 0px;
}
.bc {
	margin-top: 3em;
	margin-bottom: 2em;
	background-color: #2c2c36;
	border-radius: 8px;
	
}

.pagination{
	padding-top: 8px;
}
table{
	margin-bottom:0px !important;
}
table th{
	text-align: center;
}
table td{
	text-align: center;
}
</style>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%@include file="../common/coinheader.jsp" %>
<body>
<div class="container bm">
	<div class="py-5 text-center">
		<img src="<%= request.getContextPath() %>/img/Search/freeBoardTitle.png">
	</div>
	<div class="sortArea">
		<!-- <div class="leftA">
			<select class="form-control">
				<option>최신순</option>
				<option>조회순</option>
				<option>댓글순</option>
			</select>
		</div> -->
		<% if(loginUser !=null){ %>
			<a class="btn btn-primary pull-right" href="<%= request.getContextPath() %>/views/textBoard/textBoardInsertForm.jsp?tbtype=1">글쓰기</a>
		<% } %>
		<% if(loginUser !=null && loginUser.getNickName().equals("관리자")){ %>
			<a class="btn btn-primary pull-right" href="<%= request.getContextPath() %>/views/textBoard/textBoardInsertForm.jsp?tbtype=2">공지사항작성</a>
		<% } %>
		
	</div>
	<!--  -->
	<div class="container bc">
		<div class="contentArea">
			<table class="table table-dark" id="list-notice">
				<thead>
					<tr>
						<th style="width:9%;">번호</th>
						<th style="width:29%;">제목</th>
						<th style="width:21%;">작성자</th>
						<th style="width:32%;">날짜</th>
						<th style="width:9%;">조회수</th>
					</tr>
				</thead>
			</table>
			<table class="table table-secondary table-hover" id="list">
				<% for(TextBoard tb : nlist) { %>
					<% if(tb.getTbType()==2){ %>
						<tbody>
							<tr>
								<td><%= tb.getTbid() %><input type="hidden" value="<%= tb.getTbid() %>"></td>
								<td><strong>[공지]</strong><%= tb.getTbTitle() %></td>
								<td><%= tb.getTbWriter() %></td>
								<td><%= tb.getCreateDate() %></td>
								<td><%= tb.getTbCount() %></td>				
							</tr>
						</tbody>
					<% } %>
				<% } %>
			</table>
			<table class="table table-dark table-hover" id="list">
				<% if(list.isEmpty()){ %>
					<tr>
						<td colspan="5" style="text-align:center;">
						게시글이 없습니다.
						</td>
					</tr>
				<% } else { %>
					<% for(TextBoard tb : list) { %>
						<% if(tb.getTbType()==2){ %>
							<tbody>
								<tr>
									<td style="width:9%;"><%= tb.getTbid() %><input type="hidden" value="<%= tb.getTbid() %>"></td>
									<td style="width:29%;"><strong>[공지]</strong><%= tb.getTbTitle() %></td>
									<td style="width:21%;"><%= tb.getTbWriter() %></td>
									<td style="width:32%;"><%= tb.getCreateDate() %></td>
									<td style="width:9%;"><%= tb.getTbCount() %></td>				
								</tr>
							</tbody>
						<% } else {%>
							<tbody>
								<tr>
									<td style="width:9%;"><%= tb.getTbid() %><input type="hidden" value="<%= tb.getTbid() %>"></td>
									<td style="width:29%;"><%= tb.getTbTitle() %></td>
									<td style="width:21%;"><%= tb.getTbWriter() %></td>
									<td style="width:32%;"><%= tb.getCreateDate() %></td>
									<td style="width:9%;"><%= tb.getTbCount() %></td>				
								</tr>
							</tbody>
						<% } %>
					<% } %>
				<% } %>
			</table>
			 
			<!-- <form action=list.tb?select&searchText>
			<div class="text-center" style="padding-top:3em;">
				<select class="selectpicker" name="select">
				  <option value=0>전체</option>
				  <option value=1>제목</option>
				  <option value=2>제목 + 내용</option>
				  <option value=3>글쓴이</option>
				</select>
				<input type="text" name="searchText" placeholder="검색할 내용을 입력하세요">
				<button type="submit" class="btn btn-primary">검색</button>
			</div>
			</form> -->
			<div class="text-center">
				<ul class="pagination">
					<% if(!list.isEmpty()){ %>
					<li><a href="<%= request.getContextPath() %>/list.tb?currentPage=1">&lt;&lt;</a></li>
					<li><a id="bp" href="<%= request.getContextPath() %>/list.tb?currentPage=<%=  currentPage - 1%>">&lt;</a></li>
					
					<% for(int p=startPage; p<=endPage; p++){ %>
						<% if(p == currentPage){ %>
							<li><a href="#"><%= p %></a></li>
						<% } else if(p > currentPage) { %>
							<li><a href="<%= request.getContextPath() %>/list.tb?currentPage=<%= currentPage + 1 %>"><%= p %></a></li>
						<% } else { %>
							<li><a href="<%= request.getContextPath() %>/list.tb?currentPage=<%= currentPage - 1 %>"><%= p %></a></li>
						<% } %>
					<% } %>
					<li><a id="np" href="<%= request.getContextPath() %>/list.tb?currentPage=<%= currentPage + 1 %>">&gt;</a></li>
					<li><a href="<%= request.getContextPath() %>/list.tb?currentPage=<%= maxPage %>">&gt;&gt;</a></li>
					<% } %>
				</ul>
			</div>
		</div>
	</div>
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
	<script>
	$(function(){
		$('#list td').mouseenter(function(){
            $(this).parent().css({'cursor':'pointer'});
            var tbid = $(this).parent().children().children('input').val()
            console.log(tbid);
        }).click(function(){
           var tbid = $(this).parent().children().children('input').val();
           location.href="<%= request.getContextPath() %>/detail.tb?tbid=" + tbid;
		});
	});
	
	</script>
</body>
<%@ include file="../common/coinfooter.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/header.js"></script>
</html>