package com.gudy.counter.util;

import com.google.common.collect.ImmutableMap;
import com.gudy.counter.bean.res.Account;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author lqs
 * @version 1.0.0
 * @date 2022年12月03日 11:06:28
 * @packageName com.gudy.counter.util
 * @className DbUtil
 * @describe TODO
 */
@Component
public class DbUtil {

    /**
     * 如何在静态调用的工具类中注入Spring管理对象
     */
    private static DbUtil dbUtil = null;

    private DbUtil() {
    }

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    private SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    private void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    /**
     * @PostConstruct 将sqlSessionTemplate与DbUtil进行绑定
     * 即在调用DbUtil类时同步完成sqlSessionTemplate的初始化
     */
    @PostConstruct
    private void init() {
        dbUtil = new DbUtil();
        dbUtil.setSqlSessionTemplate(this.sqlSessionTemplate);
    }

    /**
     * 可直接DbUtil.getId()调用
     */
    public static long getId() {
        Long res = dbUtil.getSqlSessionTemplate().selectOne(
                "testMapper.queryBalance"
        );
        if (res == null) {
            return -1;
        } else {
            return res;
        }
    }

    ////////////////////////////////身份认证///////////////////////////////////

    /**
     * 根据账号和密码查询用户
     *
     * @param uid
     * @param password
     * @return
     */
    public static Account queryAccount(long uid, String password) {
        //Guava写法：ImmutableMap.of方法入参最多只能有5对,如果添加的数据超过5对,需要改用builder方法
        return dbUtil.getSqlSessionTemplate().selectOne(
                "userMapper.queryAccount",
                ImmutableMap.of("Uid", uid, "Password", password)
        );
    }

    /**
     * 更新最近登录时间
     *
     * @param uid
     * @param nowDate
     * @param nowTime
     */
    public static void updateLoginTime(long uid, String nowDate, String nowTime) {
        dbUtil.getSqlSessionTemplate().update(
                "userMapper.updateAccountLoginTime",
                ImmutableMap.of(
                        "Uid", uid,
                        "ModifyDate", nowDate,
                        "ModifyTime", nowTime
                )
        );
    }

    /**
     * 更新用户密码
     *
     * @param uid
     * @param oldPwd
     * @param newPwd
     * @return
     */
    public static int updatePwd(long uid, String oldPwd, String newPwd) {
        return dbUtil.getSqlSessionTemplate().update(
                "userMapper.updatePwd",
                ImmutableMap.of(
                        "Uid", uid,
                        "NewPwd", newPwd,
                        "OldPwd", oldPwd
                )
        );
    }

    ////////////////////////////////资金类///////////////////////////////////

}
