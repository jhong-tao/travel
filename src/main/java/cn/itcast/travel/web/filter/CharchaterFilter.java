package cn.itcast.travel.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Author: JHong.Tao
 * Date: 2019-09-11-14:51
 * Version：1.0.0
 * Description:
 */
@WebFilter(value = "/*")
public class CharchaterFilter implements Filter {
    // 重写初始化方法
    public void init(FilterConfig config) throws ServletException {
    }

    // 重写doFilter 方法，一般都要重写
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws ServletException, IOException {
        // 将父接口转为子接口
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        //获取请求方法
        String method = request.getMethod();
        // 解决post中文乱码问题
        if(method.equalsIgnoreCase("post")){
            request.setCharacterEncoding("UTF-8");
        }
        // 处理响应乱码问题
        response.setContentType("text/html;charset=UTF-8");
        // 放行
        filterChain.doFilter(request, response);
    }

    // 重写 关闭服务器的时候执行的销毁方法
    public void destroy() {
    }

}
