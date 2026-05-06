package com.example.BEDACS3.Service;

import com.example.BEDACS3.Service.model.products.productDetailDTO;

public interface productDetailService {
    productDetailDTO getProductDetailById(Integer productId);
}
