package com.service.common;

import com.form.common.FileForm;
import com.result.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FileService {
    Result list(FileForm.listForm form);

    Result add(FileForm.addForm form);

    Result addOne(MultipartFile multipartFile);

    Result addOneType(FileForm.addOneTypeForm form);

    Result download(FileForm.downloadForm form, HttpServletRequest request, HttpServletResponse response);

    Result delete(FileForm.deleteForm form);

    Result update(FileForm.updateForm form);
}
