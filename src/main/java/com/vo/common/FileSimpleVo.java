package com.vo.common;

import lombok.Data;

@Data
public class FileSimpleVo {
    private String fileId;    // 文件id
    private String previewUrl;  // 文件预览url

    public FileSimpleVo(String fileId, String previewUrl) {
        this.fileId = fileId;
        this.previewUrl = previewUrl;
    }
}
