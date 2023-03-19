package cdu.wx.app.controller;

import cdu.wx.app.model.Msg;
import cdu.wx.app.model.User;
import cdu.wx.app.service.Impl.MsgServiceImpl;
import cdu.wx.app.service.MsgService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet("/reply")
public class ReplyMsgServlet extends HttpServlet {
    MsgService msgService=new MsgServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session=request.getSession();
        User user=(User) session.getAttribute("user");
        String sid=request.getParameter("id");
        if (sid==null||sid.equals("")){
            response.sendRedirect("list");
        }
        Msg msg =new Msg();
        msg.setId(Integer.parseInt(sid));
        msg.setIsReplied(2);
        msg.setReUser(user);
        msg.setReply(request.getParameter("reply"));//获取回复内容
        msg.setReTime(new Date().getTime());
        System.out.println("回复即将执行");
        msgService.reply(msg);//todo这个方法未执行
        System.out.println("回复执行成功");
        response.sendRedirect("list");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid=request.getParameter("id");
        Msg msg=msgService.get(sid);
        request.setAttribute("msg",msg);
        request.getRequestDispatcher("reply.do").forward(request,response);
    }
}
