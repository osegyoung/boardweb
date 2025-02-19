
<%@page import="com.yedam.vo.BoardVO"%>
<%@page import="com.yedam.vo.Employee"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"></jsp:include>


	<!-- html주석문. -->
	<%
	String msg = "Hello";
	System.out.println(msg);
	// boardList.do -> request -> boardList.jsp
	String result = (String) request.getAttribute("msg");
	List<BoardVO> list = (List <BoardVO>) request.getAttribute("list");
	%>
	<h3>게시글 목록</h3> 
	<table class="table table-striped">	
		<tbody>
			<%
			for (BoardVO board : list) {// for반복문 시작.
			%>
			<tr>
				<td><%=board.getBoardNo() %></td>
				<td><a href="board.do?bno=<%=board.getBoardNo() %>"><%=board.getTitle() %></a></td>
				<td><%=board.getWriter() %></td>
				<td><%=board.getWriterDate() %></td>
				<td><%=board.getViewCnt() %></td>
			</tr>
			<%
			} // for 반복문 종료
			%>
		</tbody>
	</table>	
	<jsp:include page="includes/footer.jsp"></jsp:include>
</body>
</html>

