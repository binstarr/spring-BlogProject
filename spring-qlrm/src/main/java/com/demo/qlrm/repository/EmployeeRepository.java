package com.demo.qlrm.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import com.demo.qlrm.dto.res.EmployeeDTO;
import com.demo.qlrm.dto.res.EmployeesDTO;
import com.demo.qlrm.dto.res.SalaryDto;



// JPARepository<T,ID> 상속을 받으면 무조건 Entity(model) 로 받아야 하기 때문에
// DTO 맵핑을 하기가 힘들다. 그래서 직접 Repository를 구성하자.
@Repository
public class EmployeeRepository {
	
	// 준비물
	// 1. EntityManager
	private final EntityManager entityManager;
	
	public EmployeeRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	// 기능 설계
	public EmployeeDTO getOldEmployeeAgeInfo() {
		
		// 어노테이션 기반이 아닌 직접 생성해 보자 native query
		// Connection 객체
		// Statement
		// ResultSet rs
		// String 뿌리거나 -> rs.getString("emp_no"); rs.getInt
		String strQuery = "SELECT"
						+ " *, TIMESTAMPDIFF(YEAR, birth_date, NOW()) AS age"
						+ " FROM"
						+ " employees"
						+ " WHERE"
						+ " emp_no = 10001; ";
		
		// Query 객체를 사용합니다
		//Query nativeQuery = entityManager.createNativeQuery(strQuery);
		// Query 객체를 이용해서 구문 실행하고 결과 집합을 받아 보자.
		
//		System.out.println(nativeQuery.getSingleResult());
//		System.out.println(nativeQuery.getResultList());
//		// [[Ljava.lang.Object;@45d8c098] 형을 변환 처리 후
//		// 사이즈가 얼마 일까?
//		List<Object[]> resultList = nativeQuery.getResultList();
//		System.out.println(resultList.size()); // 1개 확인
//		Object[] objs = resultList.get(0);
//		System.out.println(objs.length); // 사이즈 확인	
//		// dto로 변환하기
//		EmployeeDTO dto = new EmployeeDTO(objs);
//		System.out.println(dto);
		
		// QLRM 사용
		// import org.qlrm.mapper.JpaResultMapper;
		Query nativeQuery = entityManager.createNativeQuery(strQuery);
		JpaResultMapper mapper = new JpaResultMapper();
		// 전체 생성자를 활용... 알아서 jpamapper 녀석 호출해서 맵핑을 해준다.
		
		// mapper.list(nativeQuery, EmployeeDTO.class)
		return mapper.uniqueResult(nativeQuery, EmployeeDTO.class);

	}
	
	
	
	public SalaryDto salaryAvg() {

		String query = "SELECT salary, COUNT(salary)"
        		+ " FROM salaries"
        		+ " WHERE emp_no BETWEEN 10003 AND 10005"
        		+ " GROUP BY emp_no;";

        Query nativeQuery = entityManager.createNativeQuery(query);
        List<Object[]> result = nativeQuery.getResultList();
        Object[] objs = result.get(0);
        SalaryDto salaryDto = new SalaryDto(objs);
        return salaryDto;
    }

    // 다중행 QLRM
        public List<SalaryDto> salaryAvgCount() {

            String query = "SELECT salary, COUNT(salary)"
            		+ " FROM salaries"
            		+ " WHERE emp_no BETWEEN 10003 AND 10005"
            		+ " GROUP BY emp_no;";

            Query nativeQuery = entityManager.createNativeQuery(query);
            JpaResultMapper mapper = new JpaResultMapper();
            List<SalaryDto> dtoList = mapper.list(nativeQuery, SalaryDto.class);
            return dtoList;
        }
	
	

}
