package cn.eastvalley.dao.user;

import cn.eastvalley.dao.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author java_shj
 * @desc
 * @createTime 2020/1/17 10:55
 **/
@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao{
    /**
     * 查询所有用户
     *
     * @return 用户列表
     */
    @Override
    public List<Map<String, Object>> getAllUser() {
        Map<String,Object> params = new HashMap<>();
        return queryForList("getAllUser", params);
    }
}
