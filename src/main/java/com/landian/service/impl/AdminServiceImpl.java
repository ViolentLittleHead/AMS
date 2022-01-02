package com.landian.service.impl;

import com.landian.dao.AdminDao;
import com.landian.dao.impl.AdminDaoImpl;
import com.landian.domain.Admin;
import com.landian.domain.PageBean;
import com.landian.domain.User;
import com.landian.service.AdminService;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    AdminDao adminDao = new AdminDaoImpl();
    @Override
    public Admin login(Admin admin) {
        return adminDao.login(admin);
    }

    @Override
    public PageBean<User> findAllUserByPage(String _currentPage, String _rows) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        //创建空的PageBean对象
        PageBean<User> pb = new PageBean<User>();
        //设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        //调用dao查询总记录数
        int totalCount = adminDao.findTotalCount();
        pb.setTotalCount(totalCount);
        //调用dao查询List集合
        //计算开始的记录索引
        int start = (currentPage - 1)*rows;
        List<User> list = adminDao.findByPage(start,rows);
        pb.setList(list);

        //计算总页码
        int totalPage = (totalCount % rows) == 0 ? totalCount/rows : (totalCount/rows) + 1;
        pb.setTotalPage(totalPage);

        return pb;
    }

    @Override
    public void frozen(String[] usernames) {
        if(usernames != null && usernames.length >0){
            //遍历数组
            for (String username:usernames){
                adminDao.frozen(username);
            }
        }
    }

    @Override
    public void thaw(String[] usernames) {
        if(usernames != null && usernames.length >0){
            //遍历数组
            for (String username:usernames){
                adminDao.thaw(username);
            }
        }
    }
}
