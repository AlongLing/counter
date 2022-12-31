package com.gudy.counter.bean.res;

import lombok.*;

/**
 * @author lqs
 * @version 1.0.0
 * @date 2022年12月17日 12:15:12
 * @packageName com.gudy.counter.bean.res
 * @className Account
 * @describe TODO
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class Account {

    @NonNull
    private int id;

    @NonNull
    private long uid;

    @NonNull
    private String lastLoginDate;

    @NonNull
    private String lastLoginTime;

    private String token;

}
