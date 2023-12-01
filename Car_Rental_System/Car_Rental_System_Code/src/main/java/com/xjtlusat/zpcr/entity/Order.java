package com.xjtlusat.zpcr.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Date;
import java.sql.Timestamp;

@TableName("`order`")
public class Order {

    @TableId(value = "id", type = IdType.AUTO)
    private String  id;
    private Integer userId;
    private Integer carId;
    private String status;
    private String description;
    private Date appointStartTime;
    private Date appointReturnTime;
    private Date realAppointStartTime;
    private Date realAppointReturnTime;
    private Date orderStartTime;
    private Date orderFinishTime;
    private String paymentMethod;
    private String paymentId;
    private Double price;
    private String remark;
    private Double extraFee;
    private String arrearagePaymentId;
    private Double refundedFee;

    public Order() {
    }

    public Order(String id, Integer userId, Integer carId, String status, String description, Date appointStartTime, Date appointReturnTime, Date realAppointStartTime, Date realAppointReturnTime, Date orderStartTime, Date orderFinishTime, String paymentMethod, String paymentId, Double price, String remark, Double extraFee, String arrearagePaymentId, Double refundedFee) {
        this.id = id;
        this.userId = userId;
        this.carId = carId;
        this.status = status;
        this.description = description;
        this.appointStartTime = appointStartTime;
        this.appointReturnTime = appointReturnTime;
        this.realAppointStartTime = realAppointStartTime;
        this.realAppointReturnTime = realAppointReturnTime;
        this.orderStartTime = orderStartTime;
        this.orderFinishTime = orderFinishTime;
        this.paymentMethod = paymentMethod;
        this.paymentId = paymentId;
        this.price = price;
        this.remark = remark;
        this.extraFee = extraFee;
        this.arrearagePaymentId = arrearagePaymentId;
        this.refundedFee = refundedFee;
    }

    public Double getRefundedFee() {
        return refundedFee;
    }

    public void setRefundedFee(Double refundedFee) {
        this.refundedFee = refundedFee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getAppointStartTime() {
        return appointStartTime;
    }

    public void setAppointStartTime(Date appointStartTime) {
        this.appointStartTime = appointStartTime;
    }

    public Date getAppointReturnTime() {
        return appointReturnTime;
    }

    public void setAppointReturnTime(Date appointReturnTime) {
        this.appointReturnTime = appointReturnTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Date getOrderStartTime() {
        return orderStartTime;
    }

    public void setOrderStartTime(Date orderStartTime) {
        this.orderStartTime = orderStartTime;
    }

    public Date getOrderFinishTime() {
        return orderFinishTime;
    }

    public void setOrderFinishTime(Date orderFinishTime) {
        this.orderFinishTime = orderFinishTime;
    }

    public Date getRealAppointStartTime() {
        return realAppointStartTime;
    }

    public void setRealAppointStartTime(Date real_appoint_startTime) {
        this.realAppointStartTime = real_appoint_startTime;
    }

    public Date getRealAppointReturnTime() {
        return realAppointReturnTime;
    }

    public void setRealAppointReturnTime(Date realAppointReturnTime) {
        this.realAppointReturnTime = realAppointReturnTime;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public Double getExtraFee() {
        return extraFee;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setExtraFee(Double extraFee) {
        this.extraFee = extraFee;
    }

    public int getDays() {
        return (int) ((this.appointReturnTime.getTime() - this.appointStartTime.getTime()) / (1000 * 60 * 60 * 24));
    }

    public String getArrearagePaymentId() {
        return arrearagePaymentId;
    }

    public void setArrearagePaymentId(String arrearagePaymentId) {
        this.arrearagePaymentId = arrearagePaymentId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", userId=" + userId +
                ", carId=" + carId +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", appointStartTime=" + appointStartTime +
                ", appointReturnTime=" + appointReturnTime +
                ", realAppointStartTime=" + realAppointStartTime +
                ", realAppointReturnTime=" + realAppointReturnTime +
                ", orderStartTime=" + orderStartTime +
                ", orderFinishTime=" + orderFinishTime +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentId='" + paymentId + '\'' +
                ", price=" + price +
                ", remark='" + remark + '\'' +
                ", extraFee=" + extraFee +
                '}';
    }
}
