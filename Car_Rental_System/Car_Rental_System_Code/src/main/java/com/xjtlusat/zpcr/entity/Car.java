package com.xjtlusat.zpcr.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("car")
public class Car {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String brand;
    private String type;
    private String introduction;
    private Integer numberOfSeats;
    private Integer numberOfDoors;
    private Double luggageCapacity;
    private String fuelType;
    private String transmissionType;
    private String licensePlateNumber;
    private Double dailyRent;

    public Car() {
    }

    public Car(Integer id, String brand, String type, String introduction, Integer numberOfSeats, Integer numberOfDoors, Double luggageCapacity, String fuelType, String transmissionType, String licensePlateNumber, Double dailyRent) {
        this.id = id;
        this.brand = brand;
        this.type = type;
        this.introduction = introduction;
        this.numberOfSeats = numberOfSeats;
        this.numberOfDoors = numberOfDoors;
        this.luggageCapacity = luggageCapacity;
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
        this.licensePlateNumber = licensePlateNumber;
        this.dailyRent = dailyRent;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Integer getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(Integer numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public Double getLuggageCapacity() {
        return luggageCapacity;
    }

    public void setLuggageCapacity(Double luggageCapacity) {
        this.luggageCapacity = luggageCapacity;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public Double getDailyRent() {
        return dailyRent;
    }

    public void setDailyRent(Double dailyRent) {
        this.dailyRent = dailyRent;
    }
}
