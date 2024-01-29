package com.example.demo.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ExampleRepository;
import com.example.demo.dto.AgeRequest;
import com.example.demo.entity.MemberCindy;

@Service
public class ExampleService {

	@Autowired
	private ExampleRepository examR;
	// 取得所有名字的方法
    public List<String> getAllNames() {
        return examR.findAllNames();
    }
    // 根據名字查詢會員的方法
    public MemberCindy getMemberByName(String name) {
        return examR.findByNamejsp(name);
    }
	
	//顯示資料庫所有資料
	public List<MemberCindy> getexamlist(){
		return examR.findAll();
	}
	
	
	//查詢大於等於某個age的會員資料
	//NativeSql
	public List<MemberCindy> age(Integer age){
		return examR.findByAge(age);
	}
	//JPQL
	public List<MemberCindy> age2(Integer age){
		return examR.findByAge2(age);
	}
	//使用list+for迴圈
	public List<MemberCindy> getagelist(Integer Age) {
        //先取得所有資料
		List<MemberCindy> all = examR.findAll();
		//創建可以放MemberCindy資料的ArrayList
        List<MemberCindy> agelist = new ArrayList<>();

        if (Age != null) {
            for (MemberCindy member : all) {
                if (member.getAge() >= Age) {
                	agelist.add(member);
                }
            }
        } else {
        	agelist = null;
        }

        return agelist;
    }
	//使用lambda + stream
	public List<MemberCindy> filterlist(Integer Age) {
		List<MemberCindy>ageList = getexamlist();
		ageList = ageList.stream()
				//過濾age不是null和大於等於前端提供的age
                .filter(i -> i.getAge()!= null && i.getAge() >= Age) 
                .collect(Collectors.toList());
		return ageList;
	}
	
	
	//查詢姓名不是null的會員資料
	//NativeSql
	public List<MemberCindy> namenotnull(){
		return examR.findBynotnullName();
	}	
	//JPQL
	public List<MemberCindy> namenotnull2(){
		return examR.findBynotnullName2();
	}
	
