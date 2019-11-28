package com.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PageForm {
    @ApiModelProperty(value = "第几页")
    protected int page;
    @ApiModelProperty(value = "每页条数")
    protected int limit;
}
