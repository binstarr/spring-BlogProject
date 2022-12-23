package com.demo.qlrm.dto.res;

import java.math.BigInteger;
import java.sql.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EmployeesDTO {
	
	private int emp_no;
	private int salary;
	private Date from_date;
	private Date to_date;
	private int 횟수;
	
	public EmployeesDTO(Integer emp_no, Integer salary, Date from_date, Date to_date, BigInteger 횟수) {
		this.emp_no = emp_no.intValue();
		this.salary = salary.intValue();
		this.from_date = from_date;
		this.to_date = to_date;
		this.횟수 = 횟수.intValue();
	}
	
	
}
