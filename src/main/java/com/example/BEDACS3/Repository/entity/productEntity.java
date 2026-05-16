package com.example.BEDACS3.Repository.entity;


import java.sql.Timestamp;

public class productEntity {
        private int id;
        private String name;
        private String description;
        private int price;
        private int quantity;
        private int categoryId;
        private Timestamp createdAt;
        private Timestamp updatedAt;

        public productEntity(int id, String name, String description, int price, int quantity, int categoryId, Timestamp createdAt, Timestamp updatedAt) {
                this.id = id;
                this.name = name;
                this.description = description;
                this.price = price;
                this.quantity = quantity;
                this.categoryId = categoryId;
                this.createdAt = createdAt;
                this.updatedAt = updatedAt;
        }

        public productEntity() {
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }


        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public int getPrice() {
                return price;
        }

        public void setPrice(int price) {
                this.price = price;
        }

        public int getQuantity() {
                return quantity;
        }

        public void setQuantity(int quantity) {
                this.quantity = quantity;
        }

        public int getCategoryId() {
                return categoryId;
        }

        public void setCategoryId(int categoryId) {
                this.categoryId = categoryId;
        }

        public Timestamp getCreatedAt() {
                return createdAt;
        }

        public void setCreatedAt(Timestamp createdAt) {
                this.createdAt = createdAt;
        }

        public Timestamp getUpdatedAt() {
                return updatedAt;
        }

        public void setUpdatedAt(Timestamp updatedAt) {
                this.updatedAt = updatedAt;
        }
}
