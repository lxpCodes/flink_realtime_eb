package com.gmall.service;

import com.gmall.bean.VisitorStats;

import java.util.List;

/**
 * Author: Felix
 * Date: 2021/2/27
 * Desc: 访客统计业务层接口
 */
public interface VisitorStatsService {

    List<VisitorStats> getVisitorStatsByNewFlag(int date);

    List<VisitorStats> getVisitorStatsByHr(int date);

}
