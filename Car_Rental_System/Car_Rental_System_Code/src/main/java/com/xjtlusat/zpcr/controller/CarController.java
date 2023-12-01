package com.xjtlusat.zpcr.controller;

import com.xjtlusat.zpcr.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("cars")
public class CarController {

    @Autowired
    CarService carService;

    @RequestMapping
    public ModelAndView carsPage() {
        return new ModelAndView("redirect:cars/selectCar");
    }

    @RequestMapping("selectCar")
    public String findPage(@RequestParam(defaultValue = "1") Integer pageNumber,
                           @RequestParam(defaultValue = "9") Integer pageSize,
                           @RequestParam(defaultValue = "") String search,
                           Model model) {
        return carService.selectPageWithPic(pageNumber, pageSize, search, model);
    }


}
