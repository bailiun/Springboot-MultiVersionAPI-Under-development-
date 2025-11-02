package org.bailiun.multipleversionscoexist.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class B {
    public static Integer[] CharacterToArray(String str) {
        String[] arr = str.substring(1, str.length() - 1).split(",");
        Integer[] arr1 = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr1[i] = Integer.valueOf(arr[i].trim());
        }
        return arr1;
    }

    public static long disposeBookId(String bookId) {
        Pattern pattern = Pattern.compile("([a-zA-Z]+)(\\d+)");
        Matcher matcher = pattern.matcher(bookId);
        return Long.parseLong(matcher.group(2));
    }

    public static void saveLong(Integer[] arr, long index) {
        arr[(int) (index / 32)] = arr[(int) (index / 32)] | (1 << index % 32);
    }

    public static void deleteLong(Integer[] arr, long index) {
        arr[(int) (index / 32)] = arr[(int) (index / 32)] & ~(1 << index % 32);
    }

    public static boolean notNullLong(Integer[] arr, Integer index) {
        return (arr[index / 32] & 1 << index % 32) != 0;
    }

    public static List<Integer> getBitmapList(Integer index) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 32; i++) {
            if ((index & 1 << i) != 0) {
                list.add(i);
            }
        }
        return list;
    }

}
