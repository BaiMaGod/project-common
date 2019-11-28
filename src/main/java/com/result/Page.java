package com.result;

import lombok.Data;

@Data
public class Page {
    //	当前第几页
    private int page;
    //	每页条数
    private int limit;
    // 总条数
    private long totalRows;
    // 总页数
    private int totalPages;
    // 起始条序号
    private int startRow;
    // 结束条序号
    private int endRow;


    public Page() {}
    public Page(int curPage) {
        super();
        this.page = curPage >= 0 ? curPage : 1;
    }
    public Page(int curPage,int pageRows) {
        super();
        this.page = curPage >= 0 ? curPage : 1;
        this.limit = pageRows > 0 ? pageRows : 10;
    }


    public int getStartRow() {
        return this.limit*(this.page-1);
    }

    public int getEndRow() {
        return this.startRow+this.limit;
    }

    public int getTotalPages() {
        return (int) (this.totalRows/(this.limit+1)+1);
    }


}