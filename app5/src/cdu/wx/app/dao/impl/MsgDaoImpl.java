package cdu.wx.app.dao.impl;

import cdu.wx.app.dao.MsgDao;
import cdu.wx.app.dao.UserDao;
import cdu.wx.app.model.Msg;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MsgDaoImpl extends BaseDao implements MsgDao {
    UserDao userDao=new UserDaoImpl();
    @Override
    public Msg get(int id) {
       Msg msg= null;
        String sql="SELECT * FROM guestbook_table WHERE id=?";
        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            rs =pstmt.executeQuery();
            while (rs.next()){
                msg =new Msg();
                msg.setId(rs.getInt("id"));
                msg.setUser(userDao.get(rs.getInt("userId")));
                msg.setSubject(rs.getString("subject"));
                msg.setContent(rs.getString("content"));
                msg.setAddMsgTime(rs.getLong("addMsgTime"));
                msg.setIsReplied(rs.getInt("isReplied"));
                msg.setReUser(userDao.get(rs.getInt("reUserId")));
                msg.setReply(rs.getString("reply"));
                msg.setReTime(rs.getLong("reTime"));
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Dao获取留言失败！：id="+id);
        }
        return msg;
    }

    @Override
    public List<Msg> findAll() {
        Msg msg= null;
        List<Msg> msgList=new ArrayList<Msg>();
        String sql="SELECT * FROM guestbook_table ";
        try {
            stmt=conn.createStatement();//获取操作数据库的对象
            rs =stmt.executeQuery(sql);//执行sql，获取结果集
            while (rs.next()) {//遍历结果集，取出数据
                    msg =new Msg();
                    msg.setId(rs.getInt("id"));
                    msg.setUser(userDao.get(rs.getInt("userId")));
                    msg.setSubject(rs.getString("subject"));
                    msg.setContent(rs.getString("content"));
                    msg.setAddMsgTime(rs.getLong("addMsgTime"));
                    msg.setIsReplied(rs.getInt("isReplied"));
                    msg.setReUser(userDao.get(rs.getInt("reUserId")));
                    msg.setReply(rs.getString("reply"));
                    msg.setReTime(rs.getLong("reTime"));
                    msgList.add(msg);
                }
            }catch (SQLException e){
                e.printStackTrace();
                System.out.println("Dao获取留言失败！findAll");
            }
            return msgList;
    }

    @Override
    public List<Msg> findByPage(int start, int num, int reStatus) {
        List<Msg> msgList=new ArrayList<Msg>();
        String sql="SELECT * FROM guestbook_table Order by addMsgTime DESC LIMIT ?,? ";
        if(reStatus>0){
            sql+="WHERE isReplied=?";
        }
        try {
           pstmt=conn.prepareStatement(sql);//作数据库的对
            pstmt.setInt(1,start);
            pstmt.setInt(2,num);
            if (reStatus>0){
                pstmt.setInt(3,reStatus);
            }
            rs =pstmt.executeQuery();//执行sql，获取结果集
            while (rs.next()) {//遍历结果集，取出数据
               Msg msg =new Msg();
                msg.setId(rs.getInt("id"));
                msg.setUser(userDao.get(rs.getInt("userId")));
                msg.setSubject(rs.getString("subject"));
                msg.setContent(rs.getString("content"));
                msg.setAddMsgTime(rs.getLong("addMsgTime"));
                msg.setIsReplied(rs.getInt("isReplied"));
                msg.setReUser(userDao.get(rs.getInt("reUserId")));
                msg.setReply(rs.getString("reply"));
                msg.setReTime(rs.getLong("reTime"));
                msgList.add(msg);
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Dao获取留言失败！findByPage");
        }
        return msgList;




    }

    @Override
    public int count(int reStatus) {
        int count=0;
        String sql="SELECT count(*) FROM guestbook_table";
        if (reStatus>0){
            sql+="WHERE isReplied=" +reStatus;
        }
        try{
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);
            while (rs.next()){
                count=rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace()  ;
            System.out.println("获取留言记录失败！");
        }

        return count;
    }

    @Override
    public int insert(Msg msg) {
        int rows=0;
        String sql="INSERT INTO guestbook_table(userId,subject,content,addMsgTime) VALUES(?,?,?,?)";
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,msg.getUser().getId());
            pstmt.setString(2,msg.getSubject());
            pstmt.setString(3,msg.getContent());
            pstmt.setLong(4,new Date().getTime());
            rows=pstmt.executeUpdate();
            System.out.println("dao添加留言："+sql+","+msg);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("dao添加留言失败："+sql+","+msg);
        }
        return rows;

    }

    @Override
    public int updateReply(Msg msg) {
        int rows=0;
        String sql="UPDATE guestbook_table SET isReplied=?,reUserId=?,reply=?,reTime=? WHERE id=?";
        try{
            System.out.println("能执行到这里public int updateReply(Msg msg)");
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,msg.getIsReplied());
            System.out.println("能执行完1");
            int aid = msg.getReUser().getId();
            System.out.println(aid);

            pstmt.setInt(2,aid);
            System.out.println("能执行完2");
            pstmt.setString(3,msg.getReply());
            System.out.println("能执行完3");
            pstmt.setLong(4,msg.getReTime());
            System.out.println("能执行完4");
            pstmt.setInt(5,msg.getId());
            System.out.println("能执行完5");
            rows=pstmt.executeUpdate();
            System.out.println("能执行完pstmt");
            System.out.println("dao添加留言："+rows+","+msg);
        }catch (SQLException e){
            e.printStackTrace()  ;
            System.out.println("dao添加留言失败(管理员)");
            System.out.println("updateReplyMsg 不成功！");
        }

        return rows;
    }

    @Override
    public int update(Msg msg) {
        int rows=0;
        String sql="UPDATE guestbook_table SET UserId=?,subject=?,content=?,addMsgTime=?,isReplied=?,reUserId=?reply=?,reTime=? WHERE id=?";
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,msg.getUser().getId());
            pstmt.setString(2,msg.getSubject());
            pstmt.setString(3,msg.getContent());
            pstmt.setLong(4,msg.getAddMsgTime());
            pstmt.setInt(5,msg.getIsReplied());
            pstmt.setInt(6,msg.getReUser().getId());
            pstmt.setString(7,msg.getReply());
            pstmt.setLong(8,msg.getReTime());
           pstmt.setInt(9,msg.getId());
            rows=pstmt.executeUpdate();
            System.out.println("dao添加留言："+rows+","+msg);
        }catch (Exception e){
            e.printStackTrace()  ;
            System.out.println("updateMsg不成功");

        }

        return rows;
    }

    @Override
    public int delete(int id) {
        int rows=0;
        String sql="DELETE FROM guestbook_table WHERE id=?";
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
