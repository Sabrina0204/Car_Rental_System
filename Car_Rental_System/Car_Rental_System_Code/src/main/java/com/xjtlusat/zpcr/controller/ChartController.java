package com.xjtlusat.zpcr.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xjtlusat.zpcr.dao.OrderMapper;
import com.xjtlusat.zpcr.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("dashboard")
public class ChartController {

    @Autowired
    OrderMapper orderMapper;
    @RequestMapping
    public String chartPage(Model model) {
        List<String> lables = getDays(7);
        model.addAttribute("labels", lables);
        List<Long> data = new ArrayList<>();
        for (String date : lables) {
            QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
            queryWrapper.between("real_appoint_start_time", date + " 00:00:00.0", date + " 23:59:59.0");
            Long cnt = orderMapper.selectCount(queryWrapper);
            data.add(cnt);
        }
        model.addAttribute("data", data);
        return "dashboard";
    }

    private ArrayList<String> getDays(int intervals) {
        ArrayList<String> pastDaysList = new ArrayList<>();
        for (int i = intervals -1; i >= 0; i--) {
            pastDaysList.add(getPastDate(i));
        }
        return pastDaysList;
    }

    private String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }

}
