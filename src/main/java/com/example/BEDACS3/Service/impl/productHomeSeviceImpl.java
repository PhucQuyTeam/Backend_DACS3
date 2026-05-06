package com.example.BEDACS3.Service.impl;

import com.example.BEDACS3.Repository.OrderDetailRepository;
import com.example.BEDACS3.Repository.entity.ProductImageEntity;
import com.example.BEDACS3.Repository.entity.productEntity;
import com.example.BEDACS3.Repository.productImageRepository;
import com.example.BEDACS3.Repository.productRepository;
import com.example.BEDACS3.Service.ProductHomeService;
import com.example.BEDACS3.Service.model.products.ProductHomeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class productHomeSeviceImpl implements ProductHomeService {

    @Autowired
    private productRepository productRepository;
    @Autowired
    private productImageRepository productImageRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;


    @Override
    public List<ProductHomeDTO> getProducts() {
        List<productEntity> entities = productRepository.findAllProducts();


        List<ProductHomeDTO> dtos = new ArrayList<>();

        for (productEntity entity : entities){
            ProductHomeDTO dto = new ProductHomeDTO();
            dto.setProductId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());
            dto.setPrice(entity.getPrice());
            dto.setQuantity(entity.getQuantity());

            Integer productID = entity.getId();

            ProductImageEntity productImageEntity = productImageRepository.findFirstImageByProductId(productID);
            if (productImageEntity != null && productImageEntity.getImagePath() != null) {
                dto.setImg(productImageEntity.getImagePath());
            }

            Integer total_proQuantity = orderDetailRepository.getTotalQuantityByProductId(productID);
            if(total_proQuantity !=null || total_proQuantity !=0){
                dto.setTotal_ProductQuantity(total_proQuantity);
            }

            dtos.add(dto);
        }
        return dtos;
    }
}
