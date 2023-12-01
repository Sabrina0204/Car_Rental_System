package com.xjtlusat.zpcr.controller;

import com.xjtlusat.zpcr.entity.Car;
import com.xjtlusat.zpcr.service.CarService;
import com.xjtlusat.zpcr.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("cars_admin")
public class CarAdminController {

    @Autowired
    CarService carService;

    @Autowired
    ImageService imageService;

    @RequestMapping
    public ModelAndView carAdminPage() {
        return new ModelAndView("redirect:cars_admin/selectCar");
    }

    @RequestMapping("addCar")
    public String save(Car car, String imageClassification, MultipartFile pic) {
        carService.insert(car);
        return imageService.upload(car, imageClassification, pic);
    }

    @RequestMapping("selectCar")
    public String findPage(@RequestParam(defaultValue = "1") Integer pageNumber,
                           @RequestParam(defaultValue = "10") Integer pageSize,
                           @RequestParam(defaultValue = "") String search,
                           Model model) {
        return carService.selectPage(pageNumber, pageSize, search, model);
    }

    @RequestMapping("editCar")
    public String edit(Car car, String imageClassification, MultipartFile pic) {
        carService.updateById(car);
        return imageService.upload(car, imageClassification, pic);
    }

    @RequestMapping("delete")
    public String delete(@RequestParam Long id) {
        carService.deleteById(id);
        return imageService.delete(id);
    }

}
