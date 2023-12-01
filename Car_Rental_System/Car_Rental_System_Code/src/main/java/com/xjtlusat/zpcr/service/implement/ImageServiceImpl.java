package com.xjtlusat.zpcr.service.implement;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjtlusat.zpcr.dao.CarMapper;
import com.xjtlusat.zpcr.dao.ImageMapper;
import com.xjtlusat.zpcr.entity.Car;
import com.xjtlusat.zpcr.entity.Image;
import com.xjtlusat.zpcr.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Service
@Slf4j
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements ImageService {

    @Autowired
    private ImageMapper imageMapper;

    @Autowired
    private CarMapper carMapper;

    // Note: You should config your image save path in application.properties
    @Value("${car.img.save.path}")
    private String imgSaveRawPath;

    public String upload(Car car, String imageClassification, MultipartFile file) {
        if(Objects.equals(imageClassification, "") || imageClassification.equals("---Select Please---")){
            imageClassification = "abs";
        }
        if (file == null){
            log.warn("uploaded car image is null");
            return "redirect:/cars_admin/selectCar";
        }
        if(!file.isEmpty()){
            String uploadPath = buildPath();
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String OriginalFilename = file.getOriginalFilename();
            String suffixName = ".jpg";
            // sample car img name: CarID_ImageClassification
            Integer carID = carMapper.getCarIDByLicensePlateNumber(car.getLicensePlateNumber());
            String filename = carID + "_" + imageClassification + suffixName;
            File localFile = new File(uploadPath+"\\"+filename);
            try {
                file.transferTo(localFile);
                imageMapper.insertCarImage(filename, carID);
                return "redirect:/cars_admin/selectCar";
            } catch (IOException e){
                log.warn("upload car image failed");
                e.printStackTrace();
                return "";
            }
        } else {
            log.warn("uploaded car image is empty");
            return "redirect:/cars_admin/selectCar";
        }
    }

    @Override
    public String delete(Long carID) {
        String removePath = buildPath();
        File folder = new File(removePath);
        File[] files = folder.listFiles();
        if (files == null) {
            log.warn("car image file is null");
            return "redirect:/cars_admin/selectCar";
        }
        for (File file : files) {
            if (file.getName().startsWith(String.valueOf(carID))) {
                boolean isSuccess = file.delete();
                if (!isSuccess) {
                    log.warn("delete car image file failed");
                }
            }
        }
        imageMapper.deleteCarImage(carID);
        return "redirect:/cars_admin/selectCar";
    }

    private String buildPath() {
        return System.getProperty("user.dir") + imgSaveRawPath.replace("/", "\\");
    }

}
