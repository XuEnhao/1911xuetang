package com.yijiuyiyiedu.xuetang.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/1 0001.
 */

public class CheckNull {

    public static boolean getBody(Object object) {
        if (object != null && !object.equals("") && !object.equals("null")) {
            if (object instanceof Collection) {
                if (((List) object).size() > 0) {
                    return true;
                } else {
                    return false;
                }
            } else if (object instanceof Map) {
                if (((Map) object).size() > 0) {
                    return true;
                } else {
                    return false;
                }
            } else if (object instanceof String) {
                if (((String) object).length() > 0) {
                    return true;
                } else {
                    return false;
                }
            } else if (object instanceof Integer) {
                if ((Integer) object != -1) {
                    return true;
                } else {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
