package com.kh.zipplanet.domain.review.controller;

import com.kh.zipplanet.domain.review.model.*;
import com.kh.zipplanet.domain.review.service.ReviewReportService;
import com.kh.zipplanet.domain.review.service.ReviewService;
import com.kh.zipplanet.domain.review.service.ReviewZzimService;
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

    @Autowired
    ReviewReportService reviewReportService;

    @Autowired
    ReviewZzimService reviewZzimService;

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<CommonResponse> create(@RequestBody ReviewCreateRequest reviewCreateRequest) {
        CommonResponse response = new CommonResponse();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

//        System.out.println("create review !");
//        System.out.println(reviewCreateRequest);

//        System.out.println("userId : " + reviewCreateRequest.getUserId());
//        System.out.println("totalRate : " + reviewCreateRequest.getTotalRate());
//        System.out.println("transRate : " + reviewCreateRequest.getTransRate());
//        System.out.println("infraRate : " + reviewCreateRequest.getInfraRate());
//        System.out.println("manageRate : " + reviewCreateRequest.getManageRate());
//        System.out.println("lifeRate : " + reviewCreateRequest.getLifeRate());
//        System.out.println("title : " + reviewCreateRequest.getTitle());
//        System.out.println("description : " + reviewCreateRequest.getDescription());
//        System.out.println("jibun : " + reviewCreateRequest.getJibun());
//        System.out.println("pos : " + reviewCreateRequest.getPos());
//        System.out.println("floorsCount : " + reviewCreateRequest.getFloorsCount());
//        System.out.println("pyungCount : " + reviewCreateRequest.getPyungCount());
//        System.out.println("roomInfo : " + reviewCreateRequest.getRoomInfo());
//        System.out.println("roomOption : " + reviewCreateRequest.getRoomOption());
//        System.out.println("contractTypeId : " + reviewCreateRequest.getContractTypeId());
//        System.out.println("startDate : " + reviewCreateRequest.getStartDate());
//        System.out.println("endDate : " + reviewCreateRequest.getEndDate());

        int result = 0;
        try {
            result = reviewService.createReview(reviewCreateRequest);
        } catch(Exception e) {
        }
        System.out.println(result);

        response.setStatus(StatusEnum.OK);
        response.setMessage("success");
        response.setData(result);

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
            @RequestParam(value="sort", defaultValue = "CREATE_DATE") String sort,
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

        System.out.println(reviewUpdateRequest);

        int result = 0;
        try {
            result = reviewService.updateReview(reviewUpdateRequest);
        } catch(Exception e) {
        }
        System.out.println(result);

        response.setStatus(StatusEnum.OK);
        response.setMessage("success");
        response.setData(result);

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
            result = reviewService.deleteReview(reviewDeleteRequest);
        } catch(Exception e) {
        }
        System.out.println(result);

        response.setStatus(StatusEnum.OK);
        response.setMessage("success");
        response.setData(result);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @PostMapping("/report")
    @ResponseBody
    public ResponseEntity<CommonResponse> report(@RequestBody ReviewReportRequest reviewReportRequest) {
        CommonResponse response = new CommonResponse();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

//        System.out.println(reviewUpdateRequest);

        int result = 0;
        try {
            if(reviewReportService.findByReviewIdAndUserId(reviewReportRequest) > 0) { // 이미 신고내역 있는 경우
                response.setStatus(StatusEnum.OK);
                response.setMessage("이미 신고한 리뷰입니다.");
                response.setData(null);
                return new ResponseEntity<>(response, headers, HttpStatus.OK);

            } else { // 신고내역 없는 경우
                result = reviewReportService.reportReview(reviewReportRequest);
            }
        } catch(Exception e) {
        }
        System.out.println(result);

        response.setStatus(StatusEnum.OK);
        response.setMessage("success");
        response.setData(result);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @GetMapping("/selectAllPos")
    @ResponseBody
    public ResponseEntity<CommonResponse> selectAllPos(){
        CommonResponse response = new CommonResponse();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        List<PosVo> list = null;
        try {
            list = reviewService.selectAllPos();
        } catch(Exception e){
        }

        response.setStatus(StatusEnum.OK);
        response.setMessage("success");
        response.setData(list);

        if(list == null) {
            response.setMessage("조회된 결과가 없습니다.");
        }

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @PostMapping("/favorite")
    @ResponseBody
    public ResponseEntity<CommonResponse> updateReviewZzim(@RequestBody ReviewZzimRequest reviewZzimRequest){
        CommonResponse response = new CommonResponse();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        int result = reviewZzimService.updateReviewZzim(reviewZzimRequest);

        response.setStatus(StatusEnum.OK);
        response.setMessage("success");
        response.setData(result);

        if(result == 0) {
            response.setMessage("오류가 발생했습니다.");
        }

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @GetMapping("/detail")
    @ResponseBody
    public ResponseEntity<CommonResponse> detail(@RequestParam(value = "id") String reviewId, @RequestParam(value = "uid", defaultValue = "") String userId) {
        CommonResponse response = new CommonResponse();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        System.out.println("reviewId: " + reviewId);
        System.out.println("userId: " + userId);

        ReviewVo result = reviewService.findById(reviewId, userId);

        response.setStatus(StatusEnum.OK);
        response.setMessage("success");
        response.setData(result);

        if(result == null) {
            response.setMessage("조회된 결과가 없습니다.");
        }

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
