package org.bailiun.multipleversionscoexist.utils;

import javax.servlet.http.HttpServletRequest;

public class IP {


    public static String getClientIp(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        String ip = request.getHeader("X-Forwarded-For");
        if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {

            if (ip.contains(",")) {
                ip = ip.split(",")[0].trim();
            }
        } else {
            ip = request.getHeader("Proxy-Client-IP");
            if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        }
        return ip;
    }
}