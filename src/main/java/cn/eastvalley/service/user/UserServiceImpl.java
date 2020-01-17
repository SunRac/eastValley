package cn.eastvalley.service.user;

import cn.eastvalley.dao.user.UserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author java_shj
 * @desc
 * @createTime 2020/1/17 10:56
 **/
@Service
public class UserServiceImpl implements UserService{

    @Resource
    public UserDao userDao;
    /**
     * 查询用户
     *
     * @return 用户列表
     */
    @Override
    public List<Map<String, Object>> getAllUser() {
        return userDao.getAllUser();
    }
}
