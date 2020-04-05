package com.lx.mms.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class SearchLogDto {

    /**
     *  类型
     */
    private Integer type;

    /**
     *
     */
    private String beforeSeg;

    private String afterSeg;

    private String operator;

    private LocalDateTime fromTime;

    private LocalDateTime toTime;
}
