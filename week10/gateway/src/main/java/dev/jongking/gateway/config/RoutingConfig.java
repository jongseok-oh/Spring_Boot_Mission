package dev.jongking.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class RoutingConfig {

    //@Bean //Bean에 등록 안하고 yml 설정으로도 가능
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route("community-shop", predicate ->
                    predicate
                            .path("/api/shop/**")
                            // 요청이 온 path
                            .filters(filter -> filter
                                    .rewritePath("/api/(?<path>.*)",
                                        // shop에는 /api/shop과 같은 end point가 없음
                                        // 그래서 path를 parsing 해줌
                                        // /api/까지는 일치하고 그 뒤에 오는 애를 path에 담겠다.
                                            "/${path}"))
                                        // 받은 path를 넣어 요청
                            .uri("http://localhost:8083"))
                            // 결과를 반환할 uri
                .build();
    }
}
