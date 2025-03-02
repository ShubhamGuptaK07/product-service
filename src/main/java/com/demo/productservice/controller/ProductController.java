package com.demo.productservice.controller;

import com.demo.productservice.model.ProductRequest;
import com.demo.productservice.model.ProductResponse;
import com.demo.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest) {
        long productId = productService.addProduct(productRequest);
        return new ResponseEntity<>(productId, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") long productId){
        ProductResponse  productResponse=productService.getProductById(productId);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        List<ProductResponse> products=productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/count")
    public ResponseEntity<Long> getProductCount(){
        long productsCount=productService.getProductCount();
        return new ResponseEntity<>(productsCount, HttpStatus.OK);
    }
    @PutMapping("/reduceQuantity/{productId}")
    public ResponseEntity<Void> reduceQuantity(@PathVariable("productId") long productId, @RequestParam long quantity){
        productService.reduceQuantity(productId,quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/updateQuantity/{productId}")
    public ResponseEntity<Void> updateQuantity(@PathVariable("productId") long productId, @RequestParam long quantity){
        productService.updateQuantity(productId,quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
