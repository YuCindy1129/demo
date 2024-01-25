package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.ExampleRepository;
import com.example.demo.entity.Example;

@Service
public class ExampleService {

	@Autowired
	private ExampleRepository examR;
	
	//顯示資料庫所有資料
	public List<Example> getexamlist(){
		return examR.findAll();
	}
	
	//判斷註冊1輸入
	public Example signup(Example su) {
		Integer sid = su.getId();
		Optional<Example> oid = examR.findById(sid);
		if(oid.isEmpty()) {
			return examR.save(su);
		}
		else {
			return null;
		}
	}
	
	//判斷登入1輸入
	public Example login(Example li) {
		Integer lid = li.getId();
		String lpw = li.getPassword();
		Example example = examR.findByIdAndPassword(lid, lpw);
		if(example != null) {
			return example;
		}
		else {
			return null;
		}
	}
	
	//判斷註冊2輸入
	public String signup2(Example su) {
		Integer sid = su.getId();
		Optional<Example> oid = examR.findById(sid);
		if (oid.isEmpty()) {
			examR.save(su);
			return "註冊成功";
		}
		else {
			return "帳號已存在，註冊失敗";
		}
	}
	
	//判斷登入2輸入
	public String login2(Example li) {
		Integer lid = li.getId();
		String lpw = li.getPassword();
		Example example = examR.findByIdAndPassword(lid, lpw);
		Optional<Example> fid = examR.findById(lid);
		if (example!=null) {
			String lname = example.getName();
			return "登入成功，你好!"+lname;
		}
		else if (fid.isEmpty()) {
			return "帳號不存在，登入失敗";
		}
		else {
			return "密碼錯誤，登入失敗";
		}
	}
	
	//判斷轉帳
	@Transactional
	public String transfer(Integer fromAccountId, Integer toAccountId, Integer money) {
		Optional<Example> fromid = examR.findById(fromAccountId);
		Optional<Example> toid = examR.findById(toAccountId);
		
		if(fromid.isPresent() && toid.isPresent()) {
			Example fromAccount = fromid.get();
			Example toAccount = toid.get();
			
			if(fromAccount.getId()!=toAccount.getId()) {
				
				if(fromAccount.getMoney() >= money) {
					fromAccount.setMoney(fromAccount.getMoney()- money);
					examR.save(fromAccount);
					
					toAccount.setMoney(toAccount.getMoney()+money);
					examR.save(toAccount);
					return "轉帳成功";
				}
				else {
					return "餘額不足，轉帳失敗";
				}
			}
			else {
				return "轉出及轉入帳號相同，轉帳失敗";
			}
		}
		else if (fromid.isEmpty()) {
			return "轉出帳號不存在，轉帳失敗";
		}
		else {
			return "轉入帳號不存在，轉帳失敗";
		}
	}
}
