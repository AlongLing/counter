package com.gudy.counter;

import com.gudy.counter.config.CounterConfig;
import com.gudy.counter.util.DbUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import thirdpart.uuid.GudyUuid;

@SpringBootApplication
public class CounterApplication {

    @Autowired
    private DbUtil dbUtil;

    @Autowired
    private CounterConfig counterConfig;

    @PostConstruct
    private void init(){
        GudyUuid.getInstance().init(counterConfig.getDataCenterId(),counterConfig.getWorkerId());
    }

    public static void main(String[] args) {
        SpringApplication.run(CounterApplication.class, args);
    }

}
