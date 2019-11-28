package com.service.common;

import com.form.common.CommentForm;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.common.CommentMapper;
import com.model.common.Comment;
import com.model.common.CommentExample;
import com.result.Page;
import com.result.Result;
import com.result.ResultStatus;
import com.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    CommentMapper commentMapper;

    /**
     * 查询满足条件的评论列表
     * @param form
     * @return
     */
    @Override
    public Result list(CommentForm.listForm form) {
        CommentExample example = new CommentExample();
        CommentExample.Criteria criteria = example.createCriteria();


        if(form.getCommentId()!=null){
            criteria.andCommentIdEqualTo(form.getCommentId());
        }
        if(form.getUserId()!=null){
            criteria.andUserIdEqualTo(form.getUserId());
        }
        if(form.getTargetId()!=null){
            criteria.andTargetIdEqualTo(form.getTargetId());
        }
        if(!StringUtils.isEmpty(form.getType())){
            criteria.andTypeEqualTo(form.getType());
        }
        if(!StringUtils.isEmpty(form.getContext())){
            criteria.andContextLike("%"+form.getContext()+"%");
        }

        if(form.getStartScore()!=null && form.getEndScore()==null){
            criteria.andScoreGreaterThan(form.getStartScore());
        }else if(form.getStartScore()==null && form.getEndScore()!=null){
            criteria.andScoreLessThan(form.getEndScore());
        }else if(form.getStartScore()!=null && form.getEndScore()!=null){
            criteria.andScoreBetween(form.getStartScore(),form.getEndScore());
        }

        if(form.getStartCreateTime()!=null && form.getEndCreateTime()==null){
            criteria.andCreateTimeGreaterThan(form.getStartCreateTime());
        }else if(form.getStartCreateTime()==null && form.getEndCreateTime()!=null){
            criteria.andCreateTimeLessThan(form.getEndCreateTime());
        }else if(form.getStartCreateTime()!=null && form.getEndCreateTime()!=null){
            criteria.andCreateTimeBetween(form.getStartCreateTime(),form.getEndCreateTime());
        }

        if(form.getStartUpdateTime()!=null && form.getEndUpdateTime()==null){
            criteria.andUpdateTimeGreaterThan(form.getStartUpdateTime());
        }else if(form.getStartUpdateTime()==null && form.getEndUpdateTime()!=null){
            criteria.andUpdateTimeLessThan(form.getEndUpdateTime());
        }else if(form.getStartUpdateTime()!=null && form.getEndUpdateTime()!=null){
            criteria.andUpdateTimeBetween(form.getStartUpdateTime(),form.getEndUpdateTime());
        }

        // 排序
        if(!StringUtils.isEmpty(form.getOrderByClause())){
            example.setOrderByClause(form.getOrderByClause());
        }

        int count = commentMapper.countByExample(example);
        // 使用PageHelper插件分页
        PageHelper.startPage(form.getPage(),form.getLimit());
        List<Comment> comments = commentMapper.selectByExample(example);

        Page page = (Page) ConvertUtil.convert(form,new Page());
        page.setTotalRows(count);

        return Result.success(comments,page).setCount(count);
    }

    /**
     * 新增评论
     * @param form
     * @return
     */
    @Override
    public Result add(CommentForm.addForm form) {
        // 首先检查用户注册信息的合法性，如有不合法输入，返回错误信息
        if(!form.getErrorInfo().isEmpty()){
            return Result.fail(form.getErrorInfo(), ResultStatus.ERROR_Comment_Add);
        }

        Comment comment = (Comment) ConvertUtil.convert(form, new Comment());
        comment.setCreateTime(new Date());

        int num = commentMapper.insertSelective(comment);
        if(num>0){
            return Result.success(num);
        }

        return Result.fail(num, ResultStatus.ERROR_Comment_Add);
    }

    /**
     * 根据id，修改评论，非管理员只能修改自己的
     * @param form
     * @return
     */
    @Override
    public Result update(CommentForm.updateForm form) {
        // 首先检查用户注册信息的合法性，如有不合法输入，返回错误信息
        if(!form.getErrorInfo().isEmpty()){
            return Result.fail(form.getErrorInfo(), ResultStatus.ERROR_Comment_Add);
        }

        Comment comment = commentMapper.selectByPrimaryKey(form.getCommentId());
        if(!form.isAdmin() && form.getUserId()!=comment.getUserId()){
            return Result.fail("不能修改别人的评论", ResultStatus.ERROR_Comment_Update);
        }

        comment = (Comment) ConvertUtil.convert(form, new Comment());
        comment.setUpdateTime(new Date());
        int num = commentMapper.updateByPrimaryKeySelective(comment);
        if(num>0){
            return Result.success(num);
        }

        return Result.fail(num, ResultStatus.ERROR_Comment_Update);
    }

    /**
     * 根据id，删除评论，可批量删除，多个id逗号分隔，非管理员只能删除自己的
     * @param form
     * @return
     */
    @Override
    @Transactional
    public Result delete(CommentForm.deleteForm form) {
        if(form.getIds()==null){
            return Result.success(0);
        }
        int count = 0;
        Comment comment;
        Map<String,Object> errorInfos = new HashMap<>();
        for (Integer id : form.getIds()) {
            comment = commentMapper.selectByPrimaryKey(id);
            if(!form.isAdmin() && form.getUserId()!=comment.getUserId()){
                errorInfos.put(id+"","不能删除别人的评论");
            }else{
                int num = commentMapper.deleteByPrimaryKey(id);
                count += num;
            }
        }

        errorInfos.put("delete",count);
        return Result.success(errorInfos);
    }
}
