package com.demo.qlrm.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.qlrm.dto.res.EmployeeDTO;
import com.demo.qlrm.dto.res.EmployeesDTO;
import com.demo.qlrm.dto.res.SalaryDto;
import com.demo.qlrm.repository.EmployeeRepository;

@RestController
public class HomeApiController {
	
	// 여기에서는 service를 만들지 않고 바로 repository test 하기
	private final EmployeeRepository employeeRepository;
	
	public HomeApiController(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	@GetMapping({"", "/index", "/home"})
	public ResponseEntity<?> home() {
		// ResponseEntity <-- 알아서 json으로 파싱 해준다.
		EmployeeDTO resultDto = employeeRepository.getOldEmployeeAgeInfo();
		return new ResponseEntity<EmployeeDTO>(resultDto, HttpStatus.OK);
	}
	
	
	@GetMapping("/salaryAvg")
	public ResponseEntity<?> getSalaryAvg(){
		SalaryDto salaryDto = employeeRepository.salaryAvg();
		return new ResponseEntity<Object>(salaryDto, HttpStatus.OK);
	}

	// 다중행 QLRM
	@GetMapping("/salaryCount")
	public ResponseEntity<?> home1(){
		List<SalaryDto> resultDto = employeeRepository.salaryAvgCount();
		return new ResponseEntity<List<SalaryDto>>(resultDto, HttpStatus.OK);
	}
}
