package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AgeRequest;
import com.example.demo.entity.MemberCindy;
import com.example.demo.service.ExampleService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/example")
@Slf4j
public class ExampleController {

	@Autowired
	ExampleService examS;
	
	@GetMapping(value = "/index")
	public String getexamlist(Map<String, Object> model) {
		log.info("Example");
		return "nameSelectionPage";
	}
	
	
	//查詢姓名為某某的單筆的會員資料(選單頁)
	//加上jsp頁面(下拉式選單)
	@RequestMapping(value = "/getMemberByName")
	public String result(HttpServletRequest request,
			Map<String, Object> model) {
		String value = request.getParameter("name");
		MemberCindy getname = examS.filternamejsp(value);
		model.put("id", getname.getId());
		model.put("name", getname.getName());
		model.put("age", getname.getAge());
		return "resultPage";
	}
	
	//查詢姓名為某某的單筆的會員資料(結果頁)
	@RequestMapping("result")
	public String result() {
		return "nameSelectionPage";
	}


	//顯示全部資料
	@GetMapping(value = "/examlist")
	public List<MemberCindy> getexamlist(){
		return examS.getexamlist();
	}
	
	
	//查詢大於等於某個age的會員資料
	//NativeSql
	@GetMapping(value = "/age")
	public List<MemberCindy> age(@RequestParam Integer age){
		
		return examS.age(age);
	}
	//JPQL
	@GetMapping(value = "/age2")
	public List<MemberCindy> age2(@RequestParam Integer age){
		
		return examS.age2(age);
	}
	//使用list+for迴圈
	@GetMapping(value = "/agelist")
	public List<MemberCindy> getagelist(@RequestParam Integer Age){
		
		return examS.getagelist(Age);
	}
	//使用lambda + stream
	@GetMapping(value = "/filteragelist")
	public List<MemberCindy> filteragelist(@RequestParam Integer Age){
		
		return examS.filterlist(Age);
	}
	
	
	//查詢姓名不是null的會員資料
	//NativeSql
	@GetMapping(value = "/notnullname")
	public List<MemberCindy> namenotnull(){
			
		return examS.namenotnull();
	}
	//JPQL
	@GetMapping(value = "/notnullname2")
	public List<MemberCindy> namenotnull2(){
			
		return examS.namenotnull2();
	}
	//使用list+for迴圈
	@GetMapping(value = "/notnullnamelist")
	public List<MemberCindy> notnullnamelist(){
			
		return examS.notnullnamelist();
	}
	//使用lambda + stream
	@GetMapping(value = "/filternotnullnamelist")
	public List<MemberCindy> filternotnullnamelist(){
			
		return examS.filternotnullnamelist();
	}
	
	
	//查詢姓名為某某的單筆的會員資料
	//NativeSql
	@GetMapping(value = "/name")
	public MemberCindy name(@RequestParam String name){
		return examS.name(name);
	}
	//JPQL
	@GetMapping(value = "/name2")
	public MemberCindy name2(@RequestParam String name){	
		return examS.name2(name);
	}
	//使用for迴圈
	@GetMapping(value = "/getname")
	public MemberCindy getname(@RequestParam String name){
		return examS.getname(name);
	}
	//使用lambda + stream
	@GetMapping(value = "/filtername")
	public MemberCindy filtername(@RequestParam String name){
		return examS.filtername(name);
	}
	
	
	//查詢10~20間age的會員資料
	//NativeSql
	@GetMapping(value = "/btage")
	public List<MemberCindy> betweenage(){		
		return examS.betweenage();
	}
	//JPQL
	@GetMapping(value = "/btage2")
	public List<MemberCindy> betweenage2(){	
		return examS.betweenage2();
	}
	//使用list+for迴圈
	@GetMapping(value = "/getbtagelist")
	public List<MemberCindy> betweenagelist(){		
		return examS.betweenagelist();
	}
	//使用lambda + stream
	@GetMapping(value = "/filterbtagelist")
	public List<MemberCindy> filterbetweenagelist(){		
		return examS.filterbetweenagelist();
	}
	
	
	//查詢全部資料且根據age進行倒序排列
	//NativeSql
	@GetMapping(value = "/orderByAge")
	public List<MemberCindy> orderByAge(){		
		return examS.orderByAge();
	}
	//JPQL
	@GetMapping(value = "/orderByAge2")
	public List<MemberCindy> orderByAge2(){	
		return examS.orderByAge2();
	}
	//使用list
	@GetMapping(value = "/getdescage")
	public List<MemberCindy> getAllOrderedByAgeDesc() {
	    return examS.getAllOrderedByAgeDesc();
	}
	//使用lambda + stream
	@GetMapping(value = "/finddescage")
	public List<MemberCindy> findAllAndOrderByAgeDesc(){		
		return examS.findAllAndOrderByAgeDesc();
	}
	
	
	//查詢按age分群的個別人數
	//NativeSql
	@GetMapping(value = "/groupByAge")
	public List<AgeRequest> groupByAge(){
				
		return examS.groupByAge();
	}
	//JPQL
	@GetMapping(value = "/groupByAge2")
	public List<AgeRequest> groupByAge2(){
				
		return examS.groupByAge2();
	}
	//使用list+for迴圈
	@GetMapping(value = "/countage")
	public List<AgeRequest> countByAgeGroup(){				
		return examS.countByAgeGroup();
	}
	//使用lambda + stream
	@GetMapping(value = "/getcountage")
	public List<String> getCountByAge(){			
		return examS.getCountByAge();
	}
	
	
	//過濾空白資料，name後面加上'S'，最後依照Age排序
	//使用lambda + stream
	@GetMapping(value = "/filternameorderbyage")
	public List<MemberCindy> filterNameOrderByAge(){
		return examS.filterNameOrderByAge();
	}
	
	
	//取得一個list只有name，且不重複並排序的資料
	//NativeSql
	@GetMapping(value = "/getnamelist")
	public List<String> getNameList(){		
		return examS.getNameList();
	}
	//JPQL
	@GetMapping(value = "/getnamelist2")
	public List<String> getNameList2(){			
		return examS.getNameList2();
	}
	//使用lambda + stream
	@GetMapping(value = "/distinctlistname")
	public List<String> distinctListName(){			
		return examS.distinctListName();
	}
	
