<%@page import="com.yedam.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
BoardVO board = (BoardVO) request.getAttribute("board");
String msg = (String) request.getAttribute("msg");
String logId = (String) session.getAttribute("loginId");
%>
<h3>상세화면</h3>
<tr>
	<form action="modifyForm.do">
		<input type="hidden" name="bno" value="${ board.BoardNo()}">
		<table class="table">
			<tr>
				<th>글번호</th>
				<td><%=board.getBoardNo()%></td>
				<th>조회수</th>
				<td><%=board.getViewCnt()%></td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3"><%=board.getContent()%></td>
			</tr>
			<tr>
				<th>제목</th>
				<td colspan="3"><input type="text" class="form-control"
					value="<%=board.getTitle()%>"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%=board.getWriter()%></td>
				<th>작성날짜</th>
				<td><%=board.getWriterDate()%></td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<button class="btn btn-primary" type="submit">수정</button>
					<button class="btn btn-danger" type="button">삭제</button> <%
 if (msg != null) {
 %> <span style="color: red;"><%=msg%></span> <%
 }
 %>
				
			</tr>
		</table>
	</form>

	<style>
.reply.container ul {
	list-style-type: none;
}

.reply.content span {
	display: inline-block;
}
</style>





	<!--  댓글관련. -->
	<div class="container reply">
		<!--  댓글등록 -->
		<div class="header">
		<input type="text" id="reply" class="col-sm-9">
		<button id="addReply">댓글등록</button>
		</div>

		<!-- 댓글목록 -->
		<div class="footer"></div>
		<ul>
			<li><span class="col=sm-2">글번호</span> <span class="col=sm-5">글내용</span>
				<spanclass ="col=sm-2">작성자</span> <span class="col=sm-2">삭제</span></li>
		</ul>
	</div>

	<script>
	let logid = "${loginId}"; // 자바의 변수값을 script 사용.
	const bno = "${ board.BoardNo()}";
	console.log(bno)
		"; // 자바의 변수값을 script 사용.
		// 삭제버튼에 클릭이벤트 등록.
		document.querySelector('button.btn-danger')
				.addEventListener('click',function(e) {
					let writer = document.querySelector('table.table>tbody>tr:nth-of-type(4)>td').innerHTML;
					let bno = document.querySelector('input[name="bno"]').value;
					//console.log(writer, logid);
							if (writer == logid)
								location.href = "removeBoard.do?bno=" + bno;
							else
								alert("권한을 확인하세요.");
						});
	</script>

	<script src="js/replyService.js"></script>
	<script src="js/reply.js"></script>