package com.yedam.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.DataSource;
import com.yedam.mapper.ReplyMapper;
import com.yedam.vo.ReplyVO;

public class BBFullDatacontrol implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		resp.setContentType("text/json;charset=utf-8");

		SqlSession sqlSession = DataSource.getInstance().openSession(); // sql과 연결 
		ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class); // 상세 데이터를 들고오는 
		List<Map<String, Object>> list = mapper.fullData();  // 같은 타입으로 만들어야함.

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(list);

		resp.getWriter().print(json); // 화면에 출력됨.

	}

}
