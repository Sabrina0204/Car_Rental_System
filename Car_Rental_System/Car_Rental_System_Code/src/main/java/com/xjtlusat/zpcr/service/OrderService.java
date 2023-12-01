package com.xjtlusat.zpcr.service;

import com.xjtlusat.zpcr.entity.Comment;
import com.xjtlusat.zpcr.entity.Order;
import com.xjtlusat.zpcr.entity.User;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface OrderService {
    String getOrdersByUserId(Integer pageNumber, Integer pageSize, HttpSession session, Model model);
    String generate(Order order);
    String pickup(String orderId);
    String ret(String orderId);
    String insert(Order order);
    void updateStatus(String orderID, String status);
    boolean judgeStatus(String orderID, String status);
    void updatePaymentID(String orderID, String paymentID);
    void updateArrearagePaymentID(String orderID, String arrearagePaymentID);
    void updatePaymentMethod(String orderID, String paymentMethod);
    Order getOrderByID(String orderID);
    String updateById(Order order);
    String deleteById(String id);
    String selectPage(Integer pageNumber, Integer pageSize, String search, Model model);
    String updateStatus(Integer id, String status);
    String comment(Comment comment);
    String completeOrder(String orderID);
}
