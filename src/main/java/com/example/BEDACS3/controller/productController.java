package com.example.BEDACS3.controller;


import com.example.BEDACS3.Service.ProductHomeService;
import com.example.BEDACS3.Service.model.products.ProductHomeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

}
