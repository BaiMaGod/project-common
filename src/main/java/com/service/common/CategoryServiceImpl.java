package com.service.common;

import com.form.common.CategoryForm;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.common.CategoryMapper;
import com.model.common.Category;
import com.model.common.CategoryExample;
import com.result.Page;
import com.result.Result;
import com.result.ResultStatus;
import com.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryMapper categoryMapper;

    /**
     * 查询满足条件的分类项列表
     * @param form
     * @return
     */
    @Override
    public Result list(CategoryForm.listForm form) {
        CategoryExample example = new CategoryExample();
        CategoryExample.Criteria criteria = example.createCriteria();

        if(form.getCategoryId()!=null) {
            criteria.andCategoryIdEqualTo(form.getCategoryId());
        }
        if(form.getParentId()!=null){
            criteria.andParentIdEqualTo(form.getParentId());
        }
        if(!StringUtils.isEmpty(form.getType())){
            criteria.andTypeEqualTo(form.getType());
        }

        // 排序
        if(!StringUtils.isEmpty(form.getOrderByClause())){
            example.setOrderByClause(form.getOrderByClause());
        }

        // 使用PageHelper插件分页
        PageHelper.startPage(form.getPage(),form.getLimit());
        List<Category> categories = categoryMapper.selectByExample(example);

        PageInfo<Category> pageInfo = new PageInfo<>(categories);
        Page page = form.pageHelperResult(pageInfo);

        return Result.success(page.getTotalRows(),categories,page);
    }

    /**
     * 新增分类项
     * @param form
     * @return
     */
    @Override
    public Result add(CategoryForm.addForm form) {
        // 首先检查用户注册信息的合法性，如有不合法输入，返回错误信息
        if(!form.getErrorInfo().isEmpty()){
            return Result.fail(form.getErrorInfo(), ResultStatus.ERROR_Category_Add);
        }

        Category category = (Category) ConvertUtil.convert(form, new Category());
        category.setCreateTime(new Date());

        int num = categoryMapper.insertSelective(category);
        if(num>0){
            return Result.success(num);
        }

        return Result.fail(num, ResultStatus.ERROR_Category_Add);
    }

    /**
     * 根据分类id，修改分类项
     * @param form
     * @return
     */
    @Override
    public Result update(CategoryForm.updateForm form) {
        if(form.getCategoryId()==null){
            return Result.fail("分类id不能为空", ResultStatus.ERROR_Category_Update);
        }
        Category category = (Category) ConvertUtil.convert(form, new Category());
        category.setUpdateTime(new Date());

        int num = categoryMapper.updateByPrimaryKeySelective(category);
        if(num>0){
            return Result.success(num);
        }

        return Result.fail(num, ResultStatus.ERROR_Category_Update);
    }

    /**
     * 根据分类id，删除分类项，可批量删除，多个id逗号分隔
     * @param form
     * @return
     */
    @Override
    @Transactional
    public Result delete(CategoryForm.deleteForm form) {
        if(form.getCategoryIds()==null){
            return Result.success(0);
        }
        int count = 0;
        for (Integer categoryId : form.getCategoryIds()) {
            int num = categoryMapper.deleteByPrimaryKey(categoryId);
            count += num;
        }

        return Result.success(count);
    }
}
