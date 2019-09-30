package com.eastValley.data.impl;

import com.eastValley.data.Spittle;
import com.eastValley.data.SpittleRepository;
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
