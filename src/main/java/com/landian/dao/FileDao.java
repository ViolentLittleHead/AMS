package com.landian.dao;

import com.landian.domain.File;

import java.util.List;

public interface FileDao {
    /**
     * 记录上传到数据库中
     * @param username 用户名
     * @param path 路径
     * @param date 时间
     * @return true：代表上传成功 false：代表上传失败
     */
    boolean fileupload(File file);

    /**
     * 通过用户名查找该用户全部的文件信息
     * @param username
     * @return File[] 文件类数组
     */
    List<File> selectAllFile(String username);

    /**
     * 删除指定id的文件信息
     * @param id
     */
    void delete(int id);

    /**
     * 查询总记录数
     * @return
     */
    int findTotalCount(String username);

    /**
     * 分页查询每页记录
     * @param start
     * @param rows
     * @return
     */
    List<File> findByPage(int start,int rows,String username);

    /**
     * 通过文件ID查询文件路径
     * @param id
     * @return
     */
    File findFileById(String id);

    /**
     * 查询文件表总记录数
     * @return
     */
    int findTotalCount();

    /**
     * 查询指定行数文件全部信息
     * @param start
     * @param rows
     * @return
     */
    List<File> findFileByPage(int start, int rows);
}
