package com.vo.common;

import lombok.Data;

@Data
public class FileSimpleVo {
    private String fileFullName;    // 文件在服务器上存储的全称
    private String previewUrl;  // 文件预览url

    public FileSimpleVo(String fileFullName, String previewUrl) {
        this.fileFullName = fileFullName;
        this.previewUrl = previewUrl;
    }
}
