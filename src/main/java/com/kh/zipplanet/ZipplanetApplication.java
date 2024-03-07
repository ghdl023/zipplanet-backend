package com.kh.zipplanet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = {"com.kh.zipplanet.domain.user.mapper", "com.kh.zipplanet.domain.review.mapper"})
@SpringBootApplication
public class ZipplanetApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipplanetApplication.class, args);
	}

}
