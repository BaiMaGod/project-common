package com.utils;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * 文件上传工具类
 */
public class MultipartFileUtil {

    /**
     * 文件在服务器中存储的目录
     */
    public static String uploadFilePath = "D:\\E\\ideaProject\\project-common\\src\\main\\resources\\static";
//    public static String uploadFilePath = "/root/uploadFile/project-common";


    /**
     * 获取上传文件的真实名，去后缀
     */
    public static String getFileRealName(MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        String fileRealName = originalFilename.substring(0,originalFilename.lastIndexOf("."));

        return fileRealName;
    }


    /**
     * 获取上传文件的后缀
     */
    public static String getFileSuffix(MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf(".")+1);

        return fileSuffix;
    }



    /**
     * 将文件存入磁盘中,路径在本项项目WebContent目录下
     */
    public static String fileSave(MultipartFile file, String path){

        return fileSave(file,path,CommonUtil.getUUID());
    }

    /**
     * 将文件存入磁盘中,路径在本项项目WebContent目录下,指定存储文件名
     */
    public static String fileSave(MultipartFile file, String path,String memoryName){
        String realPath = uploadFilePath + "\\" + path;
        File targetDir = new File(realPath);
        if(!targetDir.exists()){
            targetDir.mkdirs();
        }

//        获得原文件名
        String fileName = file.getOriginalFilename();
//        获得原文件后缀名
        String fileSuffix = fileName.substring(fileName.lastIndexOf(".")+1);

//        防止文件重名,随机生成文件的存储名
//        String memoryName = MyUtil.getLongId()+"."+fileSuffix;


        memoryName = memoryName + "."+fileSuffix;

//        生成文件src路径
        String src = path +"\\"+ memoryName;
        File targetFile = new File(realPath+"\\"+memoryName);
//         保存到磁盘
        try {
            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return src;
    }


    public static String downloadFile(String path,String fileName,HttpServletRequest request,
                               HttpServletResponse response) throws UnsupportedEncodingException {
        // 文件路径
        String realPath = uploadFilePath + "\\" +path;
        File file = new File(realPath);

        // 如果文件存在，则进行下载
        if (file.exists()) {

            if(StringUtils.isEmpty(fileName)) {
                fileName = file.getName(); //下载的文件名
            }

            // 配置文件下载
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            // 下载文件能正常显示中文
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            // 实现文件下载
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                System.out.println("文件["+realPath+"]下载成功!");
            }
            catch (Exception e) {
                System.out.println("文件["+realPath+"]下载失败!");
            }
            finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return null;
    }

    /**
     * 删除本项目下的文件
     * @param path 相对路径
     */
    public static void deleteFile(String path){
        String absolutePath = uploadFilePath + "\\" + path;
        File file = new File(absolutePath);
        file.delete();
    }
}
