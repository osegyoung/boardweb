package com.yedam.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.dao.BoardDAO;
import com.yedam.vo.BoardVO;

public class BoardListControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name = "홍길동";

		// boardList.do -> (BoardListControl) ->boardList.jsp
		req.setAttribute("msg", name);
		
		BoardDAO edao = new BoardDAO();
		List<BoardVO> list = edao.selectBoard();
		req.setAttribute("list", list);
		
		
		// 요청재지정(url:boardList.do (boardList.jsp))
		req.getRequestDispatcher("boardList.jsp").forward(req, resp);

	}
}
