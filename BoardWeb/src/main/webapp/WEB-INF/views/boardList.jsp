
<%@page import="com.yedam.PageVO"%>
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
	// Control에서 paging의 값을 얻어오기.
	PageVO paging = (PageVO) request.getAttribute("paging");
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
	<!-- paging 시작. -->
	<nav aria-label="...">
  <ul class="pagination">
  <!-- 이전 페이지 여부. -->
  <%if (paging.isPrev()) {%>
   <li class="page-item">
      <a class="page-link" href="boardList.do?page=<%=paging.getStartPage()-1%>">Previous</a>
    </li>
    <%} else {%>
    <li class="page-item disabled">
      <span class="page-link" >Previous</span>
    </li>
    <%} %>
     
    <!--  페이지 start ~ end 반복 -->
    <%for (int p = paging.getStartPage(); p <= paging.getEndPage(); p++){%>
    <% if (p == paging.getCurrentPage()) {%>
    <li class="page-item active" aria-current="page">
      <span class="page-link"><%=p %></span>
    </li>
    <%} else {%>
    <li class="page-item"><a class="page-link" href="boardList.do?page=<%=p %>"><%=p %></a></li>
    <%} 
    } %>
    
    <!-- 이전 페이지 여부. -->
  <%if (paging.isNext()) {%>
   <li class="page-item">
      <a class="page-link" href="boardList.do?page=<%=paging.getEndPage()+1 %>">Next</a>
    </li>
    <%} else {%>
    <li class="page-item disabled">
      <span class="page-link">Next</span>
    </li>
    <%} %>
  
    
   
    
  </ul>
</nav>
	<!--  paging 끝. -->	
	<jsp:include page="includes/footer.jsp"></jsp:include>
</body>
</html>

