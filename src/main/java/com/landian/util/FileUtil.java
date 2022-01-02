package com.landian.util;

import com.landian.service.FileService;
import com.landian.service.impl.FileServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class FileUtil {
    private StringBuffer fileStr = new StringBuffer();

    /**
     * 查询目录下的所有文件名
     * @param path
     * @return
     */
    public File[] selectAllFile(String path){
        File dirFile = new File(path);
        if (dirFile.exists()) {
            File[] files = dirFile.listFiles();
            return files;
        }
        return null;
    }

    /**
     * 获取当前上传文件时间
     * @return
     */
    public String getCurrentDate(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(date);
        return dateNowStr;
    }

    /**
     * 通过路径删除一个文件
     * @param path
     */
    public void fileDeleteByPath(String path){
        File file = new File(path);
        file.delete();
    }

    /**
     * 实现文件上传
     * @param userName 用户名称
     * @param itemList 文件表单列表
     * @return null\"error"
     */
    public String fileUpload(String userName, List<FileItem> itemList) {
        String msg_s = "error";
        int size = 0;
        System.out.println(userName);
        try {
            Iterator<FileItem> it = itemList.iterator();//迭代器
            while (it.hasNext()) {
                FileItem fileItem = it.next();//依次解析每一个FileItem实例，即表单字段
                //文表件单字段
                if(!fileItem.isFormField()){//判断是否是文件
                    String fileUpName = fileItem.getName();//用户上传的文件名
                    FileUtil fileUtil = new FileUtil();
                    String classPath = this.getClass().getResource("/").getPath().replaceFirst("/","");
                    String uploadFileRoot = classPath.replaceAll("target/AMS/WEB-INF/classes/","")+"src/main/webapp/uploadFile/";
                    String path = uploadFileRoot + userName ;
                    System.out.println("保存到目录："+path);
                    File pathfile = new File(path);
                    pathfile.mkdir();//创建文件夹
                    File[] files = fileUtil.selectAllFile(path+ "/");
                    if (files != null) {
                        for (File fileChildDir : files) {
                            if (fileChildDir.isFile() && fileChildDir.getName().equals(fileUpName)) {//判断是否已经有同名文件
                                return null;
                            }
                        }
                    }
                    System.out.println("文件名为："+fileUpName);
                    File file = new File(uploadFileRoot + userName + "/" + fileUpName);
                    //写入保存到目标文件
                    fileItem.write(file);
                    //记录写入数据库
                    FileService fileUploadService = new FileServiceImpl();
                    String cdate = fileUtil.getCurrentDate();
                    fileUploadService.fileupload(new com.landian.domain.File(userName,path+ "/"+fileUpName,cdate,fileUpName));
                    size++;
                    fileStr.append(fileUpName + "、");
                    msg_s = "用户" + userName + "上传了文件" + fileUpName;
                }
            }
            fileStr.replace(fileStr.lastIndexOf("、"), fileStr.length(), "");
            if(size>1){
                return size +"个文件上传成功";
            }else {
                return msg_s;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

}
