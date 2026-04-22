package com.example.BEDACS3.Repository.impl;


import com.example.BEDACS3.Database.DatabaseDA;
import com.example.BEDACS3.Repository.buildingRepository;
import com.example.BEDACS3.Repository.entity.buildingEntity;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class buildingRepositoryimpl implements buildingRepository {

    @Override
    public List<buildingEntity> findAll() {
        String sql = "SELECT * FROM room";
        List<buildingEntity> result = new ArrayList<>();

        try (Connection conn = DatabaseDA.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                buildingEntity bd = new buildingEntity();
                bd.setRoom_id(rs.getInt("room_id"));
                bd.setRoom_name(rs.getString("room_name"));
                bd.setBuilding(rs.getString("building"));
                bd.setFloor(rs.getInt("floor"));
                result.add(bd);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}