package com.landian.service.impl;

import com.landian.dao.FileDao;
import com.landian.dao.impl.FileDaoImpl;
import com.landian.domain.File;
import com.landian.domain.PageBean;
import com.landian.service.FileService;
import com.landian.util.FileUtil;

import java.util.List;

public class FileServiceImpl implements FileService {
    private FileDao fileDao = new FileDaoImpl();
    private FileUtil fileUtil = new FileUtil();
    @Override
    public boolean fileupload(File file) {
        fileDao.fileupload(file);
        return false;
    }

    @Override
    public void delSelectedFile(String[] ids) {

        if(ids != null && ids.length >0){
            //遍历数组
            for (String id:ids){
                fileUtil.fileDeleteByPath(fileDao.findFileById(id).getPath());
                //调用dao在数据库记录删除
                fileDao.delete(Integer.parseInt(id));
            }
        }
    }

    @Override
    public PageBean<File> findFileByPage(String _currentPage, String _rows,String username) {

        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        //创建空的PageBean对象
        PageBean<File> pb = new PageBean<File>();
        //设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        //调用dao查询总记录数
        int totalCount = fileDao.findTotalCount(username);
        pb.setTotalCount(totalCount);
        //调用dao查询List集合
        //计算开始的记录索引
        int start = (currentPage - 1)*rows;
        List<File> list = fileDao.findByPage(start,rows,username);
        pb.setList(list);

        //计算总页码
        int totalPage = (totalCount % rows) == 0 ? totalCount/rows : (totalCount/rows) + 1;
        pb.setTotalPage(totalPage);

        return pb;
    }

    @Override
    public PageBean<File> findAllFileByPage(String _currentPage, String _rows) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        //创建空的PageBean对象
        PageBean<File> pb = new PageBean<File>();
        //设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        //调用dao查询总记录数
        int totalCount = fileDao.findTotalCount();
        pb.setTotalCount(totalCount);
        //调用dao查询List集合
        //计算开始的记录索引
        int start = (currentPage - 1)*rows;
        List<File> list = fileDao.findFileByPage(start,rows);
        pb.setList(list);

        //计算总页码
        int totalPage = (totalCount % rows) == 0 ? totalCount/rows : (totalCount/rows) + 1;
        pb.setTotalPage(totalPage);

        return pb;
    }


}
