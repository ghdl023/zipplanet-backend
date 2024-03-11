package com.kh.zipplanet.domain.review.controller;

import com.kh.zipplanet.domain.review.model.*;
import com.kh.zipplanet.domain.review.service.ReviewService;
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
@RequestMapping("/api/review")
@CrossOrigin
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<CommonResponse> create(@RequestBody ReviewCreateRequest reviewCreateRequest) {
        CommonResponse response = new CommonResponse();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

//        System.out.println(reviewCreateRequest);

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

    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<CommonResponse> searchV2(
            @RequestParam(value="searchType") String searchType,
            @RequestParam(value="keyword", defaultValue = "") String keyword,
            @RequestParam(value="gu", defaultValue = "") String gu,
            @RequestParam(value="dong", defaultValue = "") String dong,
            @RequestParam(value="contractTypeId", defaultValue = "") String contractTypeId,
            @RequestParam(value="rate", defaultValue = "5") int rate,
            @RequestParam(value="pos") String pos,
            @RequestParam(value="sort", defaultValue = "LIKE_COUNT") String sort,
            @RequestParam(value="offset") int offset,
            @RequestParam(value="limit", defaultValue="2") int limit
    ) {
        CommonResponse response = new CommonResponse();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        System.out.println("searchType:" + searchType);
        System.out.println("keyword:" + keyword);
        System.out.println("gu:" + gu);
        System.out.println("dong:" + dong);
        System.out.println("contractTypeId:" + contractTypeId);
        System.out.println("rate:" + rate);
        System.out.println("pos:" + pos);
        System.out.println("sort:" + sort);
        System.out.println("offset:" + offset);
        System.out.println("limit:" + limit);

        List<ReviewVo> reviewList = null;
        int totalCount = 0;
        try {
            reviewList = reviewService.search(searchType, keyword, gu, dong, contractTypeId, rate, pos, sort, offset, limit);
            totalCount = reviewService.searchTotalCount(searchType, keyword, gu, dong, contractTypeId, rate, pos, sort, offset, limit);
            System.out.println(totalCount);
        } catch(Exception e) {
        }
        System.out.println(reviewList);

        ReviewListPagingResponse pagingresponse = new ReviewListPagingResponse();
        pagingresponse.setReviews(reviewList);
        pagingresponse.setTotalCount(totalCount);

        response.setStatus(StatusEnum.OK);
        response.setMessage("success");
        response.setData(pagingresponse);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<CommonResponse> update(@RequestBody ReviewUpdateRequest reviewUpdateRequest) {
        CommonResponse response = new CommonResponse();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

//        System.out.println(reviewUpdateRequest);

        int result = 0;
        try {
            result = reviewService.update(reviewUpdateRequest);
        } catch(Exception e) {
        }
        System.out.println(result);

        response.setStatus(StatusEnum.OK);
        response.setMessage("success");
        response.setData(null);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @PostMapping("/delete")
    @ResponseBody
    public ResponseEntity<CommonResponse> delete(@RequestBody ReviewDeleteRequest reviewDeleteRequest) {
        CommonResponse response = new CommonResponse();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

//        System.out.println(reviewUpdateRequest);

        int result = 0;
        try {
            result = reviewService.delete(reviewDeleteRequest);
        } catch(Exception e) {
        }
        System.out.println(result);

        response.setStatus(StatusEnum.OK);
        response.setMessage("success");
        response.setData(null);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
