package com.zzg.baseandroid.util;

import java.util.HashMap;
import java.util.Map;


public class JavaEnumUtils {

    private static Map<String, Object> cache = new HashMap<String, Object>();

    public static void put(String key, Object value) {
        cache.put(key, value);
    }

    public static Object get(String key) {
        return cache.get(key);
    }
}
