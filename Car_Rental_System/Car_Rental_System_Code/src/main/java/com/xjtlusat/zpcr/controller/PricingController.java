package com.xjtlusat.zpcr.controller;

import com.alipay.easysdk.factory.Factory;
import com.xjtlusat.zpcr.service.PricingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class PricingController {

    @Autowired
    private PricingService pricingService;

    @RequestMapping("/pricing")
    public String pricing(@RequestParam("id") String orderID, Model model) {
        return pricingService.pricing(orderID, model);
    }

    @RequestMapping("/paymentsuccess")
    public String payNotify() {
        return "payment_success";
    }

    @RequestMapping("/refunded")
    public String refunded() {
        return "refunded";
    }

}
