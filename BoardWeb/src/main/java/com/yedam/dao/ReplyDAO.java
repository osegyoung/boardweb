package com.yedam.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yedam.vo.ReplyVO;

//댓글목록, 등록, 삭제, 상세조회.
public class ReplyDAO extends DAO {
	
	// 부서별 인원현황 차트.
	public List<Map<String, Object>> chartData() {
		String sql = "select emp.department_id, dept.department_name, count(1) cnt "
				+ "from employees emp "
				+ "join departments dept "
				+ "on   emp.department_id = dept.department_id "
				+ "group by emp.department_id, dept.department_name";
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		try {
			psmt = getConnect().prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("dept_name",rs.getString(2));
				map.put("dept_count",rs.getInt(3));
				list.add(map);
			}
		} catch (SQLException e) {	
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return list;
	}
	
	

	// 댓글의 건수 계산(페이징)
	public int replyCount(int boardNo) {
		String sql = "select count(1) from tbl_reply where board_no = ?";
		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setInt(1, boardNo);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return 0;
	}

	// 목록.
	public List<ReplyVO> replyList(int boardNo, int page) {
		String sql = "select tbl_a.* " + "from (select /*+ INDEX (r pk_reply) */"
				+ "             rownum rn, reply_no, reply, replyer, board_no, reply_date " + "      from tbl_reply r "
				+ "      where board_no = :board_no) tbl_a " + "where tbl_a.rn > (:page - 1) * 5 "
				+ "and   tbl_a.rn <= :page * 5";
		List<ReplyVO> list = new ArrayList<>();

		// 조회
		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setInt(1, boardNo);
			psmt.setInt(2, page);
			psmt.setInt(3, page);
			rs = psmt.executeQuery();// 쿼리실행.

			while (rs.next()) {
				ReplyVO reply = new ReplyVO();
				reply.setBoardNo(rs.getInt("board_No"));
				reply.setReply(rs.getString("reply"));
				list.add(reply);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;

	}

	// 상세조회
	public ReplyVO selectReply(int replyNo) {
		String query = "select * from tbl_reply " + "where reply_no = ?";
		try {
			psmt = getConnect().prepareStatement(query);
			psmt.setInt(1, replyNo);
			rs = psmt.executeQuery();
			if (rs.next()) {
				ReplyVO reply = new ReplyVO();
				reply.setReplyNo(rs.getInt("replyNo"));
				reply.setReply(rs.getString("reply"));
				reply.setReplyer(rs.getString("replyer"));
				reply.setReplyDate(rs.getDate("reply_date"));

				return reply;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 등록.
	public boolean insertReply(ReplyVO reply) {
		String query1 = "select reply_seq.nextval from dual";
		String query = "insert into tbl_reply (reply_no, reply, replyer, board_no"
				+ "values(reply_seq.nextval, ?, ?,? ";
		try {
			psmt = getConnect().prepareStatement(query1);
			rs = psmt.executeQuery();
			if (rs.next()) {
				reply.setReplyNo(rs.getInt(1)); // 첫번째 컬럼.
			}

			psmt = getConnect().prepareStatement(query);
			psmt.setInt(1, reply.getReplyNo());
			psmt.setString(2, reply.getReply());
			psmt.setInt(3, reply.getBoardNo());
			int r = psmt.executeUpdate();// 쿼리 실행
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			disConnect();
		}
		return false;
	}

	// 삭제
	public boolean deleteReply(int replyNo) {
		String query = "delete from tbl_reply where reply_no = ?";

		try {
			psmt = getConnect().prepareStatement(query);
			psmt.setInt(1, replyNo);
			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return false;
	}
}
