package com.example.BEDACS3.controller;

import com.example.BEDACS3.Service.BlogService;
import com.example.BEDACS3.Service.model.blog.BlogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
@CrossOrigin("*")
public class BlogController {

    @Autowired
    private BlogService blogService;

    // API lấy toàn bộ bài viết
    @GetMapping("")
    public ResponseEntity<List<BlogDTO>> getAllBlogs() {
        List<BlogDTO> listBlogs = blogService.getAllBlogs();
        return ResponseEntity.ok(listBlogs);
    }
}
