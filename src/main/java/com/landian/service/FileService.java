package com.landian.service;

import com.landian.domain.File;
import com.landian.domain.PageBean;

public interface FileService {
    /**
     * 文件上传
     * @param file
     * @return
     */
    public boolean fileupload(File file);

    /**
     * 删除选中的文件
     * @param ids
     */
    void delSelectedFile(String[] ids);

    /**
     * 分页查询指定文件的信息
     * @param currentPage
     * @param rows
     * @return
     */
    PageBean<File> findFileByPage(String currentPage,String rows,String username);

    /**
     * 分页查询所有的文件信息
     * @param currentPage
     * @param rows
     * @return
     */
    PageBean<File> findAllFileByPage(String currentPage,String rows);
}
