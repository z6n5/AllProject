package cdu.wx.app.controller;

import cdu.wx.app.model.User;
import cdu.wx.app.service.Impl.UserServiceImpl;
import cdu.wx.app.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    UserService userService=new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String name=request.getParameter("name");
        String password=request.getParameter("password");
        HttpSession session=request.getSession();
        User user=userService.get(name,password);
        System.out.println("111");
        if(user!=null){
            session.setAttribute("user",user);
            System.out.println("登录成功");
        }
     //   request.getRequestDispatcher("list.do").forward(request,response);

response.sendRedirect("list");
    }

}
