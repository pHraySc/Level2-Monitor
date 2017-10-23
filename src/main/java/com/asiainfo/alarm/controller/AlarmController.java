package com.asiainfo.alarm.controller;

import com.asiainfo.alarm.service.IAlarmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("/labelMonitorController")
public class AlarmController {

    private static final Logger logger = LoggerFactory.getLogger(AlarmController.class);

    @Autowired
    private IAlarmService alarmService;

    //跳转标签库监控界面-默认汇总页面
    @RequestMapping("/labelMonitor")
    public String labelMonitor(ModelMap model) {
        model.put("time", new Date());
        model.put("message", "freemarker test!");
        return "labelMonitor";
    }

    //跳转标签库监控界面-源表
    @RequestMapping("/labelMonitor_SOURCE")
    public String labelMonitor_SOURCE(ModelMap model) {
        model.put("time", new Date());
        model.put("message", "freemarker test!");
        return "labelMonitor_SOURCE";
    }

    //跳转标签库监控界面-标签
    @RequestMapping("/labelMonitor_TAG")
    public String labelMonitor_TAG(ModelMap model) {
        model.put("time", new Date());
        model.put("message", "freemarker test!");
        return "labelMonitor_TAG";
    }

    @ResponseBody
    @GetMapping("/querySourceTabNameAndNum")
    public List<Map<String, Object>> querySourceTabNameAndNum(HttpServletRequest request, HttpServletResponse response) {
        String sourceTabName = ""; //模糊搜索源表名
        String tmp_dataCycle; //周期解析
        int dataCyle = 0; //源表周期
        String status = ""; //状态
        if (null != request.getParameter("sourceTabName")) {
            sourceTabName = request.getParameter("sourceTabName").trim();
        }
        if (null != request.getParameter("dataCyle")) {
            tmp_dataCycle = request.getParameter("dataCyle").trim();
            if(tmp_dataCycle.substring(1) == "1"){
                dataCyle = 1;
            }else {
                dataCyle = 2;
            }
        }
        if (null != request.getParameter("status")) {
            status = request.getParameter("status").trim();
        }
        Map<String, Object> sourceTMap = new HashMap<String, Object>();
        sourceTMap.put("sourceTabName", sourceTabName);
        sourceTMap.put("dataCyle", dataCyle);
        sourceTMap.put("status", status);
        List sourceTabList = alarmService.querySourceTabNameList(sourceTMap);
        return sourceTabList;
    }
}
