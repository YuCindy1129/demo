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
	
    
    //查詢大於等於某個age的會員資料
	//NativeSql  (以實際建立的database命名為主)
    //有@Param("age")，就不需要?1的1了
	@Query(value = "SELECT * FROM Member_Cindy WHERE Member_Cindy.age >= ?",nativeQuery = true)
	List<MemberCindy> findByAge(@Param("age") Integer age);
	
	//JPQL  (以entity的命名為主)
	//有?1的1，就不需要@Param("age")
	@Query(value = "SELECT m FROM MemberCindy m WHERE m.age >= ?1")
	List<MemberCindy> findByAge2(Integer age);
	
	
	//查詢姓名不是null的會員資料
	//NativeSql
	@Query(value = "SELECT * FROM Member_Cindy WHERE Member_Cindy.name IS NOT NULL",nativeQuery = true)
	List<MemberCindy> findBynotnullName();
	//JPQL
	@Query(value = "SELECT m FROM MemberCindy m WHERE m.name IS NOT NULL")
	List<MemberCindy> findBynotnullName2();
	
	
	//查詢姓名為某某的單筆的會員資料  (只是單筆資料，就不是List)
	//NativeSql
	@Query(value = "SELECT * FROM Member_Cindy WHERE Member_Cindy.name = ? LIMIT 1", nativeQuery = true)
	MemberCindy findByName(@Param("name") String name);
	//JPQL
	@Query("SELECT m FROM MemberCindy m WHERE m.name = :name")
	MemberCindy findByName2(@Param("name") String name);
		
	
	//查詢10~20間age的會員資料
	//NativeSql
	@Query(value = "SELECT * FROM Member_Cindy WHERE Member_Cindy.age BETWEEN 10 AND 20",nativeQuery = true)
	List<MemberCindy> findBetweenAge();
	//JPQL
	@Query("SELECT m FROM MemberCindy m WHERE m.age BETWEEN 10 AND 20")
	List<MemberCindy> findBetweenAge2();
	
	
	//查詢全部資料且根據age進行倒序(DESC)排列
	//NativeSql
	@Query(value = "SELECT * FROM Member_Cindy ORDER BY age DESC",nativeQuery = true)
	List<MemberCindy> findByAgeDESC();
	//JPQL
	@Query(value = "SELECT m FROM MemberCindy m ORDER BY age DESC")
	List<MemberCindy> findByAgeDESC2();
	//使用list
	List<MemberCindy> findAllByOrderByAgeDesc();

	
	//查詢按age分群的個別人數
	//NativeSql
	@Query(value = "SELECT age, COUNT(*) as AgeCount FROM Member_Cindy GROUP BY age", nativeQuery = true)
	List<Object[]> findGroupByAge();
	//JPQL
	@Query("SELECT new com.example.demo.dto.AgeRequest(m.age, CAST(COUNT(m) AS java.lang.Integer)) FROM MemberCindy m GROUP BY m.age")
	List<AgeRequest> findGroupByAge2();

	
	//取得一個list只有name，且不重複(DISTINCT)並排序的資料
	//NativeSql
	@Query(value = "SELECT DISTINCT Member_Cindy.name FROM Member_Cindy ORDER BY Member_Cindy.name", nativeQuery = true)
	List<String> findNameList();
	//JPQL
	@Query("SELECT DISTINCT m.name FROM MemberCindy m ORDER BY m.name")
    List<String> findNameList2();
	
	
	MemberCindy findFirstByName(String name);
	
	
	//取得第一筆name = FSTOP的資料
	//NativeSql
	@Query(value = "SELECT * FROM Member_Cindy WHERE Member_Cindy.name = 'FSTOP' ORDER BY Member_Cindy.id LIMIT 1", nativeQuery = true)
    List<MemberCindy> findFirstByNameFSTOP();
	//JPQL
	@Query("SELECT m FROM MemberCindy m WHERE m.name = 'FSTOP' ORDER BY m.id LIMIT 1")
    List<MemberCindy> findFirstByNameFSTOP2();

	
	//將資料先依據age排序，再依據id排序
	//NativeSql
	@Query(value = "SELECT * FROM Member_Cindy ORDER BY Member_Cindy.age, Member_Cindy.id", nativeQuery = true)
	List<MemberCindy> findOrderByAgeThenById();
	//JPQL
	@Query("SELECT m FROM MemberCindy m ORDER BY m.age, m.id")
	List<MemberCindy> findOrderByAgeThenById2();


}
