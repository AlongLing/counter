package com.gudy.counter.controller;

import com.gudy.counter.bean.res.Account;
import com.gudy.counter.bean.res.CaptchaRes;
import com.gudy.counter.bean.res.CounterRes;
import com.gudy.counter.cache.CacheType;
import com.gudy.counter.cache.RedisStringCache;
import com.gudy.counter.service.AccountService;
import com.gudy.counter.util.Captcha;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import thirdpart.uuid.GudyUuid;

import static com.gudy.counter.bean.res.CounterRes.*;

/**
 * @author lqs
 * @version 1.0.0
 * @date 2022年12月11日 12:51:22
 * @packageName com.gudy.counter.controller
 * @className LoginController
 * @describe TODO
 */
@RestController
//@CrossOrigin
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @RequestMapping("/captcha")
    public CounterRes captcha() throws Exception {
        System.out.println("生成验证码");
        //1.生成验证码 120 40 4个字符 噪点+线条
        Captcha captcha = new Captcha(120, 40,
                4, 10);

        //2.将验证码<ID,验证码数值>放入缓存
        String uuid = String.valueOf(GudyUuid.getInstance().getUUID());
        RedisStringCache.cache(uuid, captcha.getCode(),
                CacheType.CAPTCHA);

        //3.使用base64编码图片，并返回给前台
        //uuid,base64
        CaptchaRes res = new CaptchaRes(uuid, captcha.getBase64ByteStr());
        return new CounterRes(res);
    }

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/userlogin")
    public CounterRes login(@RequestParam long uid,
                            @RequestParam String password,
                            @RequestParam String captcha,
                            @RequestParam String captchaId) throws Exception {

        Account account = accountService.login(uid, password,
                captcha, captchaId);

        log.info("account = " + account);
        if (account == null) {
            return new CounterRes(FAIL,
                    "用户名密码/验证码错误，登录失败", null);
        } else {
            return new CounterRes(account);
        }
    }

    @RequestMapping("/loginfail")
    public CounterRes loginFail() {
        return new CounterRes(RELOGIN, "请重新登陆", null);
    }

    /**
     * 退出登录
     * @param token
     * @return
     */
    @RequestMapping("/logout")
    public CounterRes logout(@RequestParam String token) {
        accountService.logout(token);
        return new CounterRes(SUCCESS, "退出成功", null);
    }

    /**
     * 更新用户密码
     * @param uid
     * @param oldpwd
     * @param newpwd
     * @return
     */
    @RequestMapping("pwdupdate")
    public CounterRes pwdUpdate(@RequestParam int uid,
                                @RequestParam String oldpwd,
                                @RequestParam String newpwd){
        boolean res = accountService.updatePwd(uid, oldpwd, newpwd);
        log.info("用户："+uid+",旧密码："+oldpwd+",新密码："+newpwd+",是否更新成功："+res);
        if(res){
            return new CounterRes(SUCCESS,"密码更新成功",null);
        }else {
            return new CounterRes(FAIL,"密码更新失败",null);
        }

    }

}
