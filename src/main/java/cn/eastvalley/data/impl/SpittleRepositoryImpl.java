package cn.eastvalley.data.impl;

import cn.eastvalley.data.Spittle;
import cn.eastvalley.data.SpittleRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author java_shj
 * @desc
 * @createTime 2019/9/30 16:11
 **/
@Component
public class SpittleRepositoryImpl implements SpittleRepository {
    @Override
    public List<Spittle> findSpittles(long max, int count) {
        return null;
    }
}
