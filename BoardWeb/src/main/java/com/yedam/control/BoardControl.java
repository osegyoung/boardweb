package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.dao.BoardDAO;
import com.yedam.vo.BoardVO;

public class BoardControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// BoardWeb/board.do?bno=12
		String bno = req.getParameter("bno");
		
		BoardDAO bdao= new BoardDAO();
		bdao.updateCount(Integer.parseInt(bno)); // 조회하기 전에 업데이트 , 
		BoardVO board = bdao.getBoard(Integer.parseInt(bno)); //문자열 "14" -> int 14 변경. 조회수 증가.
		//요청정보의 attribute활용.
		req.setAttribute("board", board); // attribute
		req.getRequestDispatcher("/WEB-INF/views/board.jsp").forward(req, resp);
	}

}
