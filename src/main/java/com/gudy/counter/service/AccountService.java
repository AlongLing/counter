package com.gudy.counter.service;

import com.gudy.counter.bean.res.Account;

/**
 * @author lqs
 * @version 1.0.0
 * @date 2022年12月17日 12:21:12
 * @packageName com.gudy.counter.service
 * @className AccountService
 * @describe TODO
 */
public interface AccountService {

    /**
     * 登陆
     * @param uid
     * @param password
     * @param captcha
     * @param captchaId
     * @return
     * @throws Exception
     */
    Account login(long uid, String password,
                  String captcha, String captchaId) throws Exception;

    /**
     * 缓存中是否存在已登录信息
     * @param token
     * @return
     */
    boolean accountExistInCache(String token);


    /**
     * 退出登录
     * @param token
     * @return
     */
    boolean logout(String token);

    /**
     * 修改密码
     * @param uid
     * @param oldPwd
     * @param newPwd
     * @return
     */
    boolean updatePwd(long uid,String oldPwd,String newPwd);

}
