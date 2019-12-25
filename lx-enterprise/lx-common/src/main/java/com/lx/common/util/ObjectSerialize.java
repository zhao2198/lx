
package com.lx.common.util;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class ObjectSerialize implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static String serialization(Object obj) {
        String result = StringUtils.EMPTY;
        ObjectOutputStream oos = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            result = bos.toString("ISO-8859-1");
            result = URLEncoder.encode(result, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                    oos = null;
                }
            } catch (Exception e) {
            }

        }
        return result;

    }

    public static Object deserialization(String serial) {
        if (StringUtils.isBlank(serial)) {
            return null;
        }
        ObjectInputStream ois = null;
        Object result = null;
        try {
            String str = URLDecoder.decode(serial, "UTF-8");
            ByteArrayInputStream bis = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));
            ois = new ObjectInputStream(bis);
            result = ois.readObject();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                    ois = null;
                }
            } catch (Exception e) {
            }
        }
        return result;
    }

}
