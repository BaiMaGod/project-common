package com.result;

import lombok.Data;

/**
 * 定义统一返回格式
 * @param <T>
 */
@Data
public class Result<T> {
    private int code;   // 状态码
    private long count = 1; // 内容条数
    private String msg; // 提示信息
    private Page page; // 提示信息
    private T data;     // 数据内容


    public Result(){
        setStatus(ResultStatus.SUCCESS);
    }

    public Result(ResultStatus status){
        this.setStatus(status);
    }


    public Result(T t) {
        setStatus(ResultStatus.SUCCESS);
        this.data = t;
    }
    public Result(int count,T t) {
        setStatus(ResultStatus.SUCCESS);
        this.count = count;
        this.data = t;
    }

    private Result(T t, Page page) {
        setStatus(ResultStatus.SUCCESS);
        this.data = t;
        this.page = page;
    }

    private Result(long count,T t, Page page) {
        setStatus(ResultStatus.SUCCESS);
        this.count = count;
        this.data = t;
        this.page = page;
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

    public static <T>Result<T> success(T t, Page page) {
        Result result = new Result(t, page);
        return result;
    }

    public static <T>Result<T> success(long count,T t, Page page) {
        Result result = new Result(count,t, page);
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
