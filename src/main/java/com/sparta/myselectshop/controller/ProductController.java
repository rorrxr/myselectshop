package com.sparta.myselectshop.controller;

import com.sparta.myselectshop.dto.ProductMypriceRequestDto;
import com.sparta.myselectshop.dto.ProductRequestDto;
import com.sparta.myselectshop.dto.ProductResponseDto;
import com.sparta.myselectshop.entity.User;
import com.sparta.myselectshop.security.UserDetailsImpl;
import com.sparta.myselectshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    // 관심 상품 등록하기
    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        User user = userDetails.getUser();
        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getEmail());

        // 응답 보내기
        return productService.createProduct(requestDto, userDetails.getUser());
    }

    // 관심 상품 희망 최저가 등록하기
    @PutMapping("/products/{id}")
    public ProductResponseDto updateProduct(@PathVariable Long id, @RequestBody ProductMypriceRequestDto requestDto) {
        // 응답 보내기
        return productService.updateProduct(id, requestDto);
    }

    // 관심 상품 조회하기
    @GetMapping("/products")
    public Page<ProductResponseDto> getProducts(
            @RequestParam("page") int page, // 몇 페이지인지
            @RequestParam("size") int size, // 사이즈
            @RequestParam("sortBy") String sortBy, // 정렬 항목
            @RequestParam("isAsc") boolean isAsc, // 오름차순, 내림차순
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // 응답 보내기
        return productService.getProducts(userDetails.getUser(),  page-1, size, sortBy, isAsc);
    }

    // 관리자 조회
//    @GetMapping("/admin/products")
//    public List<ProductResponseDto> getAllProducts() {
//        return productService.getAllProducts();
//    }


}
