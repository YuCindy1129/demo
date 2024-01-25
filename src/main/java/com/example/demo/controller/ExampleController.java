package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ExampleRequest;
import com.example.demo.entity.Example;
import com.example.demo.service.ExampleService;

@RestController
@RequestMapping("/example")
public class ExampleController {

	@Autowired
	ExampleService examS;
	
	//顯示全部資料
	@GetMapping(value = "/examlist")
	public List<Example> getexamlist(){
		return examS.getexamlist();
	}
	
	//使用examS方法判斷註冊1
	@PostMapping(value = "/signup")
	public Example signup(@RequestBody Example su) {
		return examS.signup(su);
	}
	
	//使用examS方法判斷登入1
	@PostMapping(value = "/login")
	public Example login(@RequestBody Example li) {
		return examS.login(li);
	}
	
	@PostMapping(value = "/signup2")
	public String signup2(@RequestBody Example su) {
		return examS.signup2(su);
	}
	
	@PostMapping(value = "/login2")
	public String login2(@RequestBody Example li) {
		return examS.login2(li);
	}
	
	@PostMapping(value = "/transfer")
	public String transfer(@RequestBody ExampleRequest exampleRequest) {
		String result = examS.transfer(exampleRequest.getFrom(), exampleRequest.getTo(), exampleRequest.getMoney());
		return result;
	}

}
