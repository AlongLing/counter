package com.gudy.counter.bean.res;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lqs
 * @version 1.0.0
 * @date 2022年12月11日 12:54:10
 * @packageName com.gudy.counter.bean.res
 * @className CounterRes
 * @describe TODO
 */
@AllArgsConstructor
public class CounterRes {

    public static final int SUCCESS = 0;
    public static final int RELOGIN = 1;
    public static final int FAIL = 2;

    @Getter
    private int code;

    @Getter
    private String message;

    @Getter
    private Object data;


    public CounterRes(Object data){
        this(0,"",data);
    }

}
