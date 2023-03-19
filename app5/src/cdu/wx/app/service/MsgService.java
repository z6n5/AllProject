package cdu.wx.app.service;

import cdu.wx.app.model.Msg;

import java.util.List;

public interface MsgService {
    List<Msg> findByPage(int page, int pageSize, int reStatus);
    List<Msg> findByPage(String sPage, String sPageSize, int reStatus);
    List<Msg> findByPage(String sPage, int reStatus);//?
    int count(int reStatus);
    boolean add(Msg msg);
    boolean reply(Msg msg);//updateReply
    boolean del(String sid);
    Msg get(String sid);
    Msg get(int id);

}
