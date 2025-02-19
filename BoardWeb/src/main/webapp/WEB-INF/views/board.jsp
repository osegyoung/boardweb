<%@page import="com.yedam.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"></jsp:include>
<h3>상세화면</h3>
<%
BoardVO board = (BoardVO) request.getAttribute("board");
%>
<tr>
	<form action="modifyForm.do">
		<input type="hidden" name="bno" value="<%=board.getBoardNo()%>">
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
				<th>작성자</th>
				<td><%=board.getWriter()%></td>
				<th>작성날짜</th>
				<td><%=board.getWriterDate()%></td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<button class="btn btn-primary" type="submit">수정</button>
					<button class="btn btn-danger" type="button">삭제</button>
			</tr>
		</table>
	</form>

	<jsp:include page="includes/footer.jsp"></jsp:include>