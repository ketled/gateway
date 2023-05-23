package com.example.gateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class CustomStripPrefixFilter implements GatewayFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String newPath = request.getPath().value().replaceFirst("/mybooking/gateway", "");
        ServerHttpRequest modifiedRequest = request.mutate().path(newPath).build();

        HttpHeaders headers = modifiedRequest.getHeaders();
        headers.add("X-Original-Path", request.getPath().value());

        return chain.filter(exchange.mutate().request(modifiedRequest).build());
    }
}

