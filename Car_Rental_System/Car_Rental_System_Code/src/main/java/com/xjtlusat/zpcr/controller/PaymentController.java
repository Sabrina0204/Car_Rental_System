package com.xjtlusat.zpcr.controller;

import cn.hutool.core.util.IdUtil;
import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.xjtlusat.zpcr.controller.dto.Alipay;
import com.xjtlusat.zpcr.entity.Order;
import com.xjtlusat.zpcr.service.OrderService;
import com.xjtlusat.zpcr.util.OrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private OrderService orderService;

    @Value("${alipay.notifyUrl}")
    private String baseReturnURL;

    @RequestMapping("/pay/{orderID}")
    public String pay(@PathVariable String orderID) {
        AlipayTradePagePayResponse response = null;
        Alipay alipay = new Alipay();
        Order order = orderService.getOrderByID(orderID);
        alipay.setSubject(orderID);
        alipay.setOutTraceNo(IdUtil.getSnowflake().nextIdStr());
        if (order.getStatus().equals(OrderStatus.ARREARAGE.getStatus())) {
            alipay.setTotalAmount(order.getExtraFee().toString());
        } else {
            alipay.setTotalAmount(order.getPrice().toString());
        }
        try {
            // Note: replace return url ip with cloud server ip
            response = Factory.Payment.Page().pay(alipay.getSubject(), alipay.getOutTraceNo(), alipay.getTotalAmount(), baseReturnURL + "/paymentsuccess");
        } catch (Exception e) {
            log.warn("Alipay payment failed, err: ", e);
            e.printStackTrace();
        }
        if (response != null) {
            return response.getBody();
        }
        return "error";
    }

    @PostMapping("/reply")
    public void payNotify(HttpServletRequest request) throws Exception {
        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
            }
            if (Factory.Payment.Common().verifyNotify(params)) {
                log.info("subject: " + params.get("subject"));
                log.info("trade_status: " + params.get("trade_status"));
                log.info("trade_no: " + params.get("trade_no"));
                log.info("out_trade_no: " + params.get("out_trade_no"));
                log.info("total_amount: " + params.get("total_amount"));
                log.info("buyer_id: " + params.get("buyer_id"));
                log.info("gmt_payment: " + params.get("gmt_payment"));
                log.info("buyer_pay_amount: " + params.get("buyer_pay_amount"));
                String orderID = params.get("subject");
                if (orderService.judgeStatus(orderID, OrderStatus.ARREARAGE.getStatus())) {
                    orderService.updateArrearagePaymentID(orderID, params.get("trade_no"));
                } else {
                    orderService.updatePaymentID(orderID, params.get("trade_no"));
                }
                orderService.updateStatus(orderID, OrderStatus.PAID.getStatus());
                orderService.updatePaymentMethod(orderID, "Alipay");
            }
        }
    }

}
