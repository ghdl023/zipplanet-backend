package com.kh.zipplanet.domain.user.controller;

import com.kh.zipplanet.domain.user.model.LoginRequest;
import com.kh.zipplanet.domain.user.model.User;
import com.kh.zipplanet.domain.user.service.UserService;
import com.kh.zipplanet.global.common.CommonResponse;
import com.kh.zipplanet.global.common.StatusEnum;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value="/login", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<CommonResponse> login2(HttpServletRequest request) {
        System.out.println("login!!");

//        System.out.println(loginRequest.getId());
//        System.out.println(loginRequest.getPw());

        User user = userService.getUser();
        System.out.println(user);


        CommonResponse message = new CommonResponse();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        message.setStatus(StatusEnum.OK);
        message.setMessage("성공 코드");
        message.setData(user);

        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
    @RequestMapping(value="/login_post", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CommonResponse> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("login!!");

//        System.out.println(loginRequest.getId());
//        System.out.println(loginRequest.getPw());

        User user = userService.getUser();
        System.out.println(user);


        CommonResponse message = new CommonResponse();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        message.setStatus(StatusEnum.OK);
        message.setMessage("성공 코드");
        message.setData(user);

        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
}
