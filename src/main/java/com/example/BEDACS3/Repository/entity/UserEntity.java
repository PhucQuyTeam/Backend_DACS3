    package com.example.BEDACS3.Repository.entity;

    import java.sql.Timestamp;

    public class UserEntity {
        private int id;
        private String name;
        private String username;
        private String email;
        private String password;
        private String numberPhone;
        private int roleid;
        private String avatar;
        private Timestamp createAt;

        // --- GETTER & SETTER ---
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }

        public String getNumberPhone() { return numberPhone; }
        public void setNumberPhone(String numberPhone) { this.numberPhone = numberPhone; }

        public int getRoleid() { return roleid; }
        public void setRoleid(int roleid) { this.roleid = roleid; }

        public String getAvatar() { return avatar; }
        public void setAvatar(String avatar) { this.avatar = avatar; }

        public Timestamp getCreateAt() { return createAt; }
        public void setCreateAt(Timestamp createAt) { this.createAt = createAt; }
    }