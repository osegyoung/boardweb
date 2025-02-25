package com.yedam.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.vo.ReplyVO;

//댓글목록, 등록, 삭제, 상세조회.
public class ReplyDAO extends DAO {

	// 목록.
	public List<ReplyVO> replyList(int boardNo) {
		String sql = "select reply_no" + "			,reply" + "			,replyer" + " 		,reply_date"
				+ " 		,board_no" + "  from tbl_reply " + "  where board_no = ?";
		List<ReplyVO> list = new ArrayList<>();

		// 조회
		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setInt(1, boardNo);
			rs = psmt.executeQuery();// 쿼리실행.

			while (rs.next()) {
				ReplyVO reply = new ReplyVO();
				reply.setBoardNo(rs.getInt("boardNo"));
				reply.setReply(rs.getString("reply"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	// 상세조회
	public ReplyVO selectReply(int replyNo) {
		String query = "select * from tbl_reply " + "where reply_no = ?";
		try {
			psmt = getConnect().prepareStatement(query);
			psmt.setInt(1, replyNo);
			rs = psmt.executeQuery();
			if(rs.next()) {
				ReplyVO reply = new ReplyVO();
				reply.setReplyNo(rs.getInt("replyNo"));
				reply.setReply(rs.getString("reply"));
				reply.setReplyer(rs.getString("replyer"));
				reply.setReplyDate(rs.getDate("reply_date"));
				
				return reply;
			}
		}catch (SQLException e) {
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
			if(rs.next()) {				
			reply.setReplyNo(rs.getInt(1)); // 첫번째 컬럼.
			}
			
			psmt = getConnect().prepareStatement(query);
			psmt.setInt(1, reply.getReplyNo());
			psmt.setString(2, reply.getReply());
			psmt.setInt(3, reply.getBoardNo());
			int r = psmt.executeUpdate();//쿼리 실행
			if (r > 0 ) {
				return true;
			}
		}catch (SQLException e) {
				e.printStackTrace();
				
			}finally {
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
