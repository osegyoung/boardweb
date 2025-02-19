package com.yedam.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.vo.Student;

public class StudentDAO extends DAO {

	public boolean addStudent(Student student) {
		String sql = "insert into tbl_student (student_no, student_name, phone, addres)" + "values(?,?,?,?)";

		try {
			psmt = getConnect().prepareStatement(sql);
//			psmt.setString(1, student.getStudentNo());
//			psmt.setString(2,student.getStudentName());
//			psmt.setString(3,student.getPhone());
			psmt.setString(4, student.getAddress());

			// 쿼리실행.
			int r = psmt.executeUpdate(); // 처리된 건수.
			if (r > 0) {
				return true;// 등록성공
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;// 등록실패
	}

	// 학생목록을 반환 메소드. 참고 EmpDAO.search()
	public List<Student> studentList() {
		List<Student> studentList = new ArrayList<>();
		String qry = "select * from tbl_student" + " order by student_no";

		try {
			psmt = getConnect().prepareStatement(qry);
			rs = stmt.executeQuery(qry);

			// 조회결과
			while (rs.next()) {
				Student student = new Student();
//				student.setstudent_No(rs.getString("student_no"));
//				student.setstudent_Name(rs.getString("student_name"));
//				student.setAddress(rs.getString("addres"));
//				student.setTelNo(rs.getString("phone"));
				studentList.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return studentList;

	}// end of studentList().
}
