package com.example.BEDACS3.Repository.entity;

public class CategoryBlogEntity {
    private int id;
    private String name;

    public CategoryBlogEntity() {
    }

    public CategoryBlogEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // --- GETTER & SETTER ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
