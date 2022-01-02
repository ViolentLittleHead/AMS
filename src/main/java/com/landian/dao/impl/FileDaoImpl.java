package com.landian.dao.impl;

import com.landian.dao.FileDao;
import com.landian.domain.File;
import com.landian.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;


public class FileDaoImpl implements FileDao {
    //声明JDBCTemplate对象公用
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public boolean fileupload(File file) {
        try {
            String sql = "insert into file(username,path,date,fileName) values(?,?,?,?)";
            template.update(sql,file.getUsername(),file.getPath(),file.getDate(),file.getFileName());
            return true;
        }catch (DataAccessException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<File> selectAllFile(String username) {
        String sql = "select * from file where username = ?";
        List<File> files = template.query(sql, new BeanPropertyRowMapper<File>(File.class), username);
        return files;
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM file WHERE id = ?";
        template.update(sql,id);
    }

    @Override
    public int findTotalCount(String username) {
        String sql = "select count(*) from file where username = '"+username+"'";

        return template.queryForObject(sql,Integer.class);
    }

    @Override
    public List<File> findByPage(int start, int rows,String username) {
        String sql = "select * from file where username = ? limit ? , ?";

        return template.query(sql,new BeanPropertyRowMapper<File>(File.class),username,start,rows);
    }

    @Override
    public File findFileById(String id) {
        String sql = "select * from file where id = ?";

        return template.queryForObject(sql, new BeanPropertyRowMapper<File>(File.class), id);
    }

    @Override
    public int findTotalCount() {
        String sql = "select count(*) from file";

        return template.queryForObject(sql,Integer.class);
    }

    @Override
    public List<File> findFileByPage(int start, int rows) {
        String sql = "select * from file limit ? , ?";

        return template.query(sql,new BeanPropertyRowMapper<File>(File.class),start,rows);
    }
}
