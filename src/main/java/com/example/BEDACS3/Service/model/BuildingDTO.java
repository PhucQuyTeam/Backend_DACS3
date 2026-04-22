package com.example.BEDACS3.Service.model;

public class BuildingDTO {
    //format dữ liệu từ db trả về phù hợp với yêu cầu từ view
    private int room_id;
    private String phong;


    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }
}
