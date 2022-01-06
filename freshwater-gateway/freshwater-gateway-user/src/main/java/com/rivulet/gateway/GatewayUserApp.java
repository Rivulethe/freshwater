package com.rivulet.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author song
 */
@SpringBootApplication
@EnableEurekaClient
public class GatewayUserApp {
    public static void main(String[] args) {
        SpringApplication.run(GatewayUserApp.class, args);
    }

    @Bean(name = "ipKeyResolver")
    public KeyResolver userKeyResolver() {

        return new KeyResolver() {
            @Override
            public Mono<String> resolve(ServerWebExchange exchange) {
                String ip = exchange.getRequest().getRemoteAddress().getHostString();
                return Mono.just(ip);
            }
        };
    }
}
