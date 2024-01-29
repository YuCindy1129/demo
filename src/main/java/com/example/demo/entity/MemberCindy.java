package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "Member_Cindy")
@Entity
public class MemberCindy {

	@Id
	Integer id;
	
	String name;
	
	Integer age;
}
