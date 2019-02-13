package org.vincedgy.edge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;

@SpringBootApplication
public class EdgeApplication {


    /**
     * API Gateway for this edge service
     * @param rlb
     * @return
     */
    @Bean
    RouteLocator gateway (RouteLocatorBuilder rlb) {
        return rlb
            .routes()
            .route(rSpec ->
             rSpec
                .host("*.foo.fr").and().path("/proxy")
                .filters( fSpec ->
                 fSpec.setPath("/reservations")
                  .addResponseHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                )
                .uri("http://localhost:8080")
            )
            .build();

    }

    public static void main(String[] args) {
        SpringApplication.run(EdgeApplication.class, args);
    }


}

