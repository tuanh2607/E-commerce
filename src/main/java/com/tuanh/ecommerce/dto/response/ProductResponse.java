package com.tuanh.ecommerce.dto.response;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class ProductResponse {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private List<ImageResponse> images;
}
