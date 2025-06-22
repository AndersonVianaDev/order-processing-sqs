package com.anderson.order_api.infra.client;

import com.anderson.order_api.infra.exceptions.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class ProductClientErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    @Override
    public Exception decode(String s, Response response) {
        final HttpStatus status = HttpStatus.valueOf(response.status());

        try (InputStream body = response.body().asInputStream()) {
            final String json = new String(body.readAllBytes(), StandardCharsets.UTF_8);
            final StandardException standard = objectMapper.readValue(json, StandardException.class);

            return switch (response.status()) {
                case 404 -> new NotFoundException(standard.message());
                case 409 -> new DataConflictException(standard.message());
                case 422 -> new InvalidStockOperationException(standard.message());
                default -> new UnexpectedException(standard.message());
            };
        } catch (Exception e) {
            return new UnexpectedException("Failed to parse error from product-service", e);
        }
    }
}
