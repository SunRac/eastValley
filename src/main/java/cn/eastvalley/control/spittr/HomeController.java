package cn.eastvalley.control.spittr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author java_shj
 * @desc    简单的一个springMvc控制器
 * @createTime 2019/9/30 11:29
 **/
@Controller
//类级别的请求处理
public class HomeController {
    private static Logger logger = LoggerFactory.getLogger(HomeController.class);
    //方法级别的请求处理
    @RequestMapping(method = RequestMethod.GET,value = {"/home"})
    public String home(){
        logger.info("首页方法触发了");
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET,value = {"/homeNew"})
    public String home4Carousel(){
        return "home4Carousel";
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/index"})
    public String homeNew() {
        return "index";
    }

}
