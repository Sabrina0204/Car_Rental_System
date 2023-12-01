package com.xjtlusat.zpcr.service;

import com.xjtlusat.zpcr.entity.Car;
import org.springframework.ui.Model;

public interface CarService {

    String insert(Car car);
    String updateById(Car car);
    String deleteById(Long id);
    String selectPage(Integer pageNumber, Integer pageSize, String search, Model model);
    public String selectPageWithPic(Integer pageNumber, Integer pageSize, String search, Model model);
    String selectById(Integer id, Model model);

}
