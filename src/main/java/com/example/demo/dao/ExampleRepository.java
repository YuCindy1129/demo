package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.dto.AgeRequest;
import com.example.demo.entity.MemberCindy;

public interface ExampleRepository extends JpaRepository<MemberCindy, Integer>{

	
	
	
	@Query("SELECT DISTINCT m.name FROM MemberCindy m")
    List<String> findAllNames();

    @Query("SELECT m FROM MemberCindy m WHERE m.name = :name")
    MemberCindy findByNamejsp(@Param("name") String name);
	
	
	
	
	//NativeSql
	@Query(value = "SELECT * FROM Member_Cindy WHERE Member_Cindy.age >= ?",nativeQuery = true)
	List<MemberCindy> findByAge(@Param("age") Integer age);
	//JPQL
	@Query(value = "SELECT m FROM MemberCindy m WHERE m.age >= ?1")
	List<MemberCindy> findByAge2(Integer age);
	
	//NativeSql
	@Query(value = "SELECT * FROM Member_Cindy WHERE Member_Cindy.name IS NOT NULL",nativeQuery = true)
	List<MemberCindy> findBynotnullName();
	//JPQL
	@Query(value = "SELECT m FROM MemberCindy m WHERE m.name IS NOT NULL")
	List<MemberCindy> findBynotnullName2();
	
	//NativeSql
	@Query(value = "SELECT * FROM Member_Cindy WHERE Member_Cindy.name = ?1 LIMIT 1", nativeQuery = true)
	MemberCindy findByName(@Param("name") String name);
	//JPQL
	@Query("SELECT m FROM MemberCindy m WHERE m.name = :name")
	MemberCindy findByName2(@Param("name") String name);
		
	//NativeSql
	@Query(value = "SELECT * FROM Member_Cindy WHERE Member_Cindy.age BETWEEN 10 AND 20",nativeQuery = true)
	List<MemberCindy> findBetweenAge();
	//JPQL
	@Query("SELECT m FROM MemberCindy m WHERE m.age BETWEEN 10 AND 20")
	List<MemberCindy> findBetweenAge2();
	
	//NativeSql
	@Query(value = "SELECT * FROM Member_Cindy ORDER BY age DESC",nativeQuery = true)
	List<MemberCindy> findByAgeDESC();
	//JPQL
	@Query(value = "SELECT m FROM MemberCindy m ORDER BY age DESC")
	List<MemberCindy> findByAgeDESC2();
	
	List<MemberCindy> findAllByOrderByAgeDesc();
	
	//NativeSql
//	@Query(value = "SELECT new com.example.demo.dto.AgeRequest(*.age, COUNT(*)) FROM Member_Cindy GROUP BY age", nativeQuery = true)
//	List<AgeRequest> findGroupByAge();
	
	//NativeSql
	@Query(value = "SELECT age, COUNT(*) as AgeCount FROM Member_Cindy GROUP BY age", nativeQuery = true)
	List<Object[]> findGroupByAge();
	//JPQL
	@Query("SELECT new com.example.demo.dto.AgeRequest(m.age, COUNT(m)) FROM MemberCindy m GROUP BY m.age")
	List<AgeRequest> findGroupByAge2();
	
	
//	@Query(value = "SELECT age, COUNT(*) as ageCount FROM Member_Cindy GROUP BY age", nativeQuery = true)
//	List<AgeRequest> findGroupByAge();

	
//	List<Object[]> countByAgeGroupByAge ();

	//NativeSql
	@Query(value = "SELECT DISTINCT Member_Cindy.name FROM Member_Cindy ORDER BY Member_Cindy.name", nativeQuery = true)
	List<String> findNameList();
	//JPQL
	@Query("SELECT DISTINCT m.name FROM MemberCindy m ORDER BY m.name")
    List<String> findNameList2();
	
	
	MemberCindy findFirstByName(String name);
	
	//NativeSql
	@Query(value = "SELECT * FROM Member_Cindy WHERE Member_Cindy.name = 'FSTOP' ORDER BY Member_Cindy.id LIMIT 1", nativeQuery = true)
    List<MemberCindy> findFirstByNameFSTOP();
	//JPQL
	@Query("SELECT m FROM MemberCindy m WHERE m.name = 'FSTOP' ORDER BY m.id LIMIT 1")
    List<MemberCindy> findFirstByNameFSTOP2();

	//NativeSql
	@Query(value = "SELECT * FROM Member_Cindy ORDER BY Member_Cindy.age, Member_Cindy.id", nativeQuery = true)
	List<MemberCindy> findOrderByAgeThenById();
	//JPQL
	@Query("SELECT m FROM MemberCindy m ORDER BY m.age, m.id")
	List<MemberCindy> findOrderByAgeThenById2();


}
