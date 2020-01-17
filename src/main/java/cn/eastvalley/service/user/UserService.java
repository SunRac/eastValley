package cn.eastvalley.service.user;

import java.util.List;
import java.util.Map;

/**
 * @author java_shj
 * @desc
 * @createTime 2020/1/17 10:28
 **/
public interface UserService {
    /**
     * 查询用户
     * @return 用户列表
     */
    List<Map<String,Object>> getAllUser();
}
