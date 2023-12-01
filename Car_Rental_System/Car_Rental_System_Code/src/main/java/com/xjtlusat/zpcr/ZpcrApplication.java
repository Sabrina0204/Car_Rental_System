package com.xjtlusat.zpcr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({com.xjtlusat.zpcr.common.AlipayConfig.class})
public class ZpcrApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZpcrApplication.class, args);
    }

}
