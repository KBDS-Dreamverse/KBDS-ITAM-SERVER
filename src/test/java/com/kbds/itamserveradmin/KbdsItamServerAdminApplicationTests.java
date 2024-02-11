package com.kbds.itamserveradmin;

import com.kbds.itamserveradmin.domain.user.dto.UserInfoRes;
import com.kbds.itamserveradmin.domain.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class KbdsItamServerAdminApplicationTests {

	@Autowired
	private UserService userService;
	@Test
	void contextLoads() {
	}

	@Test
	public void testGetUser(){
		UserInfoRes userInfoRes =userService.GetUserInfo("L20");
		System.out.println(userInfoRes.toString());
		System.out.println(userInfoRes.getUserName());
		System.out.println(userInfoRes.getUserId());
		System.out.println(userInfoRes.getUserRole().getCode());
		System.out.println(userInfoRes.getUserRole().getValue());
	}
}
