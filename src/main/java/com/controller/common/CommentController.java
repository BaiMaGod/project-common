package com.controller.common;

import com.form.common.CommentForm;
import com.result.Result;
import com.service.common.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Api(value = "评论|回复|动态|推文|弹幕 操作接口",tags = {"评论|回复|动态|推文|弹幕 操作接口"})
@RestController
@RequestMapping("json/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @ApiOperation(value = "查询列表",notes = "查询满足条件的列表")
    @GetMapping("/list")
    public Result list(CommentForm.listForm form){

        return commentService.list(form);
    }

    @ApiOperation(value = "新增",notes = "新增,需登录")
    @PostMapping("/add")
    public Result add(CommentForm.addForm form, HttpSession session){
       /* UserExt loginUser = (UserExt) session.getAttribute("loginUser");

        if(loginUser != null && loginUser.getUserId()!=null){
            form.setUserId(loginUser.getUserId());
        }*/

        return commentService.add(form);
    }

    @ApiOperation(value = "修改",notes = "根据id修改,需登录，非管理员只能删除自己的")
    @PutMapping("/update")
    public Result update(CommentForm.updateForm form, HttpSession session){
        form.setUserInfo(session);

        return commentService.update(form);
    }

    @ApiOperation(value = "删除",notes = "根据分类id，可批量删除，多个id逗号分隔，非管理员只能删除自己的")
    @DeleteMapping("/delete")
    public Result delete(CommentForm.deleteForm form, HttpSession session){
        form.setUserInfo(session);

        return commentService.delete(form);
    }
}
