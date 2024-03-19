package com.kh.zipplanet.domain.review.model;

import java.time.LocalDateTime;
import lombok.Data;
@Data
public class ReviewMyReportRequest {
    private int reviewId;
    private int contractTypeId;
    private int userId;
    private String creator;
    private String jibun;
    private String pos;
    private String title;
    private String description;
    private int totalRate;
    private int transRate;
    private int manageRate;
    private int infraRate;
    private int lifeRate;
    private String floorsCount;
    private int pyungCount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String roomInfo;
    private String roomOption;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private int likeCount;
    private int viewCount;
    private String zzimYn;
    private int reportId;
    private LocalDateTime reportDate;
    private String solveYn;
    private int reportTypeId;
}
