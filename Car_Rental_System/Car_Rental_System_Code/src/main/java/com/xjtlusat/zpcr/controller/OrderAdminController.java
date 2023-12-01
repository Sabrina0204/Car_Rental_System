package com.xjtlusat.zpcr.controller;

import com.xjtlusat.zpcr.entity.Order;
import com.xjtlusat.zpcr.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("orders_admin")
public class OrderAdminController {

    @Autowired
    OrderService orderService;

    @RequestMapping
    public ModelAndView orderAdminPage() {
        return new ModelAndView("redirect:/orders_admin/selectOrder");
    }

    @RequestMapping("addOrder")
    public String save(Order order) {
        return orderService.insert(order);
    }

    @RequestMapping("selectOrder")
    public String findPage(@RequestParam(defaultValue = "1") Integer pageNumber,
                           @RequestParam(defaultValue = "10") Integer pageSize,
                           @RequestParam(defaultValue = "") String search,
                           Model model) {
        return orderService.selectPage(pageNumber, pageSize, search, model);
    }

    @RequestMapping("editOrder")
    public String edit(Order order) {
        return orderService.updateById(order);
    }

    @RequestMapping("delete")
    public String delete(@RequestParam String id) {
        return orderService.deleteById(id);
    }

    @RequestMapping("pickup")
    public String pickup(@RequestParam String id) {
        return orderService.pickup(id);
    }

    @RequestMapping("return")
    public String ret(@RequestParam String id) {
        return orderService.ret(id);
    }

}
