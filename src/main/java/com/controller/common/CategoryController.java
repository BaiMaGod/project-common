package com.controller.common;

import com.form.common.CategoryForm;
import com.result.Result;
import com.service.common.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "分类操作接口",tags = {"各种分类的操作"})
@RestController
@RequestMapping("json/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @ApiOperation(value = "根据id查询 分类项 详情信息",notes = "根据id查询 分类项 详情信息")
    @GetMapping("/findById")
    public Result findById(Integer id){

        return categoryService.findById(id);
    }

    @ApiOperation(value = "查询分类列表",notes = "查询满足条件的分类项列表")
    @GetMapping("/list")
    public Result list(CategoryForm.listForm form){

        return categoryService.list(form);
    }

    @ApiOperation(value = "新增分类项",notes = "新增分类项，需管理员权限")
    @PostMapping("/add")
    public Result add(CategoryForm.addForm form){

        return categoryService.add(form);
    }

    @ApiOperation(value = "修改分类项",notes = "根据分类id，修改分类项，需管理员权限")
    @PutMapping("/update")
    public Result update(CategoryForm.updateForm form){

        return categoryService.update(form);
    }

    @ApiOperation(value = "删除分类项",notes = "根据分类id，删除分类项，可批量删除，多个id逗号分隔，需管理员权限")
    @DeleteMapping("/delete")
    public Result delete(CategoryForm.deleteForm form){

        return categoryService.delete(form);
    }
}
