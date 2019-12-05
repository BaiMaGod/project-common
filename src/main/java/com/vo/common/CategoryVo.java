package com.vo.common;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CategoryVo {
    private Integer categoryId;

    private Integer parentId;

    private String categoryName;

    private String introduce;

    private String type;

    private Date createTime;

    private Date updateTime;

    private List<CategoryVo> children;
}