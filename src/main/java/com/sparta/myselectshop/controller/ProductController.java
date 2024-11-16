package com.sparta.myselectshop.controller;

import com.sparta.myselectshop.dto.ProductMypriceRequestDto;
import com.sparta.myselectshop.dto.ProductRequestDto;
import com.sparta.myselectshop.dto.ProductResponseDto;
import com.sparta.myselectshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    // 관심 상품 등록하기
    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto) {
        // 응답 보내기
        return productService.createProduct(requestDto);
    }

//    // 관심 상품 희망 최저가 등록하기
//    @PutMapping("/products/{id}")
//    public ProductResponseDto updateProduct(@PathVariable Long id, @RequestBody ProductMypriceRequestDto requestDto) {
//        // 응답 보내기
//        return productService.updateProduct(id, requestDto);
//    }
//
//    // 관심 상품 조회하기
//    @GetMapping("/products")
//    public List<ProductResponseDto> getProducts() {
//        // 응답 보내기
//        return productService.getProducts();
//    }

}
