package org.bailiun.multipleversionscoexist.utils;

import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class L {

    public static <K, V> Map<K, V> conversionsMap(List<Map<String, Object>> list, String n1, String n2) {
        Map<K, V> map = new HashMap<>();
        for (Map<String, Object> i : list) {
            map.put((K) i.get(n1), (V) i.get(n2));
        }
        return map;
    }


    public static Map<String, Object> getClassAllByMap(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        PropertyDescriptor pd;
        Map<String, Object> map = new HashMap<>();
        for (Field f : fields) {
            f.setAccessible(true);
            pd = BeanUtils.getPropertyDescriptor(obj.getClass(), f.getName());
            try {
                if (pd != null) {
                    map.put(f.getName(), pd.getReadMethod().invoke(obj));
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        return map;
    }


    public static List<List<Object>> getClassAllByList(Object t) {
        Field[] fields = t.getClass().getDeclaredFields();
        List<List<Object>> lists = new ArrayList<>();
        List<Object> list1 = new ArrayList<>();
        List<Object> list2 = new ArrayList<>();
        PropertyDescriptor pd;
        for (Field f : fields) {
            f.setAccessible(true);
            pd = BeanUtils.getPropertyDescriptor(t.getClass(), f.getName());
            try {
                if (pd != null) {
                    list1.add(f.getName());
                    list2.add(pd.getReadMethod().invoke(t));
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        lists.add(list1);
        lists.add(list2);
        return lists;
    }


    public static boolean objectIsNull(Object object, Integer objectNullSystemOutFlag) {
        if (object == null) {
            return true;
        }

        Field[] fields = object.getClass().getDeclaredFields();

        boolean flag = true;
        for (Field field : fields) {

            field.setAccessible(true);
            Object fieldValue = null;
            String fieldName;
            try {

                fieldValue = field.get(object);

                Type fieldType = field.getGenericType();
   
                fieldName = field.getName();
     
                if (objectNullSystemOutFlag == 1) {
                    System.out.println("属性类型：" + fieldType + ",属性名：" + fieldName + ",属性值：" + fieldValue);
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
 
            if (fieldValue != null) {
                flag = false;
                break;
            }
        }
        return flag;
    }

 
    public static boolean objectIsNull(Object object) {
        if (object == null) {
            return true;
        }
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldValue = null;
            try {
                fieldValue = field.get(object);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
            if (fieldValue != null) {
                return false;
            }
        }
        return true;
    }
}
