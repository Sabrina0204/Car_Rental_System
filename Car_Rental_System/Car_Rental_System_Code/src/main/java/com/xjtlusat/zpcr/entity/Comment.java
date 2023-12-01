package com.xjtlusat.zpcr.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Date;
import java.sql.Timestamp;

@TableName("comment")
public class Comment {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String content;
    private Date time;
    private Integer userId;
    private Integer carId;

    public Comment() {
    }

    public Comment(Integer id, String content, Date time, Integer userId, Integer carId) {
        this.id = id;
        this.content = content;
        this.time = time;
        this.userId = userId;
        this.carId = carId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", userId=" + userId +
                ", carId=" + carId +
                '}';
    }
}
