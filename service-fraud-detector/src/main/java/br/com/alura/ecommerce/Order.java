package br.com.alura.ecommerce;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public record Order(String userId, String orderId, BigDecimal amount) {
}
