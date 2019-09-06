<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="shop.model.vo.*, product.model.vo.*, java.util.*"%>
<%
	String pId = request.getParameter("pId");
	String pName = (String) (request.getParameter("pName"));
	String rId = request.getParameter("rId");
	String rContent = (String) (request.getParameter("rContent"));
	String imgName = (String)(request.getParameter("imgName"));
	String[] arr = null;
	if(!imgName.equals("///")){
		arr = imgName.split("\\/");
	}
	
	System.out.println(pId);
	System.out.println(rId);
%>

<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<meta charset="UTF-8">
<title>shopReviewView</title>
<style type="text/css">
*:focus {
	outline: none;
}

body {
	margin: 0;
	padding: 0;
	overflow: hidden;
}

#Index {
	width: 600px;
	display: flex;
	justify-content: center;
	background-color: rgb(42, 45, 52);
}

.pointer {
	cursor: pointer;
}

#wrap {
	width: 90%;
	color: rgb(210, 210, 210);
	position: relative;
	display: flex;
	flex-direction: column;
	justify-content: center;
	height: 700px;
}

#submitForm {
	width: 90%;
	height: 100%;
}

#upper {
	margin-top: -3%;
	width: 100%;
	border-bottom: 2px solid rgb(210, 210, 210);
	text-align: center;
}

#upper p {
	font-size: 1.3rem;
}

.button_close {
	display: inline-block;
	width: 5vw;
	height: 5vh;
	background: none;
	font-size: 1.5rem;
	font-weight: 100;
	border: none;
	color: rgb(210, 210, 210);
	position: absolute;
	top: 1.5vh;
	right: 1vw;
}

#selected_info {
	display: flex;
	margin-bottom: 5%;
	margin-top: 5%;
}

img {
	width: 10vw;
	height: 10vh;
	margin: 1vw;
}

#item_info {
	margin-left: 2%;
	font-size: 0.9rem;
}

#score {
	width: 100%;
	height: 10vh;
	display: flex;
	justify-content: center;
	align-items: center;
}

#star_area img {
	width: 15px;
	height: 15px;
	padding: 0;
	margin: 1%;
}

.review {
	width: 100%;
	display: flex;
	justify-content: center;
	margin-bottom: 5%;
}

#review_textarea {
	display: block;
	width: 100%;
	height: 100%;
	padding: 2%;
	background-color: rgb(75, 75, 75);
}

#review_textarea textArea {
	background-color: rgb(75, 75, 75);
	width: 90%;
	height: 40vh;
	border: none;
	resize: none;
	border: none;
	color: rgb(210, 210, 210);
	border: none;
}

#review_attach {
	width: 100%;
	display: flex;
	justify-content: center;
	align-content: center;
	margin-bottom: 5%;
	margin-top: 7%;
}

#review_attach_inner {
	width: 100%;
	display: inline-flex;
}

#review_attach #thumUl {
	margin: 0;
	padding: 0;
	display: flex;
	width: 59%;
}

#review_attach #submitUl {
	margin: 0;
	padding: 0;
	display: flex; align-items : center;
	width: 41%;
	align-items: center;
}

#review_attach #thumUl li, #review_attach #submitUl li {
	display: inline-flex;
	width: 100%;
}

.add_thmb {
	width: 90%;
	height: 60px;
	background-color: #9e9b9b;
	display: flex;
	border: 1px solid black;
	cursor: pointer;
	align-items: center;
	justify-content: center;
}

.add_thmb img {
	width: 90%;
	height: 90%;
	margin: 0;
	padding: 0;
}

#submitBtn, #cencleBtn {
	width: 100%;
	height: 50px;
	background-color: rgb(25, 75, 75);
	border: none;
	color: rgb(210, 210, 210);
	margin-left: 1vw;
	cursor: pointer;
}

