package com.kh.zipplanet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = {"com.kh.zipplanet.mapper", "com.kh.zipplanet.domain.user.mapper"})
@SpringBootApplication
public class ZipplanetApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipplanetApplication.class, args);
	}

}
