package com.yedam;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.dao.ReplyDAO;

public class test {
	public static void main(String[] args) {
		ReplyDAO rdao = new ReplyDAO();
		rdao.chartData();
		List<Map<String, Object>> list = rdao.chartData();
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(list);
		
		System.out.println(json);
	}
}
