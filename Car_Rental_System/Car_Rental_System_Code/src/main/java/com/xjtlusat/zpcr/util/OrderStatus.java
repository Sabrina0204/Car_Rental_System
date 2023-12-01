package com.xjtlusat.zpcr.util;

public enum OrderStatus {
    APPOINTED("Appointed"),
    RESERVED("Reserved"),
    PAID("paid"),
    IN_PROGRESS("In Progress"),
    RETURNED("Returned"),
    ARREARAGE("arrearage"),
    TIMEOUT("Time-out"),
    COMPLETED("Completed");

    private String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static OrderStatus getOrderStatus(String status) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.getStatus().equals(status)) {
                return orderStatus;
            }
        }
        return null;
    }

    public static String getOrderStatusString(OrderStatus orderStatus) {
        return orderStatus.getStatus();
    }

}
