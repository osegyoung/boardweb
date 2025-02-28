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

public class DataTableControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("type/json;charset=utf-8");
		String bno = req.getParameter("bno");
		
		SqlSession sqlSession = DataSource.getInstance().openSession();
		ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
		List<ReplyVO> list = mapper.replyListAll(Integer.parseInt(bno));
		
		List<List<String>> dataAll = new ArrayList<List<String>>();
		
		for(ReplyVO reply : list) {
			List<String> dataList = new ArrayList<>();
			dataList.add("" + reply.getReplyNo());
			dataList.add(reply.getReply());
			dataList.add(reply.getReplyer());
			dataList.add("" + reply.getReplyDate());
			
			dataAll.add(dataList); // 상위의 4개의 데이터를  dataAll에 담음.
		}
		//{"data": [ ["","","",""], [], [] ....[] ]"}
		Map<String, Object> data = new HashMap<>();
		
		data.put("data", dataAll );
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(data);
		
		System.out.println(json);
		resp.getWriter().print(json); //화면에 출력됨.
	}

}
