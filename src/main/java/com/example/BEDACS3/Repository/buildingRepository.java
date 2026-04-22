package com.example.BEDACS3.Repository;

import com.example.BEDACS3.Repository.entity.buildingEntity;

import java.util.List;

public interface buildingRepository {
    public List<buildingEntity> findAll();
}
