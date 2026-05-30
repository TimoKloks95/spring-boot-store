package com.codewithmosh.store.carts;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddProductToCartRequest {
    @NotNull
    private Long productId;
}
