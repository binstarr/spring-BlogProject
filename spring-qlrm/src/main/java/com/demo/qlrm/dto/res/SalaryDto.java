package com.demo.qlrm.dto.res;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SalaryDto {
	
	private int salaryAvg;
	private int count;
	
	public SalaryDto(Object[] objs) {
		
		this.salaryAvg = (Integer)objs[0];
		this.count = ((BigInteger)objs[1]).intValue();
	}

	// 다중행 QLRM
	public SalaryDto(int salaryAvg, BigInteger count) {
		this.salaryAvg = salaryAvg;
		this.count = count.intValue();
	}

	
	
}
