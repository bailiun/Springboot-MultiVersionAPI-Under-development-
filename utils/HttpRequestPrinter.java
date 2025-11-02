package org.bailiun.multipleversionscoexist.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;

public class HttpRequestPrinter {

    public static void printRequest(HttpServletRequest request) throws IOException {
        System.out.println("=== HttpServletRequest ===");
        System.out.println("Method: " + request.getMethod());
        System.out.println("Request URI: " + request.getRequestURI());
        System.out.println("Query String: " + request.getQueryString());

        System.out.println("--- Headers ---");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ": " + value);
        }

        System.out.println("--- Parameters ---");
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String name = paramNames.nextElement();
            String value = request.getParameter(name);
            System.out.println(name + ": " + value);
        }


        System.out.println("--- Body ---");
        StringBuilder body = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            body.append(line).append("\n");
        }
        System.out.println(body.toString());

           }
}
