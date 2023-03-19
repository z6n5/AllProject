package cdu.wx.app.dao;

import cdu.wx.app.model.Msg;

import java.util.List;

public interface MsgDao {
    Msg get(int id);

    List<Msg> findAll();
    List<Msg> findByPage(int start, int num, int reStatus);
    int count(int reStatus);
    int insert(Msg msg);
    int update(Msg msg);
    int updateReply(Msg msg);

    int delete(int id);
}
