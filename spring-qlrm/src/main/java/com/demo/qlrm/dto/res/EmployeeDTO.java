package com.demo.qlrm.dto.res;

import java.math.BigInteger;
import java.sql.Date;
import java.util.Iterator;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EmployeeDTO {

	private int emp_no;
	private Date birth_date;
	private String first_name;
	private String last_name;
	private String gender;
	private Date hire_date;
	private int age;

//	public EmployeeDTO(Object[] objs) {
//
//		for (int i = 0; i < objs.length; i++) {
//			System.out.println(objs[i]);
//			// 런타임시 데이터 타입 확인 (....)
//			System.out.println(objs[i].getClass().getTypeName());
//			System.out.println("---------------------------------");
//		}
//
//		this.emp_no = ((Integer) objs[0]).intValue();
//		this.birth_date = (Date) objs[1];
//		this.first_name = (String) objs[2];
//		this.last_name = (String) objs[3];
//		this.gender = ((Character) objs[4]).toString();
//		this.hire_date = (Date) objs[5];
//		this.age = ((BigInteger) objs[6]).intValue();
//
//	}

	public EmployeeDTO(Integer emp_no, Date birth_date, String first_name, String last_name, Character gender, Date hire_date,
			BigInteger age) {
		System.out.println("1111111111111111111111111111111111111111111111");
		this.emp_no = emp_no.intValue();
		this.birth_date = birth_date;
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender.toString();
		this.hire_date = hire_date;
		this.age = age.intValue();
	}

}