	//使用list+for迴圈
	public List<MemberCindy> notnullnamelist() {
	    List<MemberCindy> all = examR.findAll();
	    List<MemberCindy> namelist = new ArrayList<>();

	    for (MemberCindy member : all) {
            if (member.getName() != null && !member.getName().isEmpty()) {
            	namelist.add(member);
            }
        }
	    return namelist;
	}
	//使用lambda + stream
	public List<MemberCindy> filternotnullnamelist() {
		List<MemberCindy>filternameList = getexamlist();
		filternameList = filternameList.stream()
	            .filter(i -> i.getName()!= null && !i.getName().isEmpty()) 
	            .collect(Collectors.toList());
		return filternameList;
	}
	
	
	//查詢姓名為某某的單筆的會員資料
	//NativeSql
	public MemberCindy name(String name){
		return examR.findByName(name);
	}	
	//JPQL
	public MemberCindy name2(String name){
		return examR.findByName2(name);
	}
	//使用list+for迴圈
	public MemberCindy getname(String name) {
	    List<MemberCindy> all = examR.findAll();
	    for (MemberCindy member : all) {
	    	//equals比較字串是否等
            if (name.equals(member.getName())) {
            	return member;
            }
        }
	    return null;
	}
	//使用lambda + stream
	public MemberCindy filtername(String name) {
		List<MemberCindy>nameList = getexamlist();
		return nameList.stream()
	            .filter(i -> name.equals(i.getName()))
	            //回傳第一個符合條件的資料
	            .findFirst()
	            //如果都沒符合回傳null
	            .orElse(null);
	}

	
	//查詢姓名為某某的單筆的會員資料
	//加上jsp頁面
	public MemberCindy filternamejsp(String name) {
		List<MemberCindy>nameList = getexamlist();
		nameList=nameList.stream()
	            .filter(i -> Objects.equals(i.getName(), name))
	            .collect(Collectors.toList());
		//檢查新的列表stream是不是空的，如果不為空，回傳第一筆資料，不然就回傳null
		if (!nameList.isEmpty()) {
	        MemberCindy nameOne = nameList.get(0);
	        return nameOne;
	    } else {
	        return null;
	    }
	}
	
	
	//查詢10~20間age的會員資料
	//NativeSql
	public List<MemberCindy> betweenage(){
		return examR.findBetweenAge();
	}	
	//JPQL
	public List<MemberCindy> betweenage2(){
		return examR.findBetweenAge2();
	}
	//使用list+for迴圈
	public List<MemberCindy> betweenagelist() {
		List<MemberCindy> all = examR.findAll();
		List<MemberCindy> agelist = new ArrayList<>();

		for (MemberCindy member : all) {
	        if (member.getAge() != null && member.getAge() >= 10 && member.getAge() <= 20) {
	        	agelist.add(member);
	        }
	    }
		return agelist;
	}
	//使用lambda + stream
	public List<MemberCindy> filterbetweenagelist() {
		List<MemberCindy>filterageList = getexamlist();
		filterageList = filterageList.stream()
		        .filter(i -> i.getName()!= null && i.getAge() >= 10 && i.getAge() <= 20) 
		        .collect(Collectors.toList());
		return filterageList;
	}
	
	
	//查詢全部資料且根據age進行倒序(DESC)排列
	//NativeSql
	public List<MemberCindy> orderByAge(){
		return examR.findByAgeDESC();
	}	
	//JPQL
	public List<MemberCindy> orderByAge2(){
		return examR.findByAgeDESC2();
	}
	//使用list
	public List<MemberCindy> getAllOrderedByAgeDesc() {
	    return examR.findAllByOrderByAgeDesc();
	}
	//使用lambda + stream
	public List<MemberCindy> findAllAndOrderByAgeDesc() {
	    List<MemberCindy> all = examR.findAll();
	    List<MemberCindy> descageList = all.stream()
	            .sorted((a1, a2) -> Integer.compare(a2.getAge(), a1.getAge()))
	            .collect(Collectors.toList());

	    return descageList;
	}
	
	
	//查詢按age分群的個別人數
	//NativeSql
	public List<AgeRequest> groupByAge() {
	    List<Object[]> result = examR.findGroupByAge();
	    return result.stream()
	            .map(objects -> new AgeRequest((Integer) objects[0], (Integer) objects[1]))
	            .collect(Collectors.toList());
	}
	//JPQL
	public List<AgeRequest> groupByAge2(){
		return examR.findGroupByAge2();
	}
	//使用list+for迴圈
	public List<AgeRequest> countByAgeGroup() {
		List<MemberCindy> all = getexamlist();
        List<AgeRequest> result = new ArrayList<>();

        for (MemberCindy member : all) {
            int age = member.getAge();
            AgeRequest existingDTO = findAgeGroupDTO(result, age);
            if (existingDTO != null) {
                existingDTO.setCount(existingDTO.getCount() + 1);
            } else {
                result.add(new AgeRequest(age, 1));
            }
        }

        return result;
    }
    private AgeRequest findAgeGroupDTO(List<AgeRequest> list, int age) {
        for (AgeRequest dto : list) {
            if (dto.getAge() == age) {
                return dto;
            }
        }
        return null;
    }
    //使用lambda + stream
    public List<String> getCountByAge() {
    	List<MemberCindy> all = getexamlist();
    	return all.stream()
    			//Collectors.groupingBy 按指定條件，將資料進行分組。(MemberCindy::getAge, Collectors.counting())(分組條件, 收集器，用於計算每個分組的資料數量)
                .collect(Collectors.groupingBy(MemberCindy::getAge, Collectors.counting()))
                .entrySet().stream()
                .map(entry -> "Age: " + entry.getKey() + ", Count: " + entry.getValue())
                .collect(Collectors.toList());
    }


