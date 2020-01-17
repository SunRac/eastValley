package cn.eastvalley.dao.user;

import java.util.List;
import java.util.Map;

/**
 * @author java_shj
 * @desc
 * @createTime 2020/1/17 10:30
 **/
public interface UserDao {
    /**
     * 查询所有用户
     * @return 用户列表
     */
    List<Map<String, Object>> getAllUser();
}
