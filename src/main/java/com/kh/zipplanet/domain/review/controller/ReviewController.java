package com.kh.zipplanet.domain.review.controller;

import com.kh.zipplanet.domain.review.model.ReviewCreateRequest;
import com.kh.zipplanet.domain.review.service.ReviewService;
import com.kh.zipplanet.global.common.CommonResponse;
import com.kh.zipplanet.global.common.StatusEnum;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

@RestController
@RequestMapping("/api/review")
@CrossOrigin
public class ReviewController {

    ReviewService reviewService;

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<CommonResponse> create(@RequestBody ReviewCreateRequest reviewCreateRequest) {
        CommonResponse response = new CommonResponse();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        System.out.println(reviewCreateRequest);

        int result = 0;
        try {
            result = reviewService.create(reviewCreateRequest);
        } catch(Exception e) {
        }
        System.out.println(result);

        response.setStatus(StatusEnum.OK);
        response.setMessage("success");
        response.setData(null);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);

    }
}
