package cdu.wx.app.service.Impl;

import cdu.wx.app.dao.UserDao;
import cdu.wx.app.dao.impl.UserDaoImpl;
import cdu.wx.app.model.User;
import cdu.wx.app.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
UserDao userDao =new UserDaoImpl();
    @Override
    public List<User> findByPage(int page, int pageSize) {
        return userDao.findByePage((page-1)*pageSize,pageSize);
    }

    @Override
    public List<User> findByPage(String sPage, String sPageSize) {
        int page=1;
        int pageSize=10;
        if (sPage != null|| !sPage.equals("")) {
            page=Integer.parseInt(sPageSize);
            if(page<1) page=1;
        }
        if (sPageSize != null|| !sPageSize.equals("")){
            pageSize=Integer.parseInt(sPageSize);
        }
        return findByPage(page,pageSize);
    }

    @Override
    public List<User> findByPage(String sPage) {
        return findByPage(sPage,null);
    }

    @Override
    public User get(String sid) {
        if (sid==null || sid.equals("")){return null;}
        return userDao.get(Integer.parseInt(sid));
    }

    @Override
    public User get(String name, String password) {
        return userDao.get(name, password);
    }

    @Override
    public int count() {
        return userDao.count();
    }

    @Override
    public boolean add(User user) {
        if (user==null){
        return false;
    }
        System.out.println("执行成功1");
        return userDao.insert(user)==1 ? true:false;
    }

    @Override
    public boolean del(String sid) {
        if (sid==null){
            return false;
        }
        return userDao.delete(Integer.parseInt(sid))==1? true:false;
    }

    @Override
    public boolean mod(User user) {
        if (user==null){
            return false;
        }
        return userDao.insert(user)==1? true:false;
    }
}