	//取得一個 map，其 key 為 ID；value 為 name
	//使用list+for迴圈
	@GetMapping(value = "/getidnamemap")
	public Map<Integer, String> getIdNameMap(){
		return examS.getIdNameMap();
	}
	//使用lambda + stream
	@GetMapping(value = "/fliteridnamemap")
	public Map<Integer, String> fliterIdNameMap(){
		return examS.fliterIdNameMap();
	}
	
	
	//取得第一筆name = FSTOP的資料
	//NativeSql
	@GetMapping(value = "/findfirstbynameFstop")
	public List<MemberCindy> findFirstByNameFSTOP() {
		return examS.findFirstByNameFSTOP();
	}
	//JPQL
	@GetMapping(value = "/findfirstbynameFstop2")
	public List<MemberCindy> findFirstByNameFSTOP2() {
		return examS.findFirstByNameFSTOP2();
	}
	
	//使用list+for迴圈
	@GetMapping(value = "/getfirstnameFstop")
	public MemberCindy getFirstByNameFSTOP(){		
		return examS.getFirstByNameFSTOP();
	}
	//使用lambda + stream
	@GetMapping(value = "/filterfirstnameFstop")
	public MemberCindy filterFirstByNameFSTOP(){		
		return examS.filterFirstByNameFSTOP();
	}
	@GetMapping(value = "/getfirstfstop")
	public MemberCindy getFirstFstop(){		
		return examS.getFirstFstop();
	}
	
	
	//將資料先依據age排序，再依據id排序
	//NativeSql
	@GetMapping(value = "/findorderbyagethenbyid")
	public List<MemberCindy> findOrderByAgeThenById(){
		return examS.findOrderByAgeThenById();
	}
	//JPQL
	@GetMapping(value = "/findorderbyagethenbyid2")
	public List<MemberCindy> findOrderByAgeThenById2(){
		return examS.findOrderByAgeThenById2();
	}
	//使用lambda + stream
	@GetMapping(value = "/sortbyidandname")
	public List<MemberCindy> sortByIdAndAge(){		
		return examS.sortByIdAndAge();
	}
	
	
	//取得一個 string 為所有資料的 name, age|name, age
	//使用lambda + stream
	@GetMapping(value = "/getalldataasstring")
	public String getAllDataAsString(){		
		return examS.getAllDataAsString();
	}

}
