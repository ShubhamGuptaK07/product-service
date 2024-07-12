package com.demo.productservice.service;

import com.demo.productservice.model.ProductRequest;
import com.demo.productservice.model.ProductResponse;

import java.util.List;

public interface ProductService {
    long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long productId);

    List<ProductResponse> getAllProducts();

    long getProductCount();

    void reduceQuantity(long productId,long quantity);
}
