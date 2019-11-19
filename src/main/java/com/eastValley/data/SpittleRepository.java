package com.eastValley.data;

import java.util.List;

/**
 * @author java_shj
 * @desc
 * @createTime 2019/9/30 15:18
 **/
public interface SpittleRepository {
    List<Spittle> findSpittles(long max, int count);
}
