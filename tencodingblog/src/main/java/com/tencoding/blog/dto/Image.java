package com.tencoding.blog.dto;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String storyText;
	private String originFileName;
	private String postImageUrl;// 경로 저장 //https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRPGVgzIDR_Ed1CTVWRFZQGEsCkL5L1MxwxWGBLXlk0o6EtDiMk6WnnMbhHAw&s
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	@CreationTimestamp
	private Timestamp createdDate; 
}
