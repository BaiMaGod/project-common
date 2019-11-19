package com.form.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileForm {

    @Data
    public static class listForm {
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

        @ApiModelProperty(value = "第几页")
        private int page;
        @ApiModelProperty(value = "每页条数")
        private int limit;
    }

    @Data
    public static class addForm {
        @ApiModelProperty(value = "用户id")
        private Integer userId;
        @ApiModelProperty(value = "类型",required = true)
        private String type;
        @ApiModelProperty(value = "文件，可多个",required = true)
        private List<MultipartFile> multipartFiles;
        @ApiModelProperty(value = "文件描述，可多个")
        private List<String> fileDescribe;

        public Map<String,String> getErrorInfo(){
            Map<String,String> errorInfos = new HashMap<>();

            if(userId==null){
                errorInfos.put("user","登录用户才能上传文件");
            }
            if(StringUtils.isEmpty(type)){
                errorInfos.put("type","类型 不能为空");
            }
            if(multipartFiles==null || multipartFiles.isEmpty()){
                errorInfos.put("introduce","文件 不能为空");
            }

            return errorInfos;
        }
    }

    @Data
    public class deleteForm {
        @ApiModelProperty(value = "文件id,多个id用逗号分隔",required = true)
        private List<Integer> fileIds;
    }

    @Data
    public static class downloadForm {
        @ApiModelProperty(value = "文件id,单个",required = true)
        private Integer fileId;
    }
}
