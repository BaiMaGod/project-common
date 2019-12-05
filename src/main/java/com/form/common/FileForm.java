package com.form.common;

import com.form.PageForm;
import com.form.UserInfoForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileForm {

    @Data
    public static class listForm extends PageForm {
        @ApiModelProperty(value = "文件id")
        private Integer fileId;
        @ApiModelProperty(value = "用户id")
        private Integer userId;
        @ApiModelProperty(value = "文件上传时真实名")
        private String fileRealName;
        @ApiModelProperty(value = "文件后缀名")
        private String fileSuffix;
        @ApiModelProperty(value = "类型")
        private String type;

    }

    @Data
    public static class addForm {
        @ApiModelProperty(value = "用户id",hidden = true)
        private Integer userId;

        @ApiModelProperty(value = "类型",required = true)
        private String type;
        @ApiModelProperty(value = "文件，可多个",required = true)
        private List<MultipartFile> multipartFiles;
        @ApiModelProperty(value = "文件描述，可多个")
        private List<String> fileDescribe;

        public Map<String,String> getErrorInfo(){
            Map<String,String> errorInfos = new HashMap<>();

            if(StringUtils.isEmpty(type)){
                errorInfos.put("type","类型 不能为空");
            }
            if(multipartFiles==null || multipartFiles.isEmpty()){
                errorInfos.put("multipartFiles","文件 不能为空");
            }

            return errorInfos;
        }
    }

    @Data
    public static class updateForm extends UserInfoForm {
        @ApiModelProperty(value = "文件id",required = true)
        private Integer fileId;
        @ApiModelProperty(value = "类型")
        private String type;


        public Map<String,String> getErrorInfo(){
            Map<String,String> errorInfos = new HashMap<>();

            if(fileId==null){
                errorInfos.put("fileId","文件id不能为空");
            }

            return errorInfos;
        }
    }

    @Data
    public class deleteForm extends UserInfoForm{
        @ApiModelProperty(value = "文件id,多个id用逗号分隔",required = true)
        private List<Integer> fileIds;

    }

    @Data
    public static class downloadForm {
        @ApiModelProperty(value = "文件id,单个",required = true)
        private Integer fileId;
    }
}
