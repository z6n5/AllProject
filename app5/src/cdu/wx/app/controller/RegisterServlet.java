package cdu.wx.app.controller;

import cdu.wx.app.model.User;
import cdu.wx.app.service.Impl.UserServiceImpl;
import cdu.wx.app.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    UserService userService=new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        User user=new User();
        String name=request.getParameter("name");
        String password=request.getParameter("password");
        System.out.println(name);
        System.out.println(password);
        if(userService.add(user)){
         response.sendRedirect("login.do");
            System.out.println("添加用户成功1");
        }else{
            System.out.println("添加失败");
            response.sendRedirect("list");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request,response);
    }
}
