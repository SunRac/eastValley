package cn.eastvalley.config;

import cn.eastvalley.servlet.ValidateCodeServlet;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

/**
 * @author java_shj
 * @desc  空谷项目自定义Initializer，来注册自定义的Servlet、Filter、Listener,并映射到对应的url
 * @createTime 2020/1/10 17:36
 **/
public class EvServletInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
//        注册想要引入的Servlet
        Dynamic validateCodeServlet = servletContext.addServlet("validateCodeServlet", ValidateCodeServlet.class);
//        映射Servlet
        validateCodeServlet.addMapping("/validateCodeServlet");
    }
}
