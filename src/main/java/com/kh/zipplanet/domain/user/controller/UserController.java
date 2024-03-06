package com.kh.zipplanet.domain.user.controller;

import com.kh.zipplanet.domain.user.model.LoginRequest;
import com.kh.zipplanet.domain.user.model.User;
import com.kh.zipplanet.domain.user.service.UserService;
import com.kh.zipplanet.global.common.CommonResponse;
import com.kh.zipplanet.global.common.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/hello")
    public ResponseEntity<String> hello() {
        System.out.println("hello");
        return ResponseEntity.ok("hello");
    }

    @GetMapping(value = "/hello_user")
    @ResponseBody
    public ResponseEntity<CommonResponse> helloUser() {
        System.out.println("hello_user");

        List<User> userList = userService.getUser();
        System.out.println(userList.size());

        CommonResponse message = new CommonResponse();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        message.setStatus(StatusEnum.OK);
        message.setMessage("success");
        message.setData(userList);

        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
    @GetMapping(value="/login")
    @ResponseBody
    public ResponseEntity<CommonResponse> login() {
        System.out.println("login");

//        System.out.println(loginRequest.getId());
//        System.out.println(loginRequest.getPw());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setId("test");
        loginRequest.setPw("1234");

        User user = userService.login(loginRequest);
        System.out.println(user);

        CommonResponse message = new CommonResponse();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        message.setStatus(StatusEnum.OK);
        message.setMessage("success");
        message.setData(user);

        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
}
