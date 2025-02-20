package com.yedam.dao;

import java.sql.SQLException;

import com.yedam.vo.MemberVO;

public class MemberDAO extends DAO{

	public MemberVO login(String id, String pw) {
			String sql = "select member_id"
					+"         ,passwd"
					+"         ,member_name"
					+"         ,resposibility"
					+"  from tbl_member "
					+"  where member_id = ?"
					+"  and  passwd = ?";
			
			//조회
			try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			
			rs = psmt.executeQuery(); // 쿼리실행.
			if(rs.next()) { // 조회된 결과가 있으면.
				MemberVO mvo = new MemberVO();
				mvo.setMemberId(rs.getString("member_id"));
				mvo.setPasswd(rs.getString("passwd"));
				mvo.setMemberName(rs.getString("member_name"));
				mvo.setResposibility(rs.getString("resposibility"));
				return mvo;
				
				
			}
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				disConnect();
			}
			return null;
			
		}
}
