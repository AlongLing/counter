package com.gudy.counter.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author lqs
 * @version 1.0.0
 * @date 2022年12月11日 13:24:22
 * @packageName com.gudy.counter.config
 * @className CounterConfig
 * @describe TODO
 */
@Getter
@Component
public class CounterConfig {

    //////////////////////////会员号//////////////////////////
    @Value("${counter.id}")
    private short id;

    //////////////////////////UUID相关配置//////////////////////////
    @Value("${counter.dataCenterId}")
    private long dataCenterId;

    @Value("${counter.workerId}")
    private long workerId;
    ////////////////////////////////////////////////////

}
