package cn.eastvalley.dao;

import java.util.List;
import java.util.Map;

/**
 * @author java_shj
 * @desc Dao接口基类
 * @createTime 2020/1/16 18:20
 **/
public interface BaseDao {

    /**
     * 根据ID获取对象
     * @param sqlId sql脚本id
     * @param id 主键
     * @return Object对象
     */
    public Object queryForObject(String sqlId, int id);

    /**
     * 根据ID获取特定类型的对象
     * @param sqlId sql脚本id
     * @param id 主键
     * @param cls 返回的对象类型
     * @return T对象
     */
    public <T> T queryForObject(String sqlId, int id, Class<T> cls);

    /**
     * 根据条件获取对象
     * @param sqlId sql脚本id
     * @param params 查询条件组成的Map
     * @return
     */
    public Object queryForObject(String sqlId, Map<String, Object> params);

    /**
     * 根据条件获取特定类型的对象
     * @param sqlId sql脚本id
     * @param params 查询条件组成的Map
     * @param cls 返回的对象类型
     * @return T对象
     */
    public <T> T queryForObject(String sqlId, Map<String, Object> params, Class<T> cls);

    /**
     * 获取数据总数
     * @param sqlId sql脚本id
     * @param params 查询条件组成的Map
     * @return
     */
    public int getTotalCoutn(String sqlId, Map<String, Object> params);

    /**
     * 查询列表
     * @param sqlId
     * @param params
     * @param cls 返回的对象类型
     * @param <T>
     * @return
     */
    public <T> List<T> queryForList(String sqlId, Map<String, Object> params, Class<T> cls);

    /**
     * 查询列表
     * @param sqlId
     * @param params
     * @return
     */
    public List<Map<String, Object>> queryForList(String sqlId, Map<String, Object> params);

    /**
     * 修改数据
     * @param sqlId
     * @param object 对象
     * @return 修改的行
     */
    public int update(String sqlId, Object object);

    /**
     * 修改数据
     * @param sqlId
     * @param params 修改的参数
     * @return 修改的行
     */
    public int update(String sqlId, Map<String, Object> params);

    /**
     * 插入数据
     * @param sqlId
     * @param object 待插入对象
     * @return
     */
    public int insert(String sqlId, Object object);

    /**
     * 插入数据
     * @param sqlId
     * @param params 插入的参数
     * @return
     */
    public int insert(String sqlId, Map<String, Object> params);

    /**
     * 删除数据
     * @param sqlId
     * @param id 主键
     * @return 修改的行
     */
    public int delete(String sqlId, int id);

    /**
     * 删除数据
     * @param sqlId
     * @param params 删除的条件
     * @return
     */
    public int delete(String sqlId, Map<String, Object> params);



}
