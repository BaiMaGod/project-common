package com.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.servlet.http.HttpSession;

@Data
public class UserInfoForm {
    @ApiModelProperty(value = "用户id",hidden = true)
    protected Integer userId;
    @ApiModelProperty(value = "是否是管理员",hidden = true)
    protected boolean admin;


    public void setUserInfo(HttpSession session){
        /*UserExt loginUser = (UserExt) session.getAttribute("loginUser");

        if(loginUser != null && loginUser.getUserId()!=null){
            setUserId(loginUser.getUserId());
        }
        if(loginUser != null && (loginUser.getRole()!=null && loginUser.getRole().getLevel()>=100) ){
            setAdmin(true);
        }*/
    }
}
