package com.lx.mms.entity.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *  日志记录的高级查询条件
 */
@Getter
@Setter
@ToString
public class SearchLogParam {

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

    private String fromTime;

    private String toTime;
}
