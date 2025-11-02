package org.bailiun.multipleversionscoexist.utils;

import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class M {
    public static String ExtractClassFields(Object t) {
        Field[] fields = t.getClass().getDeclaredFields();
        String list = "";
        for (Field f : fields) {
            f.setAccessible(true);
            list = list.concat(f.getName()).concat(",");
        }
        return list.substring(0, list.length() - 1);
    }


    public static String ExtractMapToConditionA(Map<String, String> map) {
        String run = "";
        for (String m : map.keySet()) {
            run = run.concat(m + " = " + map.get(m) + " and ");
        }
        return run.substring(0, run.length() - 5);
    }


    public static String ExtractMapToConditionO(Map<String, String> map) {
        String run = "";
        for (String m : map.keySet()) {
            run = run.concat(m + " = " + map.get(m) + " or ");
        }
        return run.substring(0, run.length() - 5);
    }


    public static String[] getClassAllByString(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        PropertyDescriptor pd;
        String[] arr = new String[2];
        String t1 = "";
        String t2 = "";
        for (Field f : fields) {
            f.setAccessible(true);
            pd = BeanUtils.getPropertyDescriptor(obj.getClass(), f.getName());
            try {
                if (pd != null) {
                    t1 = t1.concat(f.getName()).concat(",");
                    if (pd.getReadMethod().invoke(obj) == null) {
                        t2 = t2.concat("'").concat("',");
                    } else {
                        t2 = t2.concat("'" + pd.getReadMethod().invoke(obj).toString()).concat("',");
                    }
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        arr[0] = t1.substring(0, t1.length() - 1);
        arr[1] = t2.substring(0, t2.length() - 1);
        return arr;
    }


    public static String generateNextCode(String currentCode) {
        if (currentCode == null || currentCode.isEmpty()) return null;
        String numberPart = currentCode.replaceAll(".*?(\\d+)$", "$1");
        int numberLength = numberPart.length();
        String prefix = currentCode.substring(0, currentCode.length() - numberLength);
        long nextNumber = Long.parseLong(numberPart) + 1;
        String paddedNumber = String.format("%0" + numberLength + "d", nextNumber);
        return prefix + paddedNumber;
    }


    public static Map<String, Object> ListMapToMap(List<Map<String, Object>> list, String keyField, String valField) {
        Map<String, Object> result = new HashMap<>();
        for (Map<String, Object> item : list) {
            if (item.containsKey(keyField)) {
                String key = String.valueOf(item.get(keyField));
                result.put(key, item.get(valField));
            }
        }
        return result;
    }


    public static Map<String, Object> ListMapToMap2(List<Map<String, Object>> list, String keyField, String v) {
        Map<String, Object> result = new HashMap<>();
        for (Map<String, Object> item : list) {
            if (item.containsKey(keyField)) {
                String key = String.valueOf(item.get(keyField));
                String value = String.valueOf(item.get(v));
                result.put(key, value);
            }
        }
        return result;
    }
}
