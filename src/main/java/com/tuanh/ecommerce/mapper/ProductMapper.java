package com.tuanh.ecommerce.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.tuanh.ecommerce.dto.response.ProductResponse;
import com.tuanh.ecommerce.entity.product.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    public List<ProductResponse> fromListProductToListProductResponse(List<Product> products);
}
