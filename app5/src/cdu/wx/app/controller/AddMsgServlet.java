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

@WebServlet("/addMsg")
public class AddMsgServlet extends HttpServlet {
    MsgService msgService=new MsgServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
request.setCharacterEncoding("utf-8");
        HttpSession session=request.getSession();
        User user=(User) session.getAttribute("user");
        Msg msg =new Msg();
        msg.setUser(user);
        msg.setSubject(request.getParameter("subject"));
        msg.setContent(request.getParameter("content"));
        msgService.add(msg);
        response.sendRedirect("list");
    }
}