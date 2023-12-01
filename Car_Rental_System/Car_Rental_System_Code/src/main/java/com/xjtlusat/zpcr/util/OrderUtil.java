package com.xjtlusat.zpcr.util;

import com.xjtlusat.zpcr.entity.Order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class OrderUtil {

    public static String get16UUID(){

// 1.开头两位，标识业务代码或机器代码(可变参数)

        String machineId = "01";

// 2.中间四位整数，标识日期

        SimpleDateFormat sdf = new SimpleDateFormat("MMdd");

        String dayTime = sdf.format(new Date());

// 3.生成uuid的hashCode值

        int hashCode = UUID.randomUUID().toString().hashCode();

// 4.可能为负数

        if(hashCode < 0){

            hashCode = -hashCode;

        }

// 5.算法处理: 0-代表前面补充0; 10-代表长度为10; d-代表参数为正数型

        String value = machineId + dayTime + String.format("%010d", hashCode);

        return value;

    }

    public static java.sql.Date getCurrentTimestamp() {
        Date date = new Date();
        return new java.sql.Date(date.getTime());
    }

    //判断订单状态是否合法
    public static boolean isValidStatus(Order order) {
        String validStatus="";
        if(order.getPaymentId()==null){
            validStatus = "Appointed";
        } else if (order.getOrderStartTime()==null) {
            validStatus = "Reserved";
        } else if (getCurrentTimestamp().before(order.getAppointReturnTime())) {
            validStatus = "InProgressed";
        } else if (order.getOrderFinishTime()==null) {
            validStatus = "Completed";
        } else{
            validStatus = "Returned";
        }
        return validStatus.equals(order.getStatus());
    }
}
