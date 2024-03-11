package com.kh.zipplanet.domain.review.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReviewUpdateRequest {
    private int reviewId;
    private int userId;
    private int totalRate;
    private int transRate;
    private int infraRate;
    private int manageRate;
    private int lifeRate;
    private String title;
    private String description;
    private String jibun;
    private String pos;
    private String floorsCount;
    private String pyungCount;
    private String roomInfo;
    private String roomOption;
    private String contractTypeId;
    private LocalDate startDate;
    private LocalDate endDate;
}
