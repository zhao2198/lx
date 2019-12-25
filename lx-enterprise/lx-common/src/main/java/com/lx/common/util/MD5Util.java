package com.lx.common.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MD5Util {

    public static String md5(String path) throws FileNotFoundException, IOException {
        return DigestUtils.md5Hex(new FileInputStream(path));
    }
}
