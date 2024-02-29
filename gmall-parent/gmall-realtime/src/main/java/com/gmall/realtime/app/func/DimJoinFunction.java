package com.gmall.realtime.app.func;

import com.alibaba.fastjson.JSONObject;

/**
 * Author: Felix
 * Date: 2021/2/19
 * Desc:  维度关联接口
 */
public interface DimJoinFunction<T> {

    /**
     * 需要实现如何从流中对象获取主键
     * @param obj 数据流对象
     * @return
     */
    String getKey(T obj);

    /**
     * 需要实现如何把结果装配给数据流对象
     * @param obj 数据流对象
     * @param dimInfoJsonObj 异步查询结果
     * @throws Exception
     */
    void join(T obj, JSONObject dimInfoJsonObj) throws Exception;
}
