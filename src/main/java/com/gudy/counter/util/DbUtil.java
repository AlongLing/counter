package com.gudy.counter.util;

import jakarta.annotation.PostConstruct;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

}
