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
    public List querySourceTabName(Map<String, Object> sourceTMap) {
        List sourceTabList = alarmDao.querySourceTabName(sourceTMap);
        return sourceTabList;
    }
}
