package com.form;

import com.github.pagehelper.PageInfo;
import com.result.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PageForm {
    @ApiModelProperty(value = "第几页")
    protected int page;
    @ApiModelProperty(value = "每页条数")
    protected int limit;

    public <T> Page pageHelperResult(PageInfo<T> pageInfo){
        Page page = new Page();

        page.setLimit(this.limit);
        page.setPage(this.page);
        page.setTotalRows(pageInfo.getTotal());
        page.setTotalPages(pageInfo.getNavigateLastPage());

        return page;
    }
}
