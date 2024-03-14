package com.kh.zipplanet.domain.user.controller;

import com.kh.zipplanet.domain.user.model.*;
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

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value="/login")
    @ResponseBody
    public ResponseEntity<CommonResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
        CommonResponse response = new CommonResponse();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        if(userLoginRequest.getUsername() == null || userLoginRequest.getUsername().equals("")) {
            response.setMessage("아이디를 입력하지 않으셨습니다.");
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        }

        if(userLoginRequest.getPassword() == null || userLoginRequest.getPassword().equals("")) {
            response.setMessage("비밀번호를 입력하지 않으셨습니다.");
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        }

        User user = null;
        try {
            user = userService.login(userLoginRequest);
        } catch (Exception e) {
            System.out.println(e);
        }

        response.setStatus(StatusEnum.OK);
        response.setMessage("success");
        response.setData(user);

        if(user == null) {
            response.setMessage("일치하는 유저가 없습니다. 아이디,패스워드를 다시 확인해주세요.");
        }

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<CommonResponse> signup(@RequestBody UserSignupRequest userSignupRequest) {
        CommonResponse response = new CommonResponse();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        if(userSignupRequest.getUsername() == null || userSignupRequest.getUsername().equals("")) {
            response.setMessage("아이디를 입력하지 않으셨습니다.");
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        }

        if((userSignupRequest.getPassword() == null || userSignupRequest.getPassword().equals("")) ||
                userSignupRequest.getPasswordConfirm() == null || userSignupRequest.getPasswordConfirm().equals("")) {
            response.setMessage("비밀번호를 입력하지 않으셨습니다.");
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        }

        if(!userSignupRequest.getPassword().equals(userSignupRequest.getPasswordConfirm())) {
            response.setMessage("비밀번호가 일치하지 않습니다.");
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        }
        
        if(userSignupRequest.getPhone() == null || userSignupRequest.getPhone().equals("")) {
            response.setMessage("휴대폰 번호를 입력하지 않으셨습니다.");
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        }

        User user = null;
        try {
            int result = userService.signUp(userSignupRequest);
            if(result > 0) { // insert 후 row 가져오기 (다른 방법이있을거야..)
                user = userService.getUserByUsername(userSignupRequest.getUsername());
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        response.setStatus(StatusEnum.OK);
        response.setMessage("success");
        response.setData(user);
        if(user == null) {
            response.setMessage("회원가입을 실패했습니다.");
        }

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @PostMapping("/findId")
    @ResponseBody
    public ResponseEntity<CommonResponse> findId(@RequestBody UserFindIdRequest userFindIdRequest){
        CommonResponse response = new CommonResponse();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        User user = null;
        try {
            user = userService.findId(userFindIdRequest);
        } catch (Exception e) {
            System.out.println(e);
        }

        response.setStatus(StatusEnum.OK);
        response.setMessage("success");
        response.setData(user.getUsername());
        if(user.getUsername() == null) {
            response.setMessage("일치하는 유저가 없습니다. 휴대폰 번호를 다시 확인해주세요.");
        }

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @PostMapping("/findPwd")
    @ResponseBody
    public ResponseEntity<CommonResponse> findPwd(@RequestBody UserFindPwdRequest userFindPwdRequest){
        CommonResponse response = new CommonResponse();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        String pwd = null;
        try {
            pwd = userService.findPwd(userFindPwdRequest);
        } catch (Exception e) {
            System.out.println(e);
        }

        response.setStatus(StatusEnum.OK);
        response.setMessage("success");
        response.setData(pwd);
        if(pwd == null) {
            response.setMessage("일치하는 유저가 없습니다. 아이디, 휴대폰번호를 다시 확인해주세요.");
        }

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @PostMapping("/updateUser")
    @ResponseBody
    public ResponseEntity<CommonResponse> updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {
        CommonResponse response = new CommonResponse();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        int result = 0;
        try {
            result = userService.updateUser(userUpdateRequest);
        } catch (Exception e){
            System.out.println(e);
        }
        System.out.println(result);
        response.setStatus(StatusEnum.OK);
        response.setMessage("success");
        response.setData(result);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
