package com.example.BEDACS3.controller;

import com.example.BEDACS3.Service.impl.buildingServiceImpl;
import com.example.BEDACS3.Service.model.BuildingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TestController {

    //từ param
//    @GetMapping("/api/building")
//    public void getBuilding(@RequestParam(value = "name",required = false) String name,
//                            @RequestParam(value = "tang",required = false) Integer tang) {
//        System.out.println(name+" "+tang);
//    }

    //dung map
//    @PostMapping("/api/building")
//    public void getBuilding2(@RequestBody Map<String,String> params) {
//        System.out.println("oke");
//    }

    //dùng java bean
    @PostMapping("/api/building")
    public void getBuilding3(@RequestBody BuildingDTO bt) {
        System.out.println("oke");
    }

//    @GetMapping("/api/building")
//    public BuildingDTO getBuilding(@RequestParam(value = "name",required = false) String name,
//                            @RequestParam(value = "tang",required = false) Integer tang) {
//        BuildingDTO result = new BuildingDTO();
//        result.setName(name);
//        result.setTang(tang);
//        return result;
//    }

//    @GetMapping("/api/building")
//    public List<BuildingDTO> getBuilding(@RequestParam(value = "name",required = false) String name,
//                            @RequestParam(value = "tang",required = false) Integer tang) {
//        List<BuildingDTO>    result = new ArrayList<>();
//        BuildingDTO bt1 = new BuildingDTO();
//        bt1.setName(name);
//        bt1.setTang(tang);
//        BuildingDTO bt2 = new BuildingDTO();
//        bt2.setName("quy");
//        bt2.setTang(3);
//        result.add(bt1);
//        result.add(bt2);
//        return result;
//    }

    @DeleteMapping("/api/building{id}")
    public void deleteBuilding(@PathVariable Integer id){
        System.out.println("đã xoá ");
    }



    @Autowired
    private buildingServiceImpl hi;

    // test thao tác sql
    @GetMapping("/api/building")
    public List<BuildingDTO> getBuilding() {
        List<BuildingDTO> result = hi.findAll();
        return result;
    }
}