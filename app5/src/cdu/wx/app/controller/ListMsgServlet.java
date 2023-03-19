package cdu.wx.app.controller;

import cdu.wx.app.model.Msg;
import cdu.wx.app.service.Impl.MsgServiceImpl;
import cdu.wx.app.service.MsgService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/list")
public class ListMsgServlet extends HttpServlet {
    MsgService msgService=new MsgServiceImpl();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sPage= request.getParameter("page");
        int page=1;
        int pageSize=3;
        if(sPage!=null&&!sPage.equals("")){
            page=Integer.parseInt(sPage);
        }
        int count=msgService.count(0);
        int pageCount = count % pageSize > 0 ? count/pageSize+1 : count/pageSize;
        List<Msg> msgList =msgService.findByPage(page,pageSize,0);
        request.setAttribute("msgList",msgList);
        request.setAttribute("page",page);
        request.setAttribute("pageCount",pageCount);
        request.setAttribute("count",count);
        request.getRequestDispatcher("list.do").forward(request,response);
    }
}
