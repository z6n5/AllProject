package cdu.wx.app.filter;

import cdu.wx.app.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "adminFilter",urlPatterns = "/reply")
public class AdminFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        HttpServletResponse resp=(HttpServletResponse) servletResponse;
        HttpSession session=req.getSession();
        User user=(User) session.getAttribute("user");
        if(user!=null&& user.getIsAdmin()==1){
            filterChain.doFilter(servletRequest,servletResponse);
            System.out.println("adminFilter执行成功");
        }else{
            resp.sendRedirect("login.do");
        }
    }

    @Override
    public void destroy() {

    }
}
