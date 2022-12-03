package com.gudy.counter.counter;

import com.gudy.counter.util.DbUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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

    @Autowired
    private StringRedisTemplate template;

    @RequestMapping("/hello")
    public String hello() {
//        return "Hello World! along";
        return "" + DbUtil.getId();
    }

    @RequestMapping("/hello2")
    public String hello2(){
        template.opsForValue().set("test:Hello","World");
        return template.opsForValue().get("test:Hello");
    }

}
