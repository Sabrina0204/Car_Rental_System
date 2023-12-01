package com.xjtlusat.zpcr.service.implement;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xjtlusat.zpcr.dao.OrderMapper;
import com.xjtlusat.zpcr.entity.Order;
import com.xjtlusat.zpcr.service.OrderService;
import com.xjtlusat.zpcr.service.PricingService;
import com.xjtlusat.zpcr.util.OrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


@Service
@Slf4j
public class PricingServiceImpl implements PricingService {

    @Autowired
    private OrderMapper orderMapper;

    public String pricing(String orderID, Model model) {
        LambdaQueryWrapper<Order> wrapper = Wrappers.<Order>lambdaQuery();
        if (StrUtil.isNotBlank(orderID)) {
            wrapper.eq(Order::getId, orderID);
        }
        Order order = orderMapper.selectOne(wrapper);
        if (order.getStatus().equals(OrderStatus.ARREARAGE.getStatus())) {
            order.setPrice(order.getExtraFee());
        }
        model.addAttribute("data", order);
        return "pricing";
    }

}
