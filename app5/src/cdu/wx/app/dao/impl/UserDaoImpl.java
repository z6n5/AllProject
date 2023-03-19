package cdu.wx.app.dao.impl;

import cdu.wx.app.dao.MsgDao;
import cdu.wx.app.dao.UserDao;
import cdu.wx.app.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User get(int id) {
        User user= null;
        String sql="SELECT * FROM user_table WHERE id=?";
        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            rs =pstmt.executeQuery();
            while (rs.next()){
                user =new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setIsAdmin(rs.getInt("isAdmin"));


            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Dao获取用户失败！");
        }
        return  user;
    }


    @Override
    public User get(String name, String password) {
        User user= null;
        String sql="SELECT * FROM user_table WHERE name=? AND password=?";
        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,name);
            pstmt.setString(2,password);
            rs =pstmt.executeQuery();
            while (rs.next()){
                user =new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setIsAdmin(rs.getInt("isAdmin"));


            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Dao获取用户失败！");
        }
        return  user;

    }

    @Override
    public List<User> findAll() {
        List<User> userList= new ArrayList<>();

        String sql="SELECT * FROM user_table ";
        try {
            stmt=conn.createStatement();//获取操作数据库的对象
            rs =stmt.executeQuery(sql);//执行sql，获取结果集
            while (rs.next()){//遍历结果集，取出数据
                User user =new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setIsAdmin(rs.getInt("isAdmin"));
                userList.add(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public List<User> findByePage(int start, int num) {
        List<User> userList= new ArrayList<>();
        User user= null;
        String sql="SELECT * FROM user_table ORDER BY id LIMIT ?,?";
        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,start);
            pstmt.setInt(2,num);
            rs =pstmt.executeQuery();
            while (rs.next()){
                user =new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setIsAdmin(rs.getInt("isAdmin"));
                userList.add(user);

            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("分页失败！");
        }
        return  userList;
    }

    @Override
    public int count() {
        int count=0;
        String sql="SELECT count(*) FROM user_table";
        try{
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);
            while (rs.next()){
                count=rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace()  ;
            System.out.println("获取用户记录失败！");
        }

        return count;
    }

    @Override
    public int insert(User user) {
        int rows=0;
        String sql="INSERT INTO user_table(name,password) VALUES(?,?)";
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,user.getName());
            pstmt.setString(2,user.getPassword());
            rows=pstmt.executeUpdate();
            System.out.println("添加用户成功2");
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows;


    }

    @Override
    public int update(User user) {
        int rows=0;
        String sql="UPDATE user_table SET name=?,password=? WHERE id=?";
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(2,user.getPassword());
            rows=pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace()  ;
        }

        return rows;
    }

    @Override
    public int delete(int id) {
        int rows=0;
        String sql="DELETE FROM user_table WHERE id=?";
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            rows=pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }


        return rows;
    }
}
