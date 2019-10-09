package com.eastValley.control.spittr;

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
    //方法级别的请求处理
    @RequestMapping(method = RequestMethod.GET,value = {"/","/home","/index"})
    public String home(){
        return "home";
    }

    @RequestMapping(method = RequestMethod.GET,value = {"/home2"})
    public String home4Carousel(){
        return "home4Carousel";
    }

}
