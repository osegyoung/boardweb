
<%@page import="com.yedam.vo.BoardVO"%>
<%@page import="com.yedam.vo.Employee"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
h3{
color : white;
background-color :blue;  
padding: 10px;
border:5px solid black;
font-size: 150%;
}
</style>
</head>
<body>


	<!-- html주석문. -->
	<%
	String msg = "Hello";
	System.out.println(msg);
	// boardList.do -> request -> boardList.jsp
	String result = (String) request.getAttribute("msg");
	List<BoardVO> list = (List <BoardVO>) request.getAttribute("list");
	%>
	<h3>게시글 목록</h3> 
	<table border = '2'>	
		<tbody>
			<%
			for (BoardVO board : list) {// for반복문 시작.
			%>
			<tr>
				<td><%=board.getBoardNo() %></td>
				<td><%=board.getTitle() %></td>
				<td><%=board.getWriter() %></td>
				<td><%=board.getWriterDate() %></td>
				<td><%=board.getViewCnt() %></td>
			</tr>
			<%
			} // for 반복문 종료
			%>
		</tbody>
	</table>
	
</body>
</html>

