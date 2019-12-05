package com.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 服务器配置信息
 */
@Component
public class ServerConfig  implements ApplicationListener<WebServerInitializedEvent> {

    private int serverPort;
    @Value("${uploadFile.beforeUrl}")
    String beforeUrl;
    @Value("${uploadFile.ip}")
    String uploadFileIp;
    @Value("${uploadFile.port}")
    String uploadFilePort;


    public int getServerPort() {

        return this.serverPort;
    }


    public String getIp() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return address.getHostAddress();
    }

    public String getUrl() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return "http://"+ address.getHostAddress() +":"+ this.serverPort;
    }

    public String getUploadFileUrl() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        if(StringUtils.isEmpty(uploadFilePort)){
            uploadFilePort = this.serverPort+"";
        }
        if(StringUtils.isEmpty(uploadFileIp)){
            uploadFileIp = address.getHostAddress();
        }

        return "http://"+ uploadFileIp +":"+ uploadFilePort + beforeUrl;
    }

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.serverPort = event.getWebServer().getPort();
    }
 
}