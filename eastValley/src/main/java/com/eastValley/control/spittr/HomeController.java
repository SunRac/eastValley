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
@RequestMapping({"/","/home","/index"})
public class HomeController {
    //方法级别的请求处理
    @RequestMapping(method = RequestMethod.GET)
    public String home(){
        return "home";
    }
}
