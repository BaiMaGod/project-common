package com.vo.common;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommentVo {
    private Integer commentId;

    private Integer userId;

    private Integer targetId;

    private String type;

    private Integer score;

    private String context;

    private Date createTime;

    private Date updateTime;

    private List<CommentVo> children;

}
