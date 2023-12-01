package com.xjtlusat.zpcr.service;


import org.springframework.ui.Model;

public interface PricingService {

    String pricing(String orderID, Model model);

}
