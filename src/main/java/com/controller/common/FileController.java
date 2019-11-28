package com.controller.common;

import com.form.common.FileForm;
import com.result.Result;
import com.result.ResultStatus;
import com.service.common.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "新增文件",notes = "新增文件,需登录")
    @PostMapping("/add")
    public Result add(FileForm.addForm form, HttpSession session){
       /* UserExt loginUser = (UserExt) session.getAttribute("loginUser");

        if(loginUser != null && loginUser.getUserId()!=null){
            form.setUserId(loginUser.getUserId());
        }else {
            return Result.fail(0, ResultStatus.ERROR_No_Login);
        }*/

        return fileService.add(form);
    }

    @ApiOperation(value = "修改文件信息",notes = "根据id，修改，非管理员只能修改自己上传的文件的部分信息")
    @PutMapping("/update")
    public Result update(FileForm.updateForm form, HttpSession session){
       /* UserExt loginUser = (UserExt) session.getAttribute("loginUser");

        if(loginUser != null && loginUser.getUserId()!=null){
            form.setUserId(loginUser.getUserId());
        }else {
            return Result.fail(0, ResultStatus.ERROR_No_Login);
        }
        if(loginUser != null && (loginUser.getRole()!=null && loginUser.getRole().getLevel()>=100) ){
            form.setAdmin(true);
        }

        return Result.fail(0, ResultStatus.ERROR_No_Login);
*/

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
       /* UserExt loginUser = (UserExt) session.getAttribute("loginUser");

        if(loginUser != null && loginUser.getUserId()!=null){
            form.setUserId(loginUser.getUserId());
        }else {
            return Result.fail(0, ResultStatus.ERROR_No_Login);
        }
        if(loginUser != null && (loginUser.getRole()!=null && loginUser.getRole().getLevel()>=100) ){
            form.setAdmin(true);
        }

        return Result.fail(0, ResultStatus.ERROR_No_Login);
*/
        return fileService.delete(form);
    }
}
