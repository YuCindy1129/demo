package com.example.demo.dto;

import lombok.Data;

@Data
public class AgeRequest {

	private Integer age;
    private Integer count;
    
    public AgeRequest(Integer age, Integer count) {
        this.age = age;
        this.count = count;
    }

    public AgeRequest() {
    }
}





