package com.utils;


import com.alibaba.fastjson.JSONObject;
import com.result.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HttpUtil {

    public static void returnJson(HttpServletResponse response, Result result) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        PrintWriter writer = null;
        try {
            writer = response.getWriter();

            String jsonObject = JSONObject.toJSONString(result);
            writer.print(jsonObject);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (writer != null)
                writer.close();
        }
    }
}
