package com.asiainfo.alarm.service.impl;

import com.asiainfo.alarm.dao.IAlarmDao;
import com.asiainfo.alarm.service.IAlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AlarmServiceImpl implements IAlarmService {
    @Autowired
    private IAlarmDao alarmDao;


    @Override
    public List querySourceTabNameByDataCyle(Map<String, Object> sourceTMap) {
        return alarmDao.querySourceTabNameByDataCyle(sourceTMap);
    }

    @Override
    public int querySourceTabNum(Map<String, Object> sourceTMap) {
        return alarmDao.querySourceTabNum(sourceTMap);
    }

    @Override
    public int queryLabelNum(Map<String, Object> labelMap) {
        return alarmDao.queryLabelNum(labelMap);
    }

    @Override
    public List queryLabelInfo(Map<String, Object> labelMap) {
        return alarmDao.queryLabelInfo(labelMap);
    }
}
