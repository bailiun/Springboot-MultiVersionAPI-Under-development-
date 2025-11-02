package org.bailiun.multipleversionscoexist.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Component
public class EndpointLogger implements CommandLineRunner {

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    @Override
    public void run(String... args) {
        System.out.println("========= 已加载的接口列表 =========");
        handlerMapping.getHandlerMethods().forEach((mapping, handlerMethod) -> {
            System.out.println(mapping + "  =>  " + handlerMethod);
        });
        System.out.println("=================================");
    }
}
