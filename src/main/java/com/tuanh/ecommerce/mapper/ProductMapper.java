package com.tuanh.ecommerce.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.tuanh.ecommerce.dto.response.ImageResponse;
import com.tuanh.ecommerce.dto.response.ProductResponse;
import com.tuanh.ecommerce.entity.product.Image;
import com.tuanh.ecommerce.entity.product.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    public List<ProductResponse> fromListProductToListProductResponse(List<Product> products);
    public ProductResponse fromProductToProductResponse(Product product);
    public ImageResponse fromImageToImageResponse(Image image);
}
