package com.xjtlusat.zpcr.controller;

import com.xjtlusat.zpcr.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping
    public String selectOrderByUserId(@RequestParam(defaultValue = "1") Integer pageNumber,
                                      @RequestParam(defaultValue = "10") Integer pageSize,
                                      Model model,
                                      HttpSession session) {
        return orderService.getOrdersByUserId(pageNumber, pageSize, session, model);
    }

    @RequestMapping("/complete/{id}")
    public String completeOrder(@PathVariable String id) {
        return orderService.completeOrder(id);
    }

}
