package com.xjtlusat.zpcr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjtlusat.zpcr.entity.Car;
import com.xjtlusat.zpcr.entity.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService extends IService<Image> {

    String upload(Car car, String imageClassification, MultipartFile file);

    String delete(Long carID);
}
