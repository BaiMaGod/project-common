package com.controller;

import com.config.ServerConfig;
import com.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    ServerConfig serverConfig;

    @GetMapping("/hello")
    @ResponseBody
    public Result test(String name){

        return Result.success("hello ["+name+"]") ;
    }

    @GetMapping("/file")
    public String file(String name, Model model){
        model.addAttribute("name",name);
        addServerInfo(model);

        return "fileTest";
    }

    private void addServerInfo(Model model) {
        model.addAttribute("ip",serverConfig.getIp());
        model.addAttribute("port",serverConfig.getServerPort());
        model.addAttribute("url",serverConfig.getUrl());
    }
}
