package com.example.card.config.GraphQlScalars;

import java.util.Collections;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class RequestHeaderInterceptor implements WebGraphQlInterceptor {

    @Override
    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
        String value = request.getHeaders().getFirst("Authorization");
        if (value != null) {
            request.configureExecutionInput((executionInput, builder) ->
                builder.graphQLContext(Collections.singletonMap("Authorization", value)).build());
        }
        return chain.next(request);
    }
}