package com.form.common;

import com.form.PageForm;
import com.form.UserInfoForm;
import com.utils.CheckUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentForm {

    @Data
    public static class listForm extends PageForm {
        @ApiModelProperty(value = "评论id")
        private Integer commentId;
        @ApiModelProperty(value = "用户id")
        private Integer userId;
        @ApiModelProperty(value = "目标id")
        private Integer targetId;
        @ApiModelProperty(value = "类型")
        private String type;
        @ApiModelProperty(value = "起始-分数")
        private Integer startScore;
        @ApiModelProperty(value = "结束-分数")
        private Integer endScore;
        @ApiModelProperty(value = "内容")
        private String context;

        @ApiModelProperty(value = "起始-创建时间")
        private Date startCreateTime;
        @ApiModelProperty(value = "结束-创建时间")
        private Date endCreateTime;
        @ApiModelProperty(value = "起始-修改时间")
        private Date startUpdateTime;
        @ApiModelProperty(value = "结束-修改时间")
        private Date endUpdateTime;

        @ApiModelProperty(value = "排序规则")
        private String orderByClause;
    }

    @Data
    public static class addForm {
        @ApiModelProperty(value = "用户id",hidden = true)
        private Integer userId;
        @ApiModelProperty(value = "目标id")
        private Integer targetId;
        @ApiModelProperty(value = "类型",required = true)
        private String type;
        @ApiModelProperty(value = "分数")
        private Integer score;
        @ApiModelProperty(value = "内容",required = true)
        private String context;



        public Map<String,String> getErrorInfo(){
            Map<String,String> errorInfos = new HashMap<>();


            if(type==null){
                errorInfos.put("type","类型不能为空");
            }
            if(context==null){
                errorInfos.put("context","内容不能为空");
            }

            return errorInfos;
        }
    }

    @Data
    public static class updateForm  extends UserInfoForm {
        @ApiModelProperty(value = "评论id",required = true)
        private Integer commentId;
        @ApiModelProperty(value = "内容",required = true)
        private String context;



        public Map<String,String> getErrorInfo(){
            Map<String,String> errorInfos = new HashMap<>();

            if(commentId==null){
                errorInfos.put("commentId","评论id不能为空");
            }

            return errorInfos;
        }
    }

    @Data
    public static class deleteForm extends UserInfoForm{
        @ApiModelProperty(value = "id,多个id用逗号分隔",required = true)
        private List<Integer> ids;


    }
}