    //過濾空白資料，name後面加上'S'，最後依照Age排序
	public List<MemberCindy> filterNameOrderByAge(){
		List<MemberCindy> all = getexamlist();
		List<MemberCindy> processed = all.stream()
				.filter(i -> i.getName() !=null && !i.getName().isEmpty())
				.map(i -> {
					//concat 能將字和字連接在一起
                    i.setName(i.getName().concat("S"));
                    return i;
                })
				.collect(Collectors.toList());
		//按照Age排序
		processed.sort((i1, i2) -> Integer.compare(i1.getAge(), i2.getAge()));
		return processed;
	}
	
	
	//取得一個list只有name，且不重複(DISTINCT)並排序的資料
	//NativeSql
	public List<String> getNameList(){
		return examR.findNameList();
	}
	//JPQL
	public List<String> getNameList2(){
		return examR.findNameList2();
	}
	//使用lambda + stream
	public List<String> distinctListName() {
	    List<MemberCindy> all = getexamlist();
	    List<String> processed = all.stream()
	    		.filter(i -> i.getName() != null)
	            .map(i -> i.getName())
	            .distinct()
	            .sorted()
	            .collect(Collectors.toList());
	    return processed;
	}
	
	
	//取得一個 map，其 key 為 ID；value 為 name
	//使用list+for迴圈
		public Map<Integer, String> getIdNameMap() {
			List<MemberCindy> all = getexamlist();
			Map<Integer, String> idNameMap = new HashMap<>();

	        for (MemberCindy member : all) {
	        	if (!idNameMap.containsKey(member.getId())) {
	                idNameMap.put(member.getId(), member.getName());
	            }
	        }
	        return idNameMap;
	    }
		//使用lambda + stream
		public Map<Integer, String> fliterIdNameMap() {
			List<MemberCindy> all = getexamlist();
			Map<Integer, String> idNameMap = all.stream()
	                .filter(i -> i.getName()!=null)  
	                .collect(Collectors.toMap(i -> i.getId(), i -> i.getName()));

	        return idNameMap;
	    }

	
	
	//取得第一筆name = FSTOP的資料
	//NativeSql
	public List<MemberCindy> findFirstByNameFSTOP(){
		return examR.findFirstByNameFSTOP();
	}
	//JPQL
	public List<MemberCindy> findFirstByNameFSTOP2(){
		return examR.findFirstByNameFSTOP2();
	}
	//使用list+for迴圈
	public MemberCindy getFirstByNameFSTOP() {
        List<MemberCindy> all = getexamlist();

        for (MemberCindy member : all) {
            if ("FSTOP".equals(member.getName())) {
                return member; 
            }
        }

        return null; 
    }
	//使用lambda + stream
	public MemberCindy filterFirstByNameFSTOP() {
        List<MemberCindy> all = getexamlist();

        Optional<MemberCindy> first = all.stream()
                .filter(member -> "FSTOP".equals(member.getName()))
                .findFirst();

        return first.orElse(null);
    }
	public MemberCindy getFirstFstop() {
	    return examR.findFirstByName("FSTOP");
	}
	
	
	//將資料先依據age排序，再依據id排序
	//NativeSql
	public List<MemberCindy> findOrderByAgeThenById(){
		return examR.findOrderByAgeThenById();
	}
	//JPQL
	public List<MemberCindy> findOrderByAgeThenById2(){
		return examR.findOrderByAgeThenById2();
	}
	//使用lambda + stream
	public List<MemberCindy> sortByIdAndAge() {
		List<MemberCindy> all = getexamlist();
        List<MemberCindy> sortedList = all.stream()
                .sorted(Comparator.comparing(MemberCindy::getAge)
                        .thenComparing(MemberCindy::getId))
                .collect(Collectors.toList());

        return sortedList;
    }
	
	
	//取得一個 string 為所有資料的 name, age|name, age
	//使用lambda + stream
	public String getAllDataAsString() {
		List<MemberCindy> all = getexamlist();
		String result = all.stream()
                .map(i -> i.getName() + ", " + i.getAge())
                .collect(Collectors.joining("|")); 

        return result;
    }


}
