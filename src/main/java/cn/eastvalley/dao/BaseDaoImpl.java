package cn.eastvalley.dao;

import cn.eastvalley.util.ConvertUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author java_shj
 * @desc dao基类，包含了基本的数据库操作方法
 * @createTime 2020/1/16 16:56
 **/
@Repository
public class BaseDaoImpl implements BaseDao{

    @Resource
    private SqlSession sqlSession;

    public SqlSession getSqlSession() {
        return sqlSession;
    }

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    /**
     * 根据ID获取对象
     *
     * @param sqlId sql脚本id
     * @param id    主键
     * @return Object对象
     */
    @Override
    public Object queryForObject(String sqlId, int id) {
        return getSqlSession().selectOne(sqlId, id);
    }

    /**
     * 根据ID获取特定类型的对象
     *
     * @param sqlId sql脚本id
     * @param id    主键
     * @param cls   返回的对象类型
     * @return T对象
     */
    @Override
    public <T> T queryForObject(String sqlId, int id, Class<T> cls) {
        return (T) getSqlSession().selectOne(sqlId, id);
    }

    /**
     * 根据条件获取对象
     *
     * @param sqlId  sql脚本id
     * @param params 查询条件组成的Map
     * @return
     */
    @Override
    public Object queryForObject(String sqlId, Map<String, Object> params) {
        return getSqlSession().selectOne(sqlId, params);
    }

    /**
     * 根据条件获取特定类型的对象
     *
     * @param sqlId  sql脚本id
     * @param params 查询条件组成的Map
     * @param cls    返回的对象类型
     * @return T对象
     */
    @Override
    public <T> T queryForObject(String sqlId, Map<String, Object> params, Class<T> cls) {
        return (T) getSqlSession().selectOne(sqlId, params);
    }

    /**
     * 获取数据总数
     *
     * @param sqlId  sql脚本id
     * @param params 查询条件组成的Map
     * @return
     */
    @Override
    public int getTotalCoutn(String sqlId, Map<String, Object> params) {
        return (Integer) getSqlSession().selectOne(sqlId, params);
    }

    /**
     * 查询列表
     *
     * @param sqlId
     * @param params
     * @param cls    返回的对象类型
     * @return
     */
    @Override
    public <T> List<T> queryForList(String sqlId, Map<String, Object> params, Class<T> cls) {
        return getSqlSession().selectList(sqlId, params);
    }

    /**
     * 查询列表
     *
     * @param sqlId
     * @param params
     * @return
     */
    @Override
    public List<Map<String, Object>> queryForList(String sqlId, Map<String, Object> params) {
        List<Map<String, Object>> mapList = getSqlSession().selectList(sqlId, params);
        return ConvertUtil.convertSqlMap2JavaMap(mapList);
    }

    /**
     * 修改数据
     *
     * @param sqlId
     * @param object 对象
     * @return 修改的行
     */
    @Override
    public int update(String sqlId, Object object) {
        return getSqlSession().update(sqlId, object);
    }

    /**
     * 修改数据
     *
     * @param sqlId
     * @param params 修改的参数
     * @return 修改的行
     */
    @Override
    public int update(String sqlId, Map<String, Object> params) {
        return getSqlSession().update(sqlId, params);
    }

    /**
     * 插入数据
     *
     * @param sqlId
     * @param object 待插入对象
     * @return 受影响的行
     */
    @Override
    public int insert(String sqlId, Object object) {
        return getSqlSession().insert(sqlId, object);
    }

    /**
     * 插入数据
     *
     * @param sqlId
     * @param params 插入的参数
     * @return
     */
    @Override
    public int insert(String sqlId, Map<String, Object> params) {
        return getSqlSession().insert(sqlId, params);
    }

    /**
     * 删除数据
     *
     * @param sqlId
     * @param id    主键
     * @return 受影响的行
     */
    @Override
    public int delete(String sqlId, int id) {
        return getSqlSession().delete(sqlId, id);
    }

    /**
     * 删除数据
     *
     * @param sqlId
     * @param params 删除的条件
     * @return 受影响的行
     */
    @Override
    public int delete(String sqlId, Map<String, Object> params) {
        return getSqlSession().delete(sqlId, params);
    }
}
