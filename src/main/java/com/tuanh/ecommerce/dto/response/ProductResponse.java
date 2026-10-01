package com.tuanh.ecommerce.dto.response;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class ProductResponse {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
}
