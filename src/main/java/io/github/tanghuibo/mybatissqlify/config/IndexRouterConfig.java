package io.github.tanghuibo.mybatissqlify.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.net.URI;

/**
 * index重定向
 * @author tanghuibo
 * @date 2019/12/23上午12:22
 */
@Configuration
public class IndexRouterConfig {

    /**
     * index重定向
     * @return
     */
    @Bean
    RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route(RequestPredicates.GET("/").or(RequestPredicates.GET("/index")),
                request -> ServerResponse.permanentRedirect(URI.create("/index.html")).build());
    }
}