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
import utils.DateFormatUtils;

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
    @GetMapping("/querySourceTabInfo")
    public List<Map<String, Object>> querySourceTabInfo(HttpServletRequest request, HttpServletResponse response) {
        String sourceTabName = ""; //模糊搜索源表名
        String tmp_dataCycle; //周期解析
        int dataCyle = 0; //源表周期
        String status = ""; //状态
        Date d = new Date();
        String opTime_d = DateFormatUtils.dateToStr_YYYYMMDD(d);
        String opTime_m = DateFormatUtils.dateToStr_YYYYMM(d);
        int currentPage = 0;
        int pageSize = 8;
        if (null != request.getParameter("page")) {
            currentPage = Integer.valueOf(request.getParameter("page")).intValue();
        }
        int startIndex = (currentPage - 1) * pageSize; //页码，每页的起始索引
        if (null != request.getParameter("sourceTabName")) {
            sourceTabName = request.getParameter("sourceTabName").trim();
        }
        if (null != request.getParameter("dataCyle")) {
            tmp_dataCycle = request.getParameter("dataCyle").trim();
            if (tmp_dataCycle.substring(1) == "1") {
                dataCyle = 1;
            } else {
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
        sourceTMap.put("pageSize", pageSize);
        sourceTMap.put("startIndex", startIndex);
        List sourceTabList = alarmService.querySourceTabNameByDataCyle(sourceTMap);
        return sourceTabList;
    }

    @ResponseBody
    @GetMapping("/querySourceTabNum")
    public Map<String, Object> querySourceTabNum(HttpServletRequest request, HttpServletResponse response) {
        String sourceTabName = ""; //模糊搜索源表名
        String tmp_dataCycle; //周期解析
        int dataCyle = 0; //源表周期
        String status = ""; //状态
        if (null != request.getParameter("sourceTabName")) {
            sourceTabName = request.getParameter("sourceTabName").trim();
        }
        if (null != request.getParameter("dataCyle")) {
            tmp_dataCycle = request.getParameter("dataCyle").trim();
            if (tmp_dataCycle.substring(1) == "1") {
                dataCyle = 1;
            } else {
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
        int count = alarmService.querySourceTabNum(sourceTMap);
        HashMap result = new HashMap();
        result.put("count", count);
        return result;
    }

    @ResponseBody
    @GetMapping("/queryLabelNum")
    public Map<String, Object> queryLabelNum(HttpServletRequest request, HttpServletResponse response) {
        String labelName = ""; //模糊搜索源表名
        String tmp_dataCycle; //周期解析
        int dataCyle = 0; //源表周期
        String status = ""; //状态
        if (null != request.getParameter("labelName")) {
            labelName = request.getParameter("labelName").trim();
        }
        if (null != request.getParameter("dataCyle")) {
            tmp_dataCycle = request.getParameter("dataCyle").trim();
            if (tmp_dataCycle.substring(1) == "1") {
                dataCyle = 1;
            } else {
                dataCyle = 2;
            }
        }
        if (null != request.getParameter("status")) {
            status = request.getParameter("status").trim();
        }
        Map<String, Object> labelMap = new HashMap<String, Object>();
        labelMap.put("labelName", labelName);
        labelMap.put("dataCyle", dataCyle);
        labelMap.put("status", status);
        int count = alarmService.queryLabelNum(labelMap);
        HashMap result = new HashMap<String, Object>();
        result.put("count", count);
        return result;
    }

    @ResponseBody
    @GetMapping("/queryLabelInfo")
    public void queryLabelInfo(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String labelName = ""; //模糊搜索源表名
        String tmp_dataCycle; //周期解析
        int dataCyle = 0; //源表周期
        String status = ""; //状态
        int currentPage = 0;
        int pageSize = 8;
        if (null != request.getParameter("page")) {
            currentPage = Integer.valueOf(request.getParameter("page"));
        }
        int startIndex = (currentPage - 1) * pageSize;
        if (null != request.getParameter("labelName")) {
            labelName = request.getParameter("labelName").trim();
        }
        if (null != request.getParameter("dataCyle")) {
            tmp_dataCycle = request.getParameter("dataCyle").trim();
            if (tmp_dataCycle.substring(1) == "1") {
                dataCyle = 1;
            } else {
                dataCyle = 2;
            }
        }
        if (null != request.getParameter("status")) {
            status = request.getParameter("status").trim();
        }
        Map<String, Object> labelMap = new HashMap<String, Object>();
        labelMap.put("labelName", labelName);
        labelMap.put("dataCyle", dataCyle);
        labelMap.put("status", status);
        labelMap.put("pageSize", pageSize);
        labelMap.put("startIndex", startIndex);
        List labelList = alarmService.queryLabelInfo(labelMap);
    }
}
