package cdu.wx.app.controller;

import cdu.wx.app.service.Impl.MsgServiceImpl;
import cdu.wx.app.service.MsgService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delMsg")
public class DelMsgServlet extends HttpServlet {
    MsgService msgService=new MsgServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String sid=request.getParameter("id");
       msgService.del(sid);
       response.sendRedirect("list");
    }
}
