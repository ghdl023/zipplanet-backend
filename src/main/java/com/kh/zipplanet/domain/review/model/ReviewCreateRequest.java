package com.kh.zipplanet.domain.review.model;

import lombok.Data;

@Data
public class ReviewCreateRequest {
    private int totalRate;
    private int transRate;
    private int infraRate;
    private int manageRate;
    private int lifeRate;
    private String title;
    private String description;
    private String jibun;
    private String floorsCount;
    private String pyungCount;
    private String roomCount;
    private String roomOption;
    private String contractType = "1";
}
