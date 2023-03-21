package com.prc.transfer.utils;

import java.util.UUID;

/**
 * UUID工具类
 *
 * @author huangxinrui
 * @since 1.0.0
 */
public class UuidUtil {
    public static String uuid() {
            return UUID.randomUUID().toString().replace("-", "");
        }

}
