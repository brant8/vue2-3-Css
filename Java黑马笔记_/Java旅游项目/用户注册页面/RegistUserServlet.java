package com.example.DemoWeb112233.web.servlet;

import com.example.DemoWeb112233.domain.ResultInfo;
import com.example.DemoWeb112233.domain.User;
import com.example.DemoWeb112233.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(name = "RegistUserServlet", value = "/registUserServlet")
public class RegistUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //后加验证码校验
        String check = request.getParameter("check");//注册页面提交过来的验证码的name="check"
        //从session中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String)session.getAttribute("CHECKCODE_SERVER");
        //验证码使用完后清除，否则用户点击后退键仍可用
        session.removeAttribute("CHECKCODE_SERVER");
        //比较
        if(checkcode_server==null || !checkcode_server.equalsIgnoreCase(check)){
            //验证码错误
            ResultInfo info = new ResultInfo();
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            //将info对象序列化为json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);

            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }

        //1.获取数据
        Map<String, String[]> map = request.getParameterMap();
        //2.封装对象
        User user = new User();
        /*BeanUtils.populate( Object bean, Map properties )，
        *这个方法会遍历map<key, value>中的key，如果bean中有这个属性，就把这个key对应的value值赋给bean的属性。
        */
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //3.调用service完成注册
        UserServiceImpl service = new UserServiceImpl();
        boolean flag = service.regist(user);
        //类ResultInfo继承Serializable->可序列化？
        ResultInfo result = new ResultInfo();//用于封装后端返回前端数据对象
        //4.响应结果
        if(flag){
            //注册成功
            result.setFlag(true);
        }else{
            //注册失败
            result.setFlag(false);
            result.setErrorMsg("注册失败");
        }
        //将info对象序列化为json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(result);
        //将json数据写回客户端
        //并且设置content-type
        response.setContentType("application/json;cha");
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
