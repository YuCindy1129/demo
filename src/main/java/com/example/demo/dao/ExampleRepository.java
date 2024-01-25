package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Example;

public interface ExampleRepository extends JpaRepository<Example, Integer>{
	
	Example findByIdAndPassword(Integer id,String password);
}
