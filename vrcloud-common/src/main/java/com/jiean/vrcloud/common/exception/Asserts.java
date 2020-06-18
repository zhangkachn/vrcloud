package com.jiean.vrcloud.common.exception;

import com.jiean.vrcloud.common.api.IErrorCode;

/**
 * 断言处理类，用于抛出各种API异常
 * Created by zhangkang on 2020/6/3
 */
public class Asserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}
