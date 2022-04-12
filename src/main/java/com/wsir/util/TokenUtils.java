package com.wsir.util;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class TokenUtils {

    public static String createToken(String role, String userId, String sign) {
        return JWT.create().withAudience(role, userId) //将role与userId保存到token里,作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2)) //设置2小时有效期
                .sign(Algorithm.HMAC512(sign)); //将sign作为密钥
    }
}
