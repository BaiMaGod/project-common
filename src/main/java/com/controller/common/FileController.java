package com.controller.common;

import com.form.common.FileForm;
import com.result.Result;
import com.result.ResultStatus;
import com.service.common.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Api(value = "文件操作接口",tags = {"各种文件的操作"})
@RestController
@RequestMapping("json/file")
public class FileController {
    @Autowired
    FileService fileService;

    @ApiOperation(value = "查询文件列表",notes = "查询满足条件的文件列表")
    @GetMapping("/list")
    public Result list(FileForm.listForm form){


        return fileService.list(form);
    }

    @ApiOperation(value = "新增多个文件",notes = "新增文件")
    @PostMapping("/add")
    public Result add(FileForm.addForm form, HttpSession session){
       /* UserExt loginUser = (UserExt) session.getAttribute("loginUser");

        if(loginUser != null && loginUser.getUserId()!=null){
            form.setUserId(loginUser.getUserId());
        }*/

        return fileService.add(form);
    }

    @ApiOperation(value = "新增单个文件",notes = "新增单个文件，上传成功返回可预览的url")
    @PostMapping("/addOne")
    public Result addOne(MultipartFile multipartFile){

        return fileService.addOne(multipartFile);
    }

    @ApiOperation(value = "新增单个文件，有分类",notes = "新增单个文件，有分类")
    @PostMapping("/addOneType")
    public Result addOneType(FileForm.addOneTypeForm form){

        return fileService.addOneType(form);
    }


    @ApiOperation(value = "修改文件信息",notes = "根据id，修改，非管理员只能修改自己上传的文件的部分信息")
    @PutMapping("/update")
    public Result update(FileForm.updateForm form, HttpSession session){
        form.setUserInfo(session);

        return fileService.update(form);
    }

    @ApiOperation(value = "下载文件",notes = "下载文件")
    @GetMapping("/download")
    public Result download(FileForm.downloadForm form,HttpServletRequest request, HttpServletResponse response){

        return fileService.download(form,request,response);
    }

    @ApiOperation(value = "删除文件",notes = "根据分类id，删除文件，可批量删除，多个id逗号分隔，非管理员只能删除自己上传的文件")
    @DeleteMapping("/delete")
    public Result delete(FileForm.deleteForm form, HttpSession session){
        form.setUserInfo(session);

        return fileService.delete(form);
    }
}
