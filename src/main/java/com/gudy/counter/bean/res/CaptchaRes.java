package com.gudy.counter.bean.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lqs
 * @version 1.0.0
 * @date 2022年12月11日 13:15:04
 * @packageName com.gudy.counter.bean.res
 * @className CaptchaRes
 * @describe TODO
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CaptchaRes {

    private String id;

    private String imageBase64;

}
