package com.config;

import com.utils.MultipartFileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 自定义web配置
 */
@Component
public class CustomWebConfiguration implements WebMvcConfigurer {
    @Value("${uploadFile.path}")
    private String filePath;

    @Value("${uploadFile.path}")
    public void setUploadFilePath(String uploadFilePath) {
        MultipartFileUtil.uploadFilePath = uploadFilePath;
    }
 
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
 
        // 注意如果filePath是写死在这里，一定不要忘记尾部的/或者\\，这样才能读取其目录下的文件
        registry.addResourceHandler("/**").addResourceLocations(
                "classpath:/META-INF/resources/",
                "classpath:/resources/",
                "classpath:/static/",
                "classpath:/public/",
                "file:/" + filePath,
                "classpath:/webapp/");
    }
}