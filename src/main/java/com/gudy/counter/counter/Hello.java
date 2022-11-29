package com.gudy.counter.counter;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lqs
 * @version 1.0.0
 * @date 2022年11月27日 23:26:27
 * @packageName com.gudy.counter.counter
 * @className Hello
 * @describe TODO
 */
@RestController
public class Hello {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World! along";
    }

}
