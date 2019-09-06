<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="textBoard.model.vo.*" import="java.util.ArrayList" %>
<% TextBoard tb = (TextBoard)request.getAttribute("tb"); 
   ArrayList<TextBoardReply> list = (ArrayList<TextBoardReply>)request.getAttribute("list");
   Member loginInfo = (Member) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<style>
body {
	background-color: rgb(40, 44, 52) !important;
}
.ht {
	background-color: rgb(40, 44, 52) !important;
	color: #fff;
	margin-bottom:400px;
}

.container .card{
	color: #000;
}
.media img{
	width: 50px;
	hieght:50px;
}
.dtr{
	cursor: pointer;
}
</style>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/header.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%@ include file="../common/coinheader.jsp" %>

<body>

<div class="container ht">
	<div class="py-5 text-center">
		<a href="<%= request.getContextPath() %>/list.tb"><img src="<%= request.getContextPath() %>/img/Search/freeBoardTitle.png"></a>
	</div>
    <div class="row">
      <div class="col-lg-12">
      <form method="post" action="<%= request.getContextPath() %>/views/textBoard/textBoardUpdateForm.jsp">
        <h1 class="mt-4"><%= tb.getTbTitle() %></h1>
        <input type="hidden" name="tbtitle" value=<%= tb.getTbTitle() %>>
        <p class="lead">
        	<input type="hidden" name="tbwriter" value=<%= tb.getTbWriter() %>>
          	작성자 : <%= tb.getTbWriter() %> 
        </p>
        <hr>
        <% if(tb.getCreateDate().equals(tb.getModifyDate())){ %>
        <p>작성일 : <%= tb.getCreateDate() %></p>
        <input type="hidden" name="createDate" value=<%= tb.getCreateDate() %>>
		<% } else { %>
		<p>작성일 : <%= tb.getCreateDate() %></p>
		<p><%= tb.getCreateDate() %> 수정됨 </p>
		<p>조회 : <%= tb.getTbCount() %></p>
		<% } %>
		
        <hr>
		<input type="hidden" name="tbcontent" value=<%= tb.getTbContent() %>>
        <p><%= tb.getTbContent() %></p>
        <% if(loginUser!=null) { %>
        <div class="py-5 text-center">
        	<% if(tb.getTbWriter().equals(loginInfo.getNickName()) || loginUser.getNickName().equals("관리자")) { %>
					<input type="button" class="btn btn-danger" style="display:inline;" value="게시글 삭제" onclick="deletetb(<%= tb.getTbid()%>);">
			<% } %>
			<% if(tb.getTbWriter().equals(loginInfo.getNickName())) { %>
				 <input type="hidden" name="tbid"  value=<%= tb.getTbid() %>>
				 <input type="hidden" name="tbtype" value=<%= tb.getTbType() %>>
				 <input type="submit" class="btn btn-info" value="게시글 수정">
			<% } %>
		<% } %>
		</div>
		</form>
        <hr>
		<% if(loginUser==null) { %>
		<% } else { %>
        <div class="card my-4">
          <h5 class="card-header">댓글 남기기 : </h5>
          <div class="card-body">
            <form>
              <div class="form-group">
                <textarea id="replyText" class="form-control" rows="3"></textarea>
              </div>
              
              <button type="button" class="btn btn-primary" id="replySubmit">작성</button>
              <script>
      		    $("#replySubmit").click(function(){
      	        var rwriter = '<%= loginInfo.getId() %>';
      			var tbid = <%= tb.getTbid() %>
      			var content = $('#replyText').val();
      			
      			$.ajax({
      				url: "insertrp.tb",
      				type: "post",
      				data: {rwriter:rwriter, content:content, tbid:tbid},
      				success: function(data){
		      					  setTimeout(function() 
		      					  {
		      					    location.reload();
		      					  }, 100);
      						}	
      					});
      				});
              </script>
            </form>
          </div>
        </div>
        <% } %>
		<% if(list.isEmpty()) { %>
        <div class="media mb-4">
         
          	<div class="media-body">
          		<h5 style="text-align:center;"> 댓글이 없습니다. </h5>
          	</div>
        </div>
        <% } else { %>
        	<% for(int i=list.size()-1; i>=0;i--){ %>
		        <div id="reply-Area"class="media mb-4">
		          <img class="d-flex mr-3 rounded-circle" src="<%= request.getContextPath() %>/img/karaoke/mic.png">
		          <div class="media-body">
		            <h5 class="mt-0"><%= list.get(i).getTrwriter() %></h5>
		            <% if(loginUser!=null) { %>
		            	<% if(loginInfo.getNickName().equals(list.get(i).getTrwriter()) || loginInfo.getNickName().equals("관리자"))  { %>
		            <span class="dtr" onclick="deletetr(<%= list.get(i).getTrid() %>);" style="display:inline; float:right;">삭제</span>
		            	<% } else { %>
		            <span></span>
		    			<% } %>
		    		<% } %>
		           	<p><%= list.get(i).getTrcontent() %></p>
		          </div>
		        </div>
		    <% } %>
        <% } %>
      </div>
	</div>
</div>
<script>
function deletetr(trid){
	var trid = trid;
	var bool = confirm("댓글을 삭제하시겠습니까?");
	if(bool){
		location.href='<%= request.getContextPath( )%>/deletetr.tb?tbid=<%=tb.getTbid() %>&trid=' + trid;
	}
};
function deletetb(tbid){
	var tbid = tbid;
	var bool = confirm("정말로 게시글을 삭제하시겠습니까?");
	if(bool){
		location.href='<%= request.getContextPath( )%>/delete.tb?tbid=<%=tb.getTbid() %>';
		alert("삭제되었습니다.");
	}
};
</script>
</body>
</html>
<%@ include file="../common/coinfooter.jsp"%>