package com.gudy.counter.cache;

/**
 * @author lqs
 * @version 1.0.0
 * @date 2022年12月11日 13:11:27
 * @packageName com.gudy.counter.cache
 * @className CacheType
 * @describe TODO
 */
public enum CacheType {

    CAPTCHA("captcha:"),

    ACCOUNT("account:"),

    ORDER("order:"),

    TRADE("trade:"),

    POSI("posi:"),
    ;

    private String type;

    CacheType(String type) {
        this.type = type;
    }

    public String type() {
        return this.type;
    }

}
