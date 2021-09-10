package com.deng.gateway.filter;

import com.deng.gateway.common.AuthConstant;
import com.deng.gateway.config.IgnoreUrlsConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import javax.annotation.Resource;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * 
 * @ClassName: IgnoreUrlsRemoveJwtFilter 
 * @Description: 白名单路径访问时需要移除JWT请求头
 * @author ZongCai
 * @date 2021/7/8
 */
@Slf4j
@Component
public class IgnoreUrlsRemoveJwtFilter implements WebFilter {
    @Resource
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    	log.info("进入IgnoreUrlsRemoveJwtFilter");
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();
        PathMatcher pathMatcher = new AntPathMatcher();
        //白名单路径移除JWT请求头
        List<String> ignoreUrls = ignoreUrlsConfig.getUrls();
        for (String ignoreUrl : ignoreUrls) {
            if (pathMatcher.match(ignoreUrl, uri.getPath())) {
                Map<String, Object> headerMap = new HashMap<>();
                HttpHeaders headers = request.getHeaders();
                Set<String> keys = headers.keySet();
                keys.stream().filter(k -> !k.equals(AuthConstant.JWT_TOKEN_HEADER)).forEach(k -> headerMap.put(k, headers.get(k)));
                Consumer<HttpHeaders> httpHeaders = httpHeader -> {
                    headerMap.forEach((k,v)->httpHeader.add(k,v.toString().substring(1,v.toString().length()-1)));//substring 去除toString产生的[]
                };
                //spring-web 5.1.2  request.mutate().header()实现为add,升级为版本后可直接使用其put方法实现header覆盖
                //清空header
                request.mutate().headers(HttpHeaders::clear);
                //add header
                request = request.mutate().headers(httpHeaders).build();
                exchange = exchange.mutate().request(request).build();
                return chain.filter(exchange);
            }
        }
        return chain.filter(exchange);
    }
}


