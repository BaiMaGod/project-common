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
import com.vo.common.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
@Transactional
public class CommentServiceImpl implements CommentService{
    @Autowired
    CommentMapper commentMapper;

    /**
     * 根据id查询 详情信息
     * @param id
     * @return
     */
    @Override
    public Result findById(Integer id) {
        if(id == null){
            return Result.fail("id不能为空", ResultStatus.ERROR_Parameter);
        }

        Comment comment = commentMapper.selectByPrimaryKey(id);

        // 将 基本信息模型 转换为 详情信息模型，包括 所有的回复
//        CommentVo resultVo = convertVo(comment);

        return Result.success(comment);
    }

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

        // 使用PageHelper插件分页
        PageHelper.startPage(form.getPage(),form.getLimit());
        List<Comment> comments = commentMapper.selectByExample(example);

        PageInfo<Comment> pageInfo = new PageInfo<>(comments);
        Page page = form.pageHelperResult(pageInfo);

        // 转化为评论详情信息，包括回复
//        List<CommentVo> commentVoList = convertComment(comments);

        return Result.success(comments,page);
    }

    /**
     * 查询目标id的所有评论列表,包括评论的回复
     * @param targetId
     * @return
     */
    public List<CommentVo> findAllChildrenByTargetId(int targetId) {
        CommentExample example = new CommentExample();
        example.createCriteria().andTargetIdEqualTo(targetId);
        example.setOrderByClause("createTime desc");

        List<Comment> comments = commentMapper.selectByExample(example);

        return convertVoList(comments);
    }

    /**
     * 将基本信息列表 转为 详情信息列表
     * @param modelList
     * @return
     */
    public List<CommentVo> convertVoList(List<Comment> modelList){
        List<CommentVo> voList = new ArrayList<>(modelList.size());

        for (Comment comment : modelList) {
            voList.add( convertVo(comment) );
        }

        return voList;
    }

    /**
     * 将 单个基本信息 转为 单个详情信息
     * @param model
     * @return
     */
    public CommentVo convertVo(Comment model){
        CommentVo convert = (CommentVo) ConvertUtil.convert(model, new CommentVo());
        convert.setChildren( findAllChildrenByTargetId(model.getCommentId()) );

        return convert;
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
            return Result.fail(form.getErrorInfo(), ResultStatus.ERROR_Parameter);
        }

        Comment comment = (Comment) ConvertUtil.convert(form, new Comment());
        comment.setCreateTime(new Date());

        int num = commentMapper.insertSelective(comment);
        if(num>0){
            return Result.success(num);
        }

        return Result.fail(num, ResultStatus.ERROR_Add);
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
            return Result.fail(form.getErrorInfo(), ResultStatus.ERROR_Parameter);
        }

        Comment comment = commentMapper.selectByPrimaryKey(form.getCommentId());
        if(!form.isAdmin() && form.getUserId()!=comment.getUserId()){
            return Result.fail("不能修改别人的评论", ResultStatus.ERROR_Update);
        }

        comment = (Comment) ConvertUtil.convert(form, new Comment());
        comment.setUpdateTime(new Date());
        int num = commentMapper.updateByPrimaryKeySelective(comment);
        if(num>0){
            return Result.success(num);
        }

        return Result.fail(num, ResultStatus.ERROR_Update);
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
