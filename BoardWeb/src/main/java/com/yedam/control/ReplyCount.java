package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.dao.ReplyDAO;

public class ReplyCount implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String bno = req.getParameter("bno");

		ReplyDAO rdao = new ReplyDAO();
		int totalCnt = rdao.replyCount(Integer.parseInt(bno));

		// {"totalCnt": 30}
		resp.getWriter().print("{\"totalCnt\": " + totalCnt + "}");

	}

}
