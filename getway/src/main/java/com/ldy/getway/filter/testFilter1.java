package com.ldy.getway.filter;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

@Slf4j
@Order(4)
@Component
public class testFilter1 implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("测试过滤器1 ！");
        MultiValueMap<String, String> params = exchange.getRequest().getQueryParams();
        Set<String> strings = params.keySet();
        for (String string : strings) {
            List<String> value = params.get(string);
            log.info(string+"  :  "+value.get(0));
        }
        return chain.filter(exchange);
    }
}
