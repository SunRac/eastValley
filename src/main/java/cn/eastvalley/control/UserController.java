package cn.eastvalley.control;

import cn.eastvalley.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author java_shj
 * @desc
 * @createTime 2020/1/17 11:30
 **/
@Controller
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    UserService userService;

    @RequestMapping("/user")
    public String getUser() {
        List<Map<String, Object>> allUser = userService.getAllUser();
        System.out.println(allUser);
        return "index";
    }
}
