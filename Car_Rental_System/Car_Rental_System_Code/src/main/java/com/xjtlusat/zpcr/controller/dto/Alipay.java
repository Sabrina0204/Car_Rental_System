package com.xjtlusat.zpcr.controller.dto;


public class Alipay {

    private String subject;
    private String outTraceNo;
    private String totalAmount;

    public Alipay() {
    }

    public Alipay(String subject, String outTraceNo, String totalAmount) {
        this.subject = subject;
        this.outTraceNo = outTraceNo;
        this.totalAmount = totalAmount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getOutTraceNo() {
        return outTraceNo;
    }

    public void setOutTraceNo(String outTraceNo) {
        this.outTraceNo = outTraceNo;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Alipay{" +
                "subject='" + subject + '\'' +
                ", outTraceNo='" + outTraceNo + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                '}';
    }
}
