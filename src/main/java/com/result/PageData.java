package com.result;

import lombok.Data;

/**
 * 定义统一 分页数据返回 格式
 * @param <T>
 */
@Data
public class PageData<T> {
    private Page page;
    private T content;

    public PageData(){}

    public PageData(T content){
        this.content = content;
    }

    public PageData(T content,Page page) {
        this.page = page;
        this.content = content;
    }
}
