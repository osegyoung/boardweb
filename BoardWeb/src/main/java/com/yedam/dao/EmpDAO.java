package com.yedam.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yedam.vo.Employee;

// Data Access 

public class EmpDAO extends DAO {

	// 상세조회
	public Employee selectEmp(int empNo) {
		String query = "select * from tbl_employees " + "where emp_no = ?";
		try {
			psmt = getConnect().prepareStatement(query);
			psmt.setInt(1, empNo);
			rs = psmt.executeQuery(); // 조회.
			if (rs.next()) {// 조회결과가 한건 있으면
				Employee emp = new Employee();
				emp.setEmpNo(rs.getInt("emp_no")); // 칼럼값.
				emp.setEmpName(rs.getString("emp_Name"));
				emp.setTelNo(rs.getString("tel_no"));
				emp.setHireDate(rs.getDate("hire_date"));
				emp.setSalary(rs.getInt("salary"));

				return emp;// 반환.
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // 조회된 결과가 없을 경우(null반환)
	}

	// 등록
	public boolean registerEmp(Employee emp) { // 등록.
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String query = "insert into tbl_employees ";
		query += "values (" + emp.getEmpNo() //
				+ ", '" + emp.getEmpName() //
				+ "', '" + emp.getTelNo() //
				+ "', '" + sdf.format(emp.getHireDate()) //
				+ "', " + emp.getSalary()//
				+ ")";
		try {
			 stmt = getConnect().createStatement();
			int r = stmt.executeUpdate(query);
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	} // end of registerEmp().
	
	//목록
	public List<Employee> search(Employee emp) {
		List<Employee> empList = new ArrayList<>();
		String qry = "select * from tbl_employees "
//		+ " where emp_name = nvl('" + emp.getEmpName() + "', emp_name) " 
				+ "where emp_name = nvl(?,emp_name)"// number, varchar2에 따라
				+ " order by emp_no";
		System.out.println(qry);

		try {
//			Statement stmt = getConnect().createStatement();
			PreparedStatement stmt = getConnect().prepareStatement(qry);
			stmt.setString(1, emp.getEmpName()); // 첫번째 ?에 사원이름을 대입.
			ResultSet rs = stmt.executeQuery();

			// 조회결과
			while (rs.next()) {
				Employee empl = new Employee();
				empl.setEmpNo(rs.getInt("emp_no"));
				empl.setEmpName(rs.getString("emp_name"));
				empl.setHireDate(rs.getDate("hire_date"));
				empl.setSalary(rs.getInt("salary"));
				empl.setTelNo(rs.getString("tel_no"));
				empList.add(empl);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empList;
	}
}
