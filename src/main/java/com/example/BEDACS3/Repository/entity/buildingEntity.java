package com.example.BEDACS3.Repository.entity;

public class buildingEntity {
    //class trong phần repository thường thuộc tính ánh xạ 1:1 vs DB
    private int room_id;
    private String room_name;
    private String building;
    private int floor;

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }
}
