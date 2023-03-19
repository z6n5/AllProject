package cdu.wx.app.dao;

import cdu.wx.app.model.User;

import java.util.List;

public interface UserDao {
    User get(int id);
    User get(String name,String password);
    List<User> findAll();
    List<User> findByePage(int start, int num);
    int count();
    int insert(User user);
    int update(User user);
    int delete(int id);
}
