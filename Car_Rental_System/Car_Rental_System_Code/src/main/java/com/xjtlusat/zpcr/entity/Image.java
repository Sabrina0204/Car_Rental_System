package com.xjtlusat.zpcr.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("image")
public class Image {
    @TableId(value = "fileName", type = IdType.INPUT)
    String fileName;
    Integer carId;

    public Image(String fileName, Integer carId) {
        this.fileName = fileName;
        this.carId = carId;
    }

    public Image() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }
}
