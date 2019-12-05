package com.result;

import lombok.Data;

/**
 * 定义统一返回格式
 * @param <T>
 */
@Data
public class Result<T> {
    private int code;   // 状态码
    private long count; // 内容条数
    private String msg; // 提示信息
    private Page page; // 提示信息
    private T data;     // 数据内容


    public Result(){
        setStatus(ResultStatus.SUCCESS);
    }

    public Result(ResultStatus status){
        this.setStatus(status);
    }

    public Result(int code,String msg){
        this.code = code;
        this.msg = msg;
    }


    public Result(T t) {
        setStatus(ResultStatus.SUCCESS);
        this.data = t;
    }
    public Result(T t,int count) {
        setStatus(ResultStatus.SUCCESS);
        this.count = count;
        this.data = t;
    }

    private Result(T t, Page page) {
        setStatus(ResultStatus.SUCCESS);
        this.data = t;
        this.page = page;
        this.count = page.getTotalRows();
    }


    public Result<T> setStatus(ResultStatus status) {
        this.code = status.getCode();
        this.msg = status.getMsg();

        return this;
    }

    public static <T>Result<T> success(T t) {
        Result result = new Result(t);
        return result;
    }

    public static <T>Result<T> success(T t,int count) {
        Result result = new Result(t,count);
        return result;
    }

    public static <T>Result<T> success(T t, Page page) {
        Result result = new Result(t, page);
        return result;
    }


    public static <T>Result<T> fail(ResultStatus status) {
        Result result = new Result(status);
        return result;
    }

    public static <T>Result<T> fail(int code,String msg) {
        Result result = new Result(code,msg);
        return result;
    }

    public static <T>Result<T> fail(T t, ResultStatus status) {
        Result result = new Result(t);
        result.setStatus(status);
        return result;
    }




    public Result<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public Result<T> setCount(long count) {
        this.count = count;
        return this;
    }

    public Result<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Result<T> setPage(Page page) {
        this.page = page;
        return this;
    }

    public Result<T> setData(T data) {
        this.data = data;

        return this;
    }
}
