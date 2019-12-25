
package com.lx.common.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

public class TokenUtils {

    public static String createToken(String username, String password) {
        return Base64.encodeBase64URLSafeString((username + ";" + password).getBytes());
    }

    public static String createToken(String username, String password, Integer clientType) {

        if (clientType == null) {
            clientType = 0;
        }
        return Base64.encodeBase64URLSafeString((username + ";" + password + ";" + clientType).getBytes());
    }

    public static String[] decodeToken(String session) {
        String token = new String(Base64.decodeBase64(session));
        return StringUtils.split(token, ";");
    }

}
