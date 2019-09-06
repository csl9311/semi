<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	int pId = Integer.parseInt(request.getParameter("pId"));
	int type = Integer.parseInt(request.getParameter("type"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<style>
*:focus {
	outline: none;
}
body {
	margin: 0;
	padding: 0;
	background-color: rgb(42, 40, 50);
	overflow: hidden;
}

#Index {
	width: 500px;
	height: 550px;
	text-align: center;
	justify-content: center;
}

#title {
	margin-top: 5%;
	margin-bottom: 5%;
	width: 400px;
	height: 30px;
	resize: none;
	border: none;
	background: none;
	border-bottom: gray;
	font-size: 1.2em;
	text-align: center;
	font-family: '돋움체';
	color: rgb(210,210,210);
}

#contentArea {
	width: 450px;
	height: 300px;
	background-color: rgba(210, 210, 210, 0.1);
	border: none;
	resize: none;
	color: rgb(210, 210, 210);
	padding: 10px;
	margin-left: 15px;
}

#btnArea {
	margin-top: 2%; width : 95%;
	display: flex;
	justify-content: flex-end;
	width: 95%;
}

.button {
	color: rgb(210, 210, 210);
	font-weight: 800;
	background: none;
	cursor: pointer;
}

#cancle {
	margin-left: 5%;
}

.button a:hover {
	color: rgb(100, 100, 100);
	text-decoration: none;
}

#submitBtn a:hover {
	color: #E55451;
	text-decoration: none;
}

#textArea {
	width: 90%;
	height: 95%;
	border: none;
	resize: none;
	color: rgb(210, 210, 210);
	background: none;
	padding: 10px;
}
</style>
</head>
<body>
	<div id="Index">
		<div style="boder-bottom: 1px solid gray;">
			<textarea id="title" placeholder="제목을 입력해주세요"></textarea>
		</div>
		<div id="contentArea">
			<textarea id="textArea" placeholder="내용(최대 200자)을 입력해 주세요."></textarea>
			<span id="byteCheck" style="">0</span>

		</div>


		<script>
			// 글자수 체크
			String.prototype.bytes = function() {
				var msg = this;
				var cnt = 0;
				//한글이면 2, 아니면 1 count 증가
				for (var i = 0; i < msg.length; i++)
					cnt += (msg.charCodeAt(i) > 128) ? 2 : 1;
				return cnt;
			}

			//textarea에서 키를 입력할 때마다 동작
			$("#textArea").keyup(function(e) {
				msg_length = $(this).val().bytes();

				if (msg_length <= 400) {
					$("#byteCheck").css("color", "#9e9b9b");
					$("#byteCheck").html(parseInt(msg_length / 2));
				} else {
					$("#byteCheck").css("color", "#E55451");
					$("#byteCheck").html(parseInt(msg_length / 2));
				}
			});
		</script>
		<div id="btnArea" class="btnArea">
			<span id="submitBtn" class="button"> <a>WRITE</a></span> <span id="cancle" class="button"> <a>CANCLE</a></span>
		</div>
	</div>
	<script>
		$('#cancle').on('click', function(){
			if(confirm('정말 작성을 취소하시겠습니까?')){
				self.close();
			}
		});
		// 리뷰 등록 ajax
		$('#submitBtn').on('click', function(){
			var rContent = $("#textArea").val().trim();
			var rTitle = $('#title').val().trim();
			var pId = '<%=pId%>';
			var type = '<%=type%>';
			
			console.log(pId);
			console.log(rTitle);
			console.log(rContent);
				
			$.ajax({
				url: "<%=request.getContextPath()%>/insertReview.do", 
					type : "post",
					data : {pId:pId, rTitle:rTitle, rContent:rContent, type:type},	
					success : function(data) {
						if (data.result > 0) { // 등록 성공시
									alert("작성 완료!");
									/* window.opener.location.reload(); */
									opener.parent.reviewPrint(type);
									self.close();
						} else {
							alert("글 작성을 실패했습니다.");
						}
				}
			});
		});
	</script>
</body>
</html>