package com.xjtlusat.zpcr.controller;

import com.xjtlusat.zpcr.entity.Order;
import com.xjtlusat.zpcr.service.CarService;
import com.xjtlusat.zpcr.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("car_details")
public class CarDetailController {

    @Autowired
    CarService carService;

    @Autowired
    OrderService orderService;

    @RequestMapping
    public String carDetailPage(@RequestParam Integer carId, Model model) {
        return carService.selectById(carId, model);
    }

    @RequestMapping("appoint")
    public String orderGen (Order order) {
        return orderService.generate(order);

    }

}
