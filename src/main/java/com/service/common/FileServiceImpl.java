package com.service.common;

import com.form.common.FileForm;
import com.github.pagehelper.PageHelper;
import com.mapper.common.FileMapper;
import com.model.common.File;
import com.model.common.FileExample;
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
import java.util.Date;
import java.util.List;

@Service
public class FileServiceImpl implements FileService{
    @Autowired
    FileMapper fileMapper;


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

        int count = fileMapper.countByExample(example);
        // 使用PageHelper插件分页
        PageHelper.startPage(form.getPage(),form.getLimit());
        List<File> files = fileMapper.selectByExample(example);

        Page page = (Page) ConvertUtil.convert(form,new Page());
        page.setTotalRows(count);

        for (File file : files) {
            file.setFileUrl(MultipartFileUtil.uploadFilePath +"\\"+file.getFileUrl());
        }

        return Result.success(files,page).setCount(count);
    }

    @Override
    @Transactional
    public Result add(FileForm.addForm form) {
        // 首先检查用户注册信息的合法性，如有不合法输入，返回错误信息
        if(!form.getErrorInfo().isEmpty()){
            return Result.fail(form.getErrorInfo(), ResultStatus.ERROR_File_Add);
        }

        int num = 0;
        for (MultipartFile multipartFile : form.getMultipartFiles()) {
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

            file.setFileUrl(MultipartFileUtil.fileSave(multipartFile,form.getType(),longId));

            num += fileMapper.insertSelective(file);
        }

        return Result.success(num);
    }

    @Override
    public Result download(FileForm.downloadForm form, HttpServletRequest request, HttpServletResponse response) {
        File file = fileMapper.selectByPrimaryKey(form.getFileId());

        if(file==null || StringUtils.isEmpty(file.getFileUrl())){
            return Result.fail(0,ResultStatus.ERROR_File_No_Exist);
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
        for (Integer fileId : form.getFileIds()) {

            File file = fileMapper.selectByPrimaryKey(fileId);
            // 先删除数据库数据
            int num = fileMapper.deleteByPrimaryKey(fileId);
            // 再删除磁盘文件
            if(file!=null && num > 0){
                MultipartFileUtil.deleteFile(file.getFileUrl());
            }

            count += num;
        }

        return Result.success(count);
    }
}