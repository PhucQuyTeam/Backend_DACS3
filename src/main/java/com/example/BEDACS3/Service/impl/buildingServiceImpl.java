package com.example.BEDACS3.Service.impl;

import com.example.BEDACS3.Repository.entity.buildingEntity;
import com.example.BEDACS3.Repository.impl.buildingRepositoryimpl;
import com.example.BEDACS3.Service.buildingService;
import com.example.BEDACS3.Service.model.BuildingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class buildingServiceImpl implements buildingService {
    @Autowired
    private buildingRepositoryimpl buildingRepository;

    @Override
    public List<BuildingDTO> findAll() {
        List<buildingEntity> entities = buildingRepository.findAll();
        List<BuildingDTO> rs = new ArrayList<BuildingDTO>();
        for (buildingEntity item: entities ){
            BuildingDTO bd = new BuildingDTO();
            bd.setRoom_id(item.getRoom_id());
            bd.setPhong(item.getRoom_name() + ", "+ item.getBuilding()+ ", "+ item.getFloor());
            rs.add(bd);
        }

        return rs;
    }
}
