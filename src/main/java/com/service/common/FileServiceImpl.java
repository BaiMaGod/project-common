package com.service.common;

import com.config.ServerConfig;
import com.form.common.FileForm;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.common.FileMapper;
import com.model.common.File;
import com.model.common.FileExample;
import com.vo.common.FileSimpleVo;
import com.result.Page;
import com.result.Result;
import com.result.ResultStatus;
import com.utils.CommonUtil;
import com.utils.ConvertUtil;
import com.utils.MultipartFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Service
@Transactional
public class FileServiceImpl implements FileService{
    @Autowired
    FileMapper fileMapper;
    @Autowired
    ServerConfig serverConfig;


    @Override
    public Result list(FileForm.listForm form) {
        FileExample example = new FileExample();
        FileExample.Criteria criteria = example.createCriteria();

        if(form.getFileId()!=null){
            criteria.andFileIdEqualTo(form.getFileId());
        }
        if(form.getUserId()!=null){
            criteria.andUserIdEqualTo(form.getUserId());
        }
        if(!StringUtils.isEmpty(form.getFileRealName())){
            criteria.andFileRealNameLike("%"+form.getFileRealName()+"%");
        }
        if(!StringUtils.isEmpty(form.getFileSuffix())){
            criteria.andFileSuffixEqualTo(form.getFileSuffix());
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
        List<File> files = fileMapper.selectByExample(example);

        PageInfo<File> pageInfo = new PageInfo<>(files);
        Page page = form.pageHelperResult(pageInfo);

        for (File file : files) {
            file.setFileUrl(serverConfig.getUploadFileUrl() + file.getFileUrl());
        }

        return Result.success(files,page);
    }

    @Override
    @Transactional
    public Result add(FileForm.addForm form) {
        // 首先检查用户注册信息的合法性，如有不合法输入，返回错误信息
        if(!form.getErrorInfo().isEmpty()){
            return Result.fail(form.getErrorInfo(), ResultStatus.ERROR_Parameter);
        }

        // 将上传的文件url返回给前端
        List<FileSimpleVo> urls = new ArrayList<>();

        MultipartFile multipartFile;
        for (int i = 0; i < form.getMultipartFiles().size(); i++) {
            multipartFile = form.getMultipartFiles().get(i);

            File file = new File();
            // 设置文件上传时的真实名
            file.setFileRealName(MultipartFileUtil.getFileRealName(multipartFile));
            // 设置文件后缀
            file.setFileSuffix(MultipartFileUtil.getFileSuffix(multipartFile));
            // 生成文件在服务器中的存储名，目的为防止重名
            String longId = CommonUtil.getLongId();

            file.setFileShortName(longId);
            file.setFileFullName(longId+"."+file.getFileSuffix());
            file.setUserId(form.getUserId());
            file.setType(form.getType());
            file.setCreateTime(new Date());
            if(form.getFileDescribe() != null && form.getFileDescribe().size() > i){
                file.setIntroduce(form.getFileDescribe().get(i));
            }

            file.setFileUrl(MultipartFileUtil.fileSave(multipartFile,form.getType(),longId));

            fileMapper.insertSelective(file);

            urls.add( new FileSimpleVo(file.getFileFullName(),serverConfig.getUploadFileUrl() + file.getFileUrl()) );
        }

        return Result.success(urls);
    }

    @Override
    public Result update(FileForm.updateForm form) {
        // 首先检查用户注册信息的合法性，如有不合法输入，返回错误信息
        if(!form.getErrorInfo().isEmpty()){
            return Result.fail(form.getErrorInfo(), ResultStatus.ERROR_Parameter);
        }

        File file = fileMapper.selectByPrimaryKey(form.getFileId());
        if(!form.isAdmin() && form.getUserId()!=file.getUserId()){
            return Result.fail("不能修改别人的文件", ResultStatus.ERROR_Update);
        }


        file = (File) ConvertUtil.convert(form, new File());
        file.setUpdateTime(new Date());

        int num = fileMapper.updateByPrimaryKeySelective(file);
        if(num>0){
            return Result.success(num);
        }

        return Result.fail(num, ResultStatus.ERROR_Update);
    }

    @Override
    public Result download(FileForm.downloadForm form, HttpServletRequest request, HttpServletResponse response) {
        File file = fileMapper.selectByPrimaryKey(form.getFileId());

        if(file==null || StringUtils.isEmpty(file.getFileUrl())){
            return Result.fail("文件不存在",ResultStatus.ERROR_No_Exist);
        }


        try {
            MultipartFileUtil.downloadFile(file.getFileUrl(),file.getFileRealName()+"."+file.getFileSuffix(),request,response);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Result.fail(0,ResultStatus.ERROR_File_Download);
        }

        return Result.success(1);
    }

    @Override
    public Result delete(FileForm.deleteForm form) {
        if(form.getFileIds()==null){
            return Result.success(0);
        }
        int count = 0;
        File file;
        Map<String,Object> errorInfos = new HashMap<>();
        for (Integer id : form.getFileIds()) {
            file = fileMapper.selectByPrimaryKey(id);

            if(!form.isAdmin() && form.getUserId() != file.getUserId()){
                errorInfos.put(id+"","不能删除别人的评论");
            }else{
                // 先删除数据库数据
                int num = fileMapper.deleteByPrimaryKey(id);
                // 再删除磁盘文件
                if(file!=null && num > 0){
                    MultipartFileUtil.deleteFile(file.getFileUrl());
                }

                count += num;
            }
        }

        errorInfos.put("delete",count);
        return Result.success(errorInfos);
    }
}
