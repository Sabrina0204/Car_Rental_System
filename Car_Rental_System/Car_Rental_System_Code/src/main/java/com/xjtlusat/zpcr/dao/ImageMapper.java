package com.xjtlusat.zpcr.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjtlusat.zpcr.entity.Image;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ImageMapper extends BaseMapper<Image> {

    @Insert(value = "insert into image values (#{fileName}, #{carID})")
    void insertCarImage(String fileName, Integer carID);

    @Delete(value = "delete from image where car_id = #{carID}")
    void deleteCarImage(Long carID);
}
