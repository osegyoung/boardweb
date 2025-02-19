package com.yedam;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.control.AddBoardControl;
import com.yedam.control.BoardControl;
import com.yedam.control.BoardListControl;
import com.yedam.control.Control;
import com.yedam.control.MainControl;
import com.yedam.control.ModifBoardControl;
import com.yedam.control.ModifyControl;
import com.yedam.control.addFormControl;

/*
 * MVC에서 Control역할.
 * url요청 -> 서블릿.
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	Map<String, Control> map;

	public FrontController() {
		map = new HashMap<>(); // map 필드의 초기화.
	}

	@Override
	public void init() throws ServletException {
		map.put("/main.do", new MainControl());
//		map.put("url", "servlet");// addStudent.do AddStudentServlet
		map.put("/boardList.do", new BoardListControl()); // 글목록.
		map.put("/addForm.do", new addFormControl()); // 등록화면.
		map.put("/addBoard.do", new AddBoardControl()); // 등록처리.
		map.put("/board.do", new BoardControl()); // 상세화면.
		map.put("/modifyForm.do", new ModifyControl()); // 수정화면.
		map.put("/modifyBoard.do",new ModifBoardControl());
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("front control");
		//http:localhost:8080/BoardWeb/addStudent.do => url
		//BoardWeb/addStudent.do => uri
		String uri = req.getRequestURI(); //"/BoardWeb/addStudent.do
		String context = req.getContextPath(); // "/BoardWeb"
		String page = uri.substring(context.length());
		
		System.out.println(page);
		Control control = map.get(page);
		control.exec(req, resp);
	}

}
