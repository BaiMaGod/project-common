package com.result;

/**
 * 返回状态码枚举类
 */
public enum ResultStatus {
    SUCCESS(0,"请求成功"),
    ERROR_SYS(1,"系统错误"),
    ERROR_Parameter(2,"参数错误"),
    ERROR_No_Login(3,"未登录"),
    ERROR_No_Permission(4,"无权限"),
    ERROR_Add(5,"添加错误"),
    ERROR_Delete(6,"删除错误"),
    ERROR_Update(7,"修改错误"),
    ERROR_Select(8,"查询错误"),
    ERROR_No_Exist(9,"不存在"),
    ERROR_File_Download(113,"文件下载出错");

    private int code;
    private String msg;

    ResultStatus(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode(){
        return this.code;
    }

    public String getMsg(){
        return this.msg;
    }
}
