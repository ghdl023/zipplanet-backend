package com.kh.zipplanet.domain.mail.controller;

import com.kh.zipplanet.domain.mail.model.EmailMessage;
import com.kh.zipplanet.domain.mail.service.EmailMessageService;
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
@RequestMapping("/api/mail")
@CrossOrigin
public class EmailMessageController {

    @Autowired
    EmailMessageService emailMessageService;

    @PostMapping("/sendMail")
    @ResponseBody
    public ResponseEntity<CommonResponse> sendMail(@RequestBody EmailMessage emailMessage){
        CommonResponse response = new CommonResponse();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        try {
            emailMessageService.sendMail(emailMessage);
        } catch (Exception e){
            System.out.println(e);
        }

        response.setStatus(StatusEnum.OK);
        response.setMessage("success");
        response.setData("good");

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

}
