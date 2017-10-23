package com.asiainfo.alarm.dao;

import java.util.List;
import java.util.Map;

public interface IAlarmDao {

    List querySourceTabName(Map<String, Object> sourceTMap);
}
