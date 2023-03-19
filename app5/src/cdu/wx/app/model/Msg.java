package cdu.wx.app.model;

import java.io.Serializable;

public class Msg implements Serializable {
    private int id;
    private User user;
    private String subject;
    private String content;
    private long addMsgTime;
    private int isReplied;
    private User reUser;
    private String reply;
    private long reTime;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setAddMsgTime(long addMsgTime) {
        this.addMsgTime = addMsgTime;
    }

    public int getIsReplied() {
        return isReplied;
    }

    public long getAddMsgTime() {
        return addMsgTime;
    }

    public long getReTime() {
        return reTime;
    }

    public String getContent() {
        return content;
    }

    public String getReply() {
        return reply;
    }

    public String getSubject() {
        return subject;
    }

    public User getReUser() {
        return reUser;
    }

    public User getUser() {
        return user;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setIsReplied(int isReplied) {
        this.isReplied = isReplied;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public void setReTime(long reTime) {
        this.reTime = reTime;
    }

    public void setReUser(User reUser) {
        this.reUser = reUser;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "id=" + id +
                ", user=" + user +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", addMsgTime=" + addMsgTime +
                ", isReplied=" + isReplied +
                ", reUser=" + reUser +
                ", reply='" + reply + '\'' +
                ", reTime=" + reTime +
                '}';
    }
}
