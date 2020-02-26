package io.github.tanghuibo.mybatissqlify;

import io.github.tanghuibo.mybatissqlify.tools.SpringBrowserUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MybatisSqlifyApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MybatisSqlifyApplication.class, args);
        SpringBrowserUtils.openBrowserSpringMVC(context);
    }


}
