package com.service.common;

import com.form.common.CategoryForm;
import com.result.Result;

public interface CategoryService {
    Result list(CategoryForm.listForm form);

    Result add(CategoryForm.addForm form);

    Result update(CategoryForm.updateForm form);

    Result delete(CategoryForm.deleteForm form);
}
