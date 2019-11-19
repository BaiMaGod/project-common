package com.result;

/**
 * 返回状态码枚举类
 */
public enum ResultStatus {
    SUCCESS(0,"请求成功"),
    ERROR_SYS(1,"系统错误"),
    ERROR_UPDATE(2,"修改错误"),
    ERROR_Category_Add(101,"分类添加失败"),
    ERROR_Category_Update(102,"分类修改失败"),
    ERROR_File_Add(111,"文件添加失败"),
    ERROR_File_No_Exist(112,"文件不存在"),
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
