package cdu.wx.app.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "encodingFilter",urlPatterns = "/*")
public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) servletRequest;
    if(req.getMethod().equals("POST")){
        req.setCharacterEncoding("utf-8");
    }else{
        req =new MyEncodingReqWrapper(req);
    }
    filterChain.doFilter(req,servletResponse);
        System.out.println("EncodingFilter执行成功");
    }

    @Override
    public void destroy() {

    }
}
