package com.model.common;

import com.vo.common.CategoryVo;
import lombok.Data;

import java.util.List;

@Data
public class CategoryExt extends Category{
    private List<CategoryVo> children;
}

