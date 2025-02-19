package com.yedam.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //전체 생성자
@NoArgsConstructor // 기본생성자
public class Student {
	
	private String student_no;
	private String student_name;
	private String tel_no;
	private String address;
	
	

}
