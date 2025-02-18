	package com.yedam.serv;
	// param의 값을 활용 -> db 입력. 
	//처리성공./ 처리실패 메세지.

	import java.io.IOException;

	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

import com.yedam.dao.StudentDAO;
import com.yedam.vo.Student;

	//init - service - destroy :서블릿의 생명주기.
	@WebServlet("/addStudentServlet")
	public class AddStudentServlet extends HttpServlet {
		

		@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
//			?empNo=1001&empName=oh&telNo=23
			resp.setContentType("text/html;charset=utf-8");
			
			String sno = req.getParameter("std_no"); // empNo의 param에 담겨있는 값 반환.
			String sname = req.getParameter("std_name");
			String stel = req.getParameter("tel_no");
			String saddress = req.getParameter("address");
			
			//매개값으로 Student.
			Student std = new Student(saddress);
			std.setstudent_No(sno);
			std.setstudent_Name(sname);
			std.setTelNo(stel);
			std.setAddress(saddress);
			
			//db등록
			StudentDAO sdao = new StudentDAO();
			boolean result = sdao.addStudent(std);
			if(result) {
				resp.getWriter().print("처리성공");
				
			}else {
				resp.getWriter().print("처리실패");
			}
			
		}
	}
