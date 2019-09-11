package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Author: JHong.Tao
 * Date: 2019-09-11-14:43
 * Version：1.0.0
 * Description:
 */
@WebServlet(value = "/registUserServlet")
public class RegistUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 编码设置
        request.setCharacterEncoding("utf-8");
        // 验证码校验
        String check = request.getParameter("check");
        // 从session中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String)session.getAttribute("CHECKCODE_SERVER");
        // 为了保证验证码只能使用一次，每次使用过后都要主动删除
        session.removeAttribute("CHECKCODE_SERVER");
        if(checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)){
            // 验证码错误
            ResultInfo info = new ResultInfo();
            // 注册失败
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            // 将info对象序列化为json
            String json = new ObjectMapper().writeValueAsString(info);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(json);
            return;
        }

        // 1.获取数据
        Map<String, String[]> map = request.getParameterMap();

        // 2.封装对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // 3.调用service完成注册
        UserService service = new UserServiceImpl();
        boolean flag = service.regist(user);
        ResultInfo info = new ResultInfo();
        // 4.响应结果
        if(flag){
            // 注册成功
            info.setFlag(true);
        }else {
            // 注册失败
            info.setFlag(false);
            info.setErrorMsg("注册失败！！");
        }

        // 将info对象序列化为json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);

        // 将json数据写回客户端
        // 设置response返回类型和编码方式
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
