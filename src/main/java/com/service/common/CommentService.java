package com.service.common;

import com.form.common.CommentForm;
import com.result.Result;

public interface CommentService {
    Result list(CommentForm.listForm form);

    Result add(CommentForm.addForm form);

    Result update(CommentForm.updateForm form);

    Result delete(CommentForm.deleteForm form);
}
