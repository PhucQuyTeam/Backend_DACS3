package com.example.BEDACS3.controller;


import com.example.BEDACS3.Repository.ReviewRepository;
import com.example.BEDACS3.Service.ProductHomeService;
import com.example.BEDACS3.Service.model.products.ProductHomeDTO;
import com.example.BEDACS3.Service.model.products.ReviewDTO;
import com.example.BEDACS3.Service.model.products.productDetailDTO;
import com.example.BEDACS3.Service.productDetailService;
import com.example.BEDACS3.Service.reviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/home")
public class productController {
    @Autowired
    private ProductHomeService productHomeService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductHomeDTO>> getProducts(){
        try {
            List<ProductHomeDTO> productHomeDTOS = productHomeService.getProducts();
            return ResponseEntity.ok(productHomeDTOS);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Autowired
    private productDetailService detailService;

    @GetMapping("products/{id}")
    public ResponseEntity<productDetailDTO> getProductDetail(@PathVariable("id") Integer productId) {
        try {
            productDetailDTO productDetail = detailService.getProductDetailById(productId);

            if (productDetail == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(productDetail);

        } catch (Exception e) {
            e.printStackTrace();
            // Nếu code bị lỗi (VD: đứt cáp DB), trả về mã 500 Internal Server Error
            return ResponseEntity.internalServerError().build();
        }
    }

    @Autowired
    private reviewService reviewService;

    @GetMapping("/products/{id}/reviews")
    public ResponseEntity<List<ReviewDTO>> getProductReviews(@PathVariable("id") Integer productId) {
        try {
            List<ReviewDTO> reviewList = reviewService.getReviewListForProduct(productId);

            return ResponseEntity.ok(reviewList);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }



}
