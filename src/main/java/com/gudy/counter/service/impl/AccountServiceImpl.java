package com.gudy.counter.service.impl;

import com.gudy.counter.bean.res.Account;
import com.gudy.counter.cache.CacheType;
import com.gudy.counter.cache.RedisStringCache;
import com.gudy.counter.service.AccountService;
import com.gudy.counter.util.DbUtil;
import com.gudy.counter.util.JsonUtil;
import com.gudy.counter.util.TimeformatUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import thirdpart.uuid.GudyUuid;

import java.util.Date;

/**
 * @author lqs
 * @version 1.0.0
 * @date 2022年12月17日 12:21:49
 * @packageName com.gudy.counter.service.impl
 * @className AccountServiceImpl
 * @describe TODO
 */
@Component
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Override
    public Account login(long uid, String password, String captcha, String captchaId) throws Exception {
        log.info("uid = " + uid + ", password = " + password + ", captcha = " + captcha + ", captchaId = " + captchaId);
        //1.入参的合法性校验
        if (StringUtils.isAllBlank(password, captcha, captchaId)) {
            return null;
        }
        //2.校验缓存验证码
        String captchaCache = RedisStringCache.get(captchaId, CacheType.CAPTCHA);
        if (StringUtils.isEmpty(captchaCache)) {
            return null;
        } else if (!StringUtils.equalsAnyIgnoreCase(captcha, captchaCache)) {
            return null;
        }
        //如果存在就先移除该缓存
        RedisStringCache.remove(captchaId, CacheType.CAPTCHA);
        //3.比对数据库用户名和密码
        Account account = DbUtil.queryAccount(uid, password);
        if (account == null) {
            return null;
        } else {
            //增加唯一ID作为身份标志
            account.setToken(String.valueOf(
                    GudyUuid.getInstance().getUUID()
            ));

            //存入缓存
            RedisStringCache.cache(String.valueOf(account.getToken()), JsonUtil.toJson(account), CacheType.ACCOUNT);

            //更新登录时间
            Date date = new Date();
            DbUtil.updateLoginTime(uid, TimeformatUtil.yyyyMMdd(date), TimeformatUtil.hhMMss(date));
            return account;
        }
    }

    /**
     * 判断缓存中是否已存在用户信息
     *
     * @param token
     * @return
     */
    @Override
    public boolean accountExistInCache(String token) {
        if (StringUtils.isBlank(token)) {
            return false;
        }
        //从缓存中获取数据
        String account = RedisStringCache.get(token, CacheType.ACCOUNT);
        if (account != null) {
            RedisStringCache.cache(token, account, CacheType.ACCOUNT);
            return true;
        }
        return false;
    }

    /**
     * 清除缓存登录信息
     *
     * @param token
     * @return
     */
    @Override
    public boolean logout(String token) {
        RedisStringCache.remove(token, CacheType.ACCOUNT);
        return true;
    }

    /**
     * 更新用户密码
     * @param uid
     * @param oldPwd
     * @param newPwd
     * @return
     */
    @Override
    public boolean updatePwd(long uid, String oldPwd, String newPwd) {
        int res = DbUtil.updatePwd(uid,oldPwd,newPwd);
        return res != 0;
    }
}
