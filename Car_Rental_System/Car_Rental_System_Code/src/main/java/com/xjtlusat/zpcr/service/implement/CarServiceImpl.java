package com.xjtlusat.zpcr.service.implement;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xjtlusat.zpcr.dao.CarMapper;
import com.xjtlusat.zpcr.dao.CommentMapper;
import com.xjtlusat.zpcr.dao.ImageMapper;
import com.xjtlusat.zpcr.entity.Car;
import com.xjtlusat.zpcr.entity.Comment;
import com.xjtlusat.zpcr.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.File;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarMapper carMapper;

    @Autowired
    CommentMapper commentMapper;

    @Override
    public String insert(Car car) {
        carMapper.insert(car);
        return "redirect:/cars_admin/selectCar";
    }

    @Override
    public String updateById(Car car) {
        carMapper.updateById(car);
        return "redirect:/cars_admin/selectCar";
    }

    @Override
    public String deleteById(Long id) {
        carMapper.deleteById(id);
        return "redirect:/cars_admin/selectCar";
    }

    @Override
    public String selectPage(Integer pageNumber, Integer pageSize, String search, Model model) {
        LambdaQueryWrapper<Car> wrapper = Wrappers.<Car>lambdaQuery();
        if(StrUtil.isNotBlank(search)) { // 防止该字段在数据库中未设置
            search = "%" + search + "%";
            wrapper.like(Car::getType, search);
        }
        Page<Car> carPage = carMapper.selectPage(new Page<>(pageNumber, pageSize), wrapper);
        model.addAttribute("data", carPage);
        return "cars_admin";
    }

    @Override
    public String selectPageWithPic(Integer pageNumber, Integer pageSize, String search, Model model) {
        LambdaQueryWrapper<Car> wrapper = Wrappers.<Car>lambdaQuery();
        if(StrUtil.isNotBlank(search)) { // 防止该字段在数据库中未设置
            search = "%" + search + "%";
            wrapper.like(Car::getType, search)
                    .or()
                    .like(Car::getBrand, search)
                    .or()
                    .like(Car::getIntroduction, search)
                    .or()
                    .like(Car::getTransmissionType, search);
        }
        Page<Car> carPage = carMapper.selectPage(new Page<>(pageNumber, pageSize), wrapper);
        model.addAttribute("data", carPage);
        return "cars";
    }

    @Override
    public String selectById(Integer id, Model model) {
        LambdaQueryWrapper<Comment> wrapper = Wrappers.<Comment>lambdaQuery();
        wrapper.eq(Comment::getCarId, id);
        List<Comment> comments = commentMapper.selectList(wrapper);
        model.addAttribute("comments", comments);
        model.addAttribute("comments_size", comments.size());
        model.addAttribute("car", carMapper.selectById(id));
        return "car_details";
    }

}
