package org.bailiun.multipleversionscoexist.utils;


import java.util.HashMap;
import java.util.Map;


public class R {
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Map<String, Object> getExtra() {
        return extra;
    }

    public void setExtra(Map<String, Object> extra) {
        this.extra = extra;
    }

    private int code;
    private String msg;
    private String type;
    private Object data;
    private Map<String, Object> extra; // 扩展字段

    public R() {
    }

    private R(int code, String msg, String type, Object data) {
        this.code = code;
        this.msg = msg;
        this.type = type;
        this.data = data;
    }



    public static R ok() {
        return new R(200, "操作成功", "成功", null);
    }

    public static R ok(Object data) {
        return new R(200, "操作成功", "成功", data);
    }

    public static R ok(String msg, Object data) {
        return new R(200, msg, "成功", data);
    }

    public static R ok(String msg, String type, Object data) {
        return new R(200, msg, type, data);
    }

    public static R error() {
        return new R(500, "服务器异常", "失败", null);
    }

    public static R error(String msg) {
        return new R(500, msg, "失败", null);
    }

    public static R error(int code, String msg) {
        return new R(code, msg, "失败", null);
    }

    public static R error(int code, String msg, Object data) {
        return new R(code, msg, "失败", data);
    }

    public R code(int code) {
        this.code = code;
        return this;
    }

    public R msg(String msg) {
        this.msg = msg;
        return this;
    }

    public R type(String type) {
        this.type = type;
        return this;
    }

    public R data(Object data) {
        this.data = data;
        return this;
    }

    public R put(String key, Object value) {
        if (this.extra == null) {
            this.extra = new HashMap<>();
        }
        this.extra.put(key, value);
        return this;
    }
}
