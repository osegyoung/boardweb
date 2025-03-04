package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.mapper.ReplyMapper;

public class RemoveData implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String title = req.getParameter("title");
		String start = req.getParameter("start");
		String end = req.getParameter("end");
		
		SqlSession sqlSession = DataSource.getInstance().openSession(true);
		ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
		int row = mapper.deleteEvent(title, start, end);
		
		if(row == 1) {
			//{"retCode":"OK"}
			resp.getWriter().print("{\"retCode\":\"OK\"}");
			
		}else {
			//{"retCode":"NG"}
			resp.getWriter().print("{\"retCode\":\"NG\"}");
		}
	}

}
