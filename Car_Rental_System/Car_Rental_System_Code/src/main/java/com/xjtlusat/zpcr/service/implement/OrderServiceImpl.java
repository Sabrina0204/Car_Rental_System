package com.xjtlusat.zpcr.service.implement;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xjtlusat.zpcr.dao.CarMapper;
import com.xjtlusat.zpcr.dao.CommentMapper;
import com.xjtlusat.zpcr.dao.OrderMapper;
import com.xjtlusat.zpcr.entity.Car;
import com.xjtlusat.zpcr.entity.Comment;
import com.xjtlusat.zpcr.entity.Order;
import com.xjtlusat.zpcr.entity.User;
import com.xjtlusat.zpcr.service.OrderService;
import com.xjtlusat.zpcr.util.DateUtils;
import com.xjtlusat.zpcr.util.OrderStatus;
import com.xjtlusat.zpcr.util.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.sql.Date;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    CarMapper carMapper;
    @Autowired
    CommentMapper commentMapper;

    @Override
    public String getOrdersByUserId(Integer pageNumber, Integer pageSize, HttpSession session, Model model) {
        LambdaQueryWrapper<Order> wrapper = Wrappers.<Order>lambdaQuery();
        User user = (User) session.getAttribute("user");
        if (null != user){
            wrapper.eq(Order::getUserId, user.getId()).orderByDesc(Order::getOrderStartTime);
        } else {
            return "redirect:/login";
        }
        Page<Order> orderPage = orderMapper.selectPage(new Page<>(pageNumber, pageSize), wrapper);
        model.addAttribute("data", orderPage);
        return "orders";
    }

    @Override
    public String generate(Order order) {

        Car car = carMapper.selectById(order.getCarId());

//        id;
//        userId;
//        carId;

        order.setId(OrderUtil.get16UUID());

//        status;

        order.setStatus("Appointed");

//        description;

        order.setDescription(car.getBrand() + " " + car.getType());

//        private Date appointStartTime;
//        private Date appointReturnTime;

//        private Timestamp realAppointStartTime;
//        private Timestamp realAppointReturnTime;

        order.setRealAppointStartTime(new Date(0));
        order.setRealAppointReturnTime(new Date(0));

//        private Timestamp orderStartTime;
//        private Timestamp orderFinishTime;

        order.setOrderStartTime(OrderUtil.getCurrentTimestamp());
        order.setOrderFinishTime(new Date(0));

//        private String paymentMethod;
//        private String paymentId;
//        private Double price;

        order.setPrice(car.getDailyRent()*order.getDays());

//        private String remark;

        orderMapper.insert(order);
        return "redirect:/pricing?id=" + order.getId();
    }

    @Override
    public String pickup(String orderId) {
        Order order = orderMapper.selectById(orderId);
        if (!"paid".equals(order.getStatus())) {
            return "redirect:/orders_admin/selectOrder";
        }
        order.setRealAppointStartTime(OrderUtil.getCurrentTimestamp());
        order.setStatus("InProgress");
        orderMapper.updateById(order);
        return "redirect:/orders_admin/selectOrder";
    }

    @Override
    public String ret(String orderId) {
        Order order = orderMapper.selectById(orderId);
        if (!"InProgress".equals(order.getStatus())) {
            return "redirect:/orders_admin/selectOrder";
        }
        order.setRealAppointReturnTime(OrderUtil.getCurrentTimestamp());
        order.setStatus("Returned");
        orderMapper.updateById(order);
        return "redirect:/orders_admin/selectOrder";
    }

    @Override
    public String insert(Order order) {
        order.setId(OrderUtil.get16UUID());
        orderMapper.insert(order);
        return "redirect:/orders_admin/selectOrder";
    }

    @Override
    public void updateStatus(String orderID, String status) {
        Order order = orderMapper.selectById(orderID);
        order.setStatus(status);
        orderMapper.updateById(order);
    }

    @Override
    public void updatePaymentID(String orderID, String paymentID) {
        Order order = orderMapper.selectById(orderID);
        order.setPaymentId(paymentID);
        orderMapper.updateById(order);
    }

    @Override
    public void updateArrearagePaymentID(String orderID, String arrearagePaymentID) {
        Order order = orderMapper.selectById(orderID);
        order.setArrearagePaymentId(arrearagePaymentID);
        orderMapper.updateById(order);
    }

    @Override
    public void updatePaymentMethod(String orderID, String paymentMethod) {
        Order order = orderMapper.selectById(orderID);
        order.setPaymentMethod(paymentMethod);
        orderMapper.updateById(order);
    }

    @Override
    public boolean judgeStatus(String orderID, String status) {
        Order order = orderMapper.selectById(orderID);
        return order.getStatus().equals(status);
    }

    @Override
    public Order getOrderByID(String orderID) {
        return orderMapper.selectById(orderID);
    }

    @Override
    public String updateById(Order order) {
        orderMapper.updateById(order);
        return "redirect:/orders_admin/selectOrder";
    }

    @Override
    public String deleteById(String id) {
        orderMapper.deleteById(id);
        return "redirect:/orders_admin/selectOrder";
    }

    @Override
    public String selectPage(Integer pageNumber, Integer pageSize, String search, Model model) {
        LambdaQueryWrapper<Order> wrapper = Wrappers.<Order>lambdaQuery();
        if(StrUtil.isNotBlank(search)) { // 防止该字段在数据库中未设置
            wrapper.like(Order::getId, search);
        }
        Page<Order> orderPage = orderMapper.selectPage(new Page<>(pageNumber, pageSize), wrapper);
        model.addAttribute("data", orderPage);
        return "orders_admin";
    }

    @Override
    public String updateStatus(Integer id, String status) {
        Order order = orderMapper.selectById(id);
        order.setStatus(status);
        orderMapper.updateById(order);
        return "redirect:/orders_admin/selectOrder";
    }

    @Override
    public String comment(Comment comment) {
        comment.setTime(OrderUtil.getCurrentTimestamp());
        commentMapper.insert(comment);
        return "redirect:/orders";
    }

    @Override
    public String completeOrder(String orderID) {
        Order order = orderMapper.selectById(orderID);
        int dateDiff = DateUtils.calculateDateDiff(order.getAppointStartTime(), order.getRealAppointReturnTime());
        Car car = carMapper.selectById(order.getCarId());
        if (dateDiff > 0) {
            order.setStatus(OrderStatus.ARREARAGE.getStatus());
            order.setExtraFee(DateUtils.abs(dateDiff) * car.getDailyRent());
            orderMapper.updateById(order);
            return "redirect:/pricing?id=" + order.getId();
        } else {
            order.setStatus(OrderStatus.COMPLETED.getStatus());
            order.setRefundedFee(DateUtils.abs(dateDiff) * car.getDailyRent());
            orderMapper.updateById(order);
            return "redirect:/refunded";
        }
    }

}
