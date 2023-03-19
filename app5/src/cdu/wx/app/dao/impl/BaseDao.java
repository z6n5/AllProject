package cdu.wx.app.dao.impl;

import java.sql.*;

public class BaseDao {

    String driver="com.mysql.jdbc.Driver";
    String url="jdbc:mysql://localhost:3306/learn?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
    String jdbcUser= "root";
    String jdbcPwd="789652";
    protected Connection conn=null;
    protected Statement stmt=null;
    protected PreparedStatement pstmt=null;
    protected ResultSet rs=null;
    public BaseDao(){
        connect();
    }
    void connect(){
        try{
            Class.forName(driver).newInstance();
            conn= DriverManager.getConnection(url,jdbcUser,jdbcPwd);


        } //ClassNotFoundException是java运行时加载类时抛出的异常(Exception)，并且名称是在运行时提供。
        catch (IllegalAccessException e) {
            e.printStackTrace();
            System.out.println("IllegalAccessException");
        } catch (InstantiationException e) {
            e.printStackTrace();
            System.out.println("InstantiationException");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("SQLException");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("ClassNotFoundException");
        }


    }



    protected void close(){
        try {
            if(rs !=null&&!rs.isClosed()){
                rs.isClosed();
            }
            if (stmt!=null&&!stmt.isClosed()){
                stmt.close();
            }
            if (pstmt!=null&&!pstmt.isClosed()){
                pstmt.close();
            }
            if (conn!=null&&!conn.isClosed()){
                conn.close();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


}
