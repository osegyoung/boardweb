package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.dao.MemberDAO;
import com.yedam.vo.MemberVO;

public class LoginControl implements Control {

	/**
	 *
	 */
	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 요청방식(get, post)
		if (req.getMethod().equals("GET")) {
			// 1.로그인 화면.
			req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);

		} else if (req.getMethod().equals("POST")) {
			// 2.로그인 기능.
			String id = req.getParameter("uname");
			String pw = req.getParameter("psw");
			
			//로그인 확인.
			MemberDAO mdao = new MemberDAO();
			MemberVO mvo = mdao.login(id, pw);
			
			if(mvo != null) {
				System.out.println("당신은 성공" + mvo.getMemberName());
				//세션객체에 로그인 아이디를 저장.
				HttpSession session = req.getSession();
				session.setAttribute("loginId", id); // attribute활용.
				resp.sendRedirect("boardList.do");
			}else {
				System.out.println("id, pw 확인");
			}
			
		}

	}

}
