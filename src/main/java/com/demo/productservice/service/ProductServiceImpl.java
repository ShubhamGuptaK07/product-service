package com.demo.productservice.service;

import com.demo.productservice.entity.Product;
import com.demo.productservice.exception.ProductServiceCustomException;
import com.demo.productservice.model.ProductRequest;
import com.demo.productservice.model.ProductResponse;
import com.demo.productservice.repository.ProductRespository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRespository productRespository;

    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("Adding product.....");
        Product product = Product.builder()
                .productName(productRequest.getName())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();
        productRespository.save(product);
        log.info("Added product.....");
        return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(long productId) {
        log.info("get the product for product id : {}",productId);
        Product product=productRespository.findById(productId)
                .orElseThrow(()->new ProductServiceCustomException("Product with given id not found","PRODUCT_NOT_FOUND"));
        ProductResponse productResponse=new ProductResponse();
        BeanUtils.copyProperties(product,productResponse);
        return productResponse;
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<ProductResponse> ProductResponses=new ArrayList<>();
        List<Product> products=productRespository.findAll();
        for(Product product:products){
            ProductResponse productResponse=new ProductResponse();
            BeanUtils.copyProperties(product,productResponse);
            ProductResponses.add(productResponse);
        }
        return ProductResponses;
    }

    @Override
    public long getProductCount() {
        List<Product> products=productRespository.findAll();
        return products.size();
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reduce the qunatity {} for id : {}",quantity,productId);
        Product product=productRespository.findById(productId)
                .orElseThrow(()->new ProductServiceCustomException("Product with given id not found",
                        "PRODUCT_NOT_FOUND"));
        if(product.getQuantity()<quantity){
            throw new ProductServiceCustomException("Product does not have sufficient quantity",
                    "INSUFFICIENT_QUANTITY");
        }else{
            product.setQuantity(product.getQuantity()-quantity);
            productRespository.save(product);
            log.info("Product quantity updated successfully");
        }
    }
}