#cencleBtn {
	background-color: rgb(75, 75, 75);
}
</style>
</head>
<body>
	<div id="Index">
		<div id="wrap">
			<form id="file_form" method="post" enctype="multipart/form-data">
			<input type="hidden" id="pId" name="pId"> 
			<div id="upper">
				<p>상품평 수정</p>
				<button class="button_close" type="button" onclick="self.close();">X</button>
			</div>
			<div id="selected_info">
				<div id="selecetd_img">
					<img alt="상품명" src="../../img/shopImg/photo2.jpg">
				</div>
				<div id="item_info">
					<p id="item"><%=pName%></p>
				</div>
			</div>
			<div class="review">
				<div id="review_textarea">
					<textArea id="textArea" placeholder="상품평 내용(최대 2000자)을 입력해 주세요."><%=rContent%></textArea>
					<span id="byteCheck">0</span>
					<script>
							String.prototype.bytes = function() {
							 var msg = this;
							 var cnt = 0;
							//한글이면 2, 아니면 1 count 증가
							 for( var i=0; i< msg.length; i++) 
							  cnt += (msg.charCodeAt(i) > 128 ) ? 2 : 1;  
							 return cnt;
							}

							//textarea에서 키를 입력할 때마다 동작
							$("#textArea").keyup(function( e ){
							 msg_length = $(this).val().bytes(); 
							
							 if( msg_length <= 3999 ) {     
							  $("#byteCheck").css("color", "#9e9b9b");    
							  $("#byteCheck").html(parseInt(msg_length/2));  
							 }
							 else {
							  $("#byteCheck").css("color", "#E55451");   
							  $("#byteCheck").html(parseInt(msg_length/2));   
							 }
							});
						</script>
				</div>
			</div>

			<div id="review_attach">
				<div id="review_attach_inner">
					<ul id="thumUl">
					<% 
					
					int i = 0;
					
					if(arr != null){
					for(i = 1; i < arr.length+1; i++){ %>
						<li>
							<div class="add_thmb" id="contentImgArea<%=i%>">
								<img id="contentImg<%=i%>" class="notEmpty" src="<%=request.getContextPath()%>/review_uploadFiles/<%=arr[i-1]%>">
							</div>
						</li>
					<%} }
						if(i < 4) {
							for(int j = i; j < 4; j++) {%>
								<li>
									<div class="add_thmb" id="contentImgArea<%=i%>">
										<img id="contentImg<%=i%>" src="../../img/shopImg/picture_WH.png">
									</div>
								</li>
							<%}
						}
					%>
						
					</ul>
					<ul id="submitUl">
						<li><button type="button" id="submitBtn">등록</button></li>
						<li><button type="button" id="cencleBtn" onclick="self.close();">취소</button></li>
					</ul>
				</div>
			</div>

		<div id="fileArea">
			<input type="file" id="thumbnailImg1" multiple="multiple" name="thumbnailImg1" onchange="LoadImg(this,1)"> 
			<input type="file" id="thumbnailImg2" multiple="multiple" name="thumbnailImg2" onchange="LoadImg(this,2)"> 
			<input type="file" id="thumbnailImg3" multiple="multiple" name="thumbnailImg3" onchange="LoadImg(this,3)">
		</div>
	</form>
		</div>
	</div>
	<script>
	var deleteImgName = null;
		$(function(){
			$("#fileArea").hide();
			
			$("#contentImgArea1").click(function(){
				$("#thumbnailImg1").click();
			});
			$("#contentImgArea2").click(function(){
				$("#thumbnailImg2").click();
			});
			$("#contentImgArea3").click(function(){
				$("#thumbnailImg3").click();
			});
		});
			
		function LoadImg(value, num){
			if(value.files && value.files[0]){
				var reader = new FileReader();
				
				reader.onload = function(e){								
					switch(num){
					case 1:
						deleteImg(num);
						$("#contentImg1").attr("src", e.target.result); 
						break;
					case 2: 
						deleteImg(num);
						$("#contentImg2").attr("src", e.target.result);
						break;
					case 3:
						deleteImg(num);
						$("#contentImg3").attr("src", e.target.result);
						break;
					}
				}
				reader.readAsDataURL(value.files[0]);
			}
		}
		
		function deleteImg(num){
			if(<%=arr%> != null){
				switch(num){
				case 1:
					deleteImgName += "<%= arr==null ? "": arr[0]%>"+"/";
					break;
				case 2: 
					deleteImgName += "<%= arr==null ? "": arr[1]%>"+"/";
					break;
				case 3:
					deleteImgName += "<%= arr==null ? "": arr[2]%>"+"/";
					break;
				}
			}			
		}
		
		$('#submitBtn').on('click', function(){
			if(confirm("정말 수정하시겠습니까?")){
			var rId = '<%=rId%>';
			var content = $("#textArea").val();
			var rContent = content.trim();
			var form = $('#file_form');
			var pId = '<%=pId%>';
			
			var fileData = new FormData();
			fileData.append("file1", $("#thumbnailImg1")[0].files[0]);
			fileData.append("file2", $("#thumbnailImg2")[0].files[0]);
			fileData.append("file3", $("#thumbnailImg3")[0].files[0]);
			fileData.append("rId", rId);
			fileData.append("rContent", rContent);
			fileData.append("pId", pId);
			fileData.append("deleteImgName", deleteImgName);
			
			alert(deleteImgName);

			$.ajax({
				url: "<%=request.getContextPath()%>/updateReview.do",
					type : "post",
					contentType:false,
				    processData:false,
					data : fileData,	
					success : function(data) {
						if (data.result > 1) { // 수정 성공시
									alert("수정에 성공했습니다!");
									window.opener.location.reload();
									self.close();
						} else {
							alert("수정에 실패했습니다.");
						}
				
			}
			});
			}
		});
	</script>
</body>
</html>