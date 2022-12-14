package com.tencoding.blog.dto;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 150)
	private String title;
	
	@Lob // 대용량 데이터 선언
	private String content; // <html>asdasd</html>
	
	@ColumnDefault("0") // <-- int다, 문자열로 하려면 "" 안에 '' 넣어주자
	private int count;

	@ManyToOne(fetch = FetchType.EAGER) // userId_id <-- 이렇게 들어가있따.
	@JoinColumn(name = "userId") // 컬럼명을 직접 지정
	private User user;
	
	// 오브젝트를 다룰 때 가지고 와 달라고 요청해야 함 (mappedMy)
	// Boar <---> Reply 관계
	// 연관 관계에 주인이 아니다. (select할 때 가지고 와야 하는 데이터이다.)
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // 변수 명을 가지고 와야 한다.
	private List<Reply> reply;
	// reply - > FK board table 생성이 된다. 1정규화 위반!!

	@CreationTimestamp
	private Timestamp createDate;

}
