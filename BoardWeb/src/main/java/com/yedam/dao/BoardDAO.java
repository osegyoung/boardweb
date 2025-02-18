package com.yedam.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.vo.BoardVO;

/*
 * 추가,수정,삭제,조회
 * Create,Read,Update,Delete
 */
public class BoardDAO extends DAO{
	//조회
	public List<BoardVO> selectBoard(){
		List<BoardVO> BoardList = new ArrayList<>();
		String qry = "select * from tbl_board" + " order by board_no";
		
		try {
			psmt = getConnect().prepareStatement(qry);
			rs = psmt.executeQuery(qry);
			
			while(rs.next()) {
				BoardVO brd = new BoardVO();
				brd.setBoardNo(rs.getInt("board_no"));
				brd.setTitle(rs.getString("title"));
				brd.setContent(rs.getString("content"));
				brd.setWriter(rs.getString("writer"));
				brd.setWriterDate(rs.getDate("writer_Date"));;
				brd.setViewCnt(rs.getInt("view_Cnt"));
				BoardList.add(brd);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return BoardList;
	}
	
	//추가
	public boolean inserBoard(BoardVO board) {
		return false;
	}
	//수정
	public boolean updateBoard(BoardVO board) {
		return false;
	}
	//삭제
	public boolean deleteBoard(int boardNo) {
		String query = "delete from tbl_board where board_no = ?";
		
		try {
			psmt = getConnect().prepareStatement(query);
			psmt.setInt(1, boardNo); 
			int r = stmt.executeUpdate(query);
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	} 
}
