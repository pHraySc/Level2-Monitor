package com.asiainfo.alarm.service;

import java.util.List;
import java.util.Map;

public interface IAlarmService {

    List querySourceTabNameByDataCyle(Map<String, Object> sourceTMap);

    int querySourceTabNum(Map<String, Object> sourceTMap);

    int queryLabelNum(Map<String, Object> labelMap);

    List queryLabelInfo(Map<String, Object> labelMap);
}
