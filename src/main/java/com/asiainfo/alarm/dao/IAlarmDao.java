package com.asiainfo.alarm.dao;

import java.util.List;
import java.util.Map;

public interface IAlarmDao {

    /**
     *
     * @param sourceTMap
     * @return
     * 功能描述：
     * 根据周期从指标层配置表中直接取出源表名
     */
    List querySourceTabNameByDataCyle(Map<String, Object> sourceTMap);

    int querySourceTabNum(Map<String, Object> sourceTMap);

    int queryLabelNum(Map<String, Object> labelMap);

    List queryLabelInfo(Map<String, Object> labelMap);
}
