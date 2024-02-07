package com.kbds.itamserveradmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class KbdsItamServerAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(KbdsItamServerAdminApplication.class, args);


		// 메모리 사용량 출력
		long heapSize = Runtime.getRuntime().totalMemory();
		System.out.println("HEAP Size(M) : "+ heapSize / (1024*1024) + " MB");

	}

}
