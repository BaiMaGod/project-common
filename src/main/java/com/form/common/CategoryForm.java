package com.form.common;

import com.utils.CheckUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryForm {

    @Data
    public static class listForm{
        @ApiModelProperty(value = "分类id")
        private Integer categoryId;
        @ApiModelProperty(value = "父类id")
        private Integer parentId;
        @ApiModelProperty(value = "分类类型")
        private String type;

        @ApiModelProperty(value = "第几页")
        private int page;
        @ApiModelProperty(value = "每页条数")
        private int limit;

        @ApiModelProperty(value = "排序规则")
        private String orderByClause;
    }

    @Data
    public static class addForm {
        @ApiModelProperty(value = "父类id")
        private Integer parentId;
        @ApiModelProperty(value = "分类名称",required = true)
        private String categoryName;
        @ApiModelProperty(value = "分类描述",required = true)
        private String introduce;
        @ApiModelProperty(value = "分类类型",required = true)
        private String type;

        public Map<String,String> getErrorInfo(){
            Map<String,String> errorInfos = new HashMap<>();

            if(StringUtils.isEmpty(categoryName)){
                errorInfos.put("categoryName","分类名称 不能为空");
            }
            if(StringUtils.isEmpty(introduce)){
                errorInfos.put("introduce","分类描述 不能为空");
            }
            if(StringUtils.isEmpty(type)){
                errorInfos.put("type","分类类型 不能为空");
            }

            return errorInfos;
        }
    }

    @Data
    public static class updateForm {
        @ApiModelProperty(value = "分类id",required = true)
        private Integer categoryId;
        @ApiModelProperty(value = "父类id")
        private Integer parentId;
        @ApiModelProperty(value = "分类名称")
        private String categoryName;
        @ApiModelProperty(value = "分类描述")
        private String introduce;
        @ApiModelProperty(value = "分类类型")
        private String type;
    }

    @Data
    public static class deleteForm {
        @ApiModelProperty(value = "分类id",required = true)
        private List<Integer> categoryIds;
    }
}
