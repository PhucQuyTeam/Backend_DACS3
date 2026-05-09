    package com.example.BEDACS3.Repository.impl;

    import com.example.BEDACS3.Database.DatabaseDA;
    import com.example.BEDACS3.Repository.UserRepository;
    import com.example.BEDACS3.Repository.entity.UserEntity;
    import com.example.BEDACS3.Service.model.address.AddressDTO;
    import com.example.BEDACS3.Service.model.address.ProvinceDTO;
    import com.example.BEDACS3.Service.model.address.WardDTO;
    import org.springframework.stereotype.Repository;

    import java.sql.*;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;

    @Repository
    public class UserRepositoryImpl implements UserRepository {

        @Override
        public UserEntity findByEmail(String email) {
            String sql = "SELECT * FROM users WHERE email = ?";
            try (Connection conn = DatabaseDA.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, email);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    UserEntity user = new UserEntity();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setUsername(rs.getString("username"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setNumberPhone(rs.getString("numberPhone"));
                    user.setAvatar(rs.getString("avatar"));
                    user.setRoleid(rs.getInt("roleid"));
                    return user;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public boolean existsByEmail(String email) {
            String sql = "SELECT count(*) FROM users WHERE email = ?";
            try (Connection conn = DatabaseDA.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, email);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        public UserEntity save(UserEntity user) {
            String sql = "INSERT INTO users (name, username, email, password, numberPhone, roleid, create_at) VALUES (?, ?, ?, ?, ?, ?, NOW())";
            try (Connection conn = DatabaseDA.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                ps.setString(1, user.getName());
                ps.setString(2, user.getUsername());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getPassword());
                ps.setString(5, user.getNumberPhone());
                ps.setInt(6, user.getRoleid());

                int affectedRows = ps.executeUpdate();
                if (affectedRows > 0) {
                    ResultSet rs = ps.getGeneratedKeys();
                    if (rs.next()) {
                        user.setId(rs.getInt(1)); // Gắn ID vừa tạo vào entity
                        return user;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public UserEntity getNameReviewById(Integer userId) {
            UserEntity user = null;
            String sql = "SELECT name FROM users WHERE id = ?";


            try (Connection conn = DatabaseDA.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, userId);

                try (ResultSet rs = ps.executeQuery()) {


                    if (rs.next()) {
                        user = new UserEntity();
                        user.setName(rs.getString("name"));

                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return user;
        }

        // Thêm hàm này vào trong UserRepositoryImpl.java
        public boolean updateUserInfo(String email, String name, String phone, String avatar) {
            String sql = "UPDATE users SET name = ?, numberPhone = ?, avatar = ? WHERE email = ?";
            try (Connection conn = DatabaseDA.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, name);
                ps.setString(2, phone);
                ps.setString(3, avatar);
                ps.setString(4, email); // Lấy email làm gốc để tìm đúng user

                int affectedRows = ps.executeUpdate();
                return affectedRows > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }

        public List<AddressDTO> getMyAddresses(int userId) {
            List<AddressDTO> list = new ArrayList<>();

            // ĐÃ SỬA: Bổ sung a.proviceId, a.wardId, a.streetDetail vào câu SELECT
            String sql = "SELECT a.id, u.name, u.numberPhone, " +
                    "a.proviceId, a.wardId, a.streetDetail, " +
                    "CONCAT_WS(', ', a.streetDetail, w.name, p.name) as fullAddress " +
                    "FROM address a " +
                    "JOIN users u ON a.userId = u.id " +
                    "LEFT JOIN wards w ON a.wardId = w.id " +
                    "LEFT JOIN provinces p ON a.proviceId = p.id " +
                    "WHERE a.userId = ?";

            try (Connection conn = DatabaseDA.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    AddressDTO dto = new AddressDTO();
                    dto.setId(rs.getInt("id"));
                    dto.setReceiverName(rs.getString("name"));
                    dto.setReceiverPhone(rs.getString("numberPhone"));
                    dto.setFullAddress(rs.getString("fullAddress"));
                    // Gán thêm 3 biến mới này để lúc bấm Sửa nó hiện lên
                    dto.setProvinceId(rs.getInt("proviceId"));
                    dto.setWardId(rs.getInt("wardId"));
                    dto.setStreetDetail(rs.getString("streetDetail"));

                    list.add(dto);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("LỖI SQL LẤY ĐỊA CHỈ: " + e.getMessage());
            }
            return list;
        }

        // 1. Hàm Thêm địa chỉ
        public boolean insertAddress(int userId, int provinceId, int wardId, String streetDetail) {
            String sql = "INSERT INTO address (userId, proviceId, wardId, streetDetail, create_at) VALUES (?, ?, ?, ?, NOW())";
            try (Connection conn = DatabaseDA.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, userId);
                ps.setInt(2, provinceId);
                ps.setInt(3, wardId);
                ps.setString(4, streetDetail);
                return ps.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        // 2. Hàm Cập nhật địa chỉ (Ràng buộc cả id và userId)
        public boolean updateAddress(int id, int userId, int provinceId, int wardId, String streetDetail) {
            String sql = "UPDATE address SET proviceId = ?, wardId = ?, streetDetail = ?, updated_at = NOW() WHERE id = ? AND userId = ?";
            try (Connection conn = DatabaseDA.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, provinceId);
                ps.setInt(2, wardId);
                ps.setInt(3, Integer.parseInt(streetDetail));
                ps.setInt(4, id);
                ps.setInt(5, userId);
                return ps.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        // 3. Hàm Xóa địa chỉ (Ràng buộc userId để an toàn)
        public boolean deleteAddress(int id, int userId) {
            String sql = "DELETE FROM address WHERE id = ? AND userId = ?";
            try (Connection conn = DatabaseDA.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.setInt(2, userId);
                return ps.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        public List<ProvinceDTO> getAllProvinces() {
            List<ProvinceDTO> list = new ArrayList<>();
            String sql = "SELECT id, name FROM provinces";

            try (Connection conn = DatabaseDA.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    ProvinceDTO p = new ProvinceDTO();
                    p.setId(rs.getInt("id"));
                    p.setName(rs.getString("name"));
                    list.add(p);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return list;
        }

        // 2. Lấy danh sách Phường/Xã theo ID Tỉnh
        public List<WardDTO> getWardsByProvinceId(int provinceId) {
            List<WardDTO> list = new ArrayList<>();
            // Sửa lại chữ province_id cho khớp với cột khóa ngoại trong bảng wards của sếp nhé
            String sql = "SELECT id, name FROM wards WHERE proviceId = ?";

            try (Connection conn = DatabaseDA.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, provinceId);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    WardDTO w = new WardDTO();
                    w.setId(rs.getInt("id"));
                    w.setName(rs.getString("name"));
                    list.add(w);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return list;
        }

        public Map<String, Integer> getOrderCounts(int userId) {
            Map<String, Integer> counts = new HashMap<>();
            // Mặc định cho tất cả bằng 0
            counts.put("pendingCount", 0);
            counts.put("shippingCount", 0);
            counts.put("completedCount", 0);

            // ĐÃ SỬA: Lấy đúng cột deliveryStatus và Group by theo nó
            String sql = "SELECT deliveryStatus, COUNT(*) as total FROM orders WHERE userId = ? GROUP BY deliveryStatus";

            try (Connection conn = DatabaseDA.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    // Đọc đúng tên cột đã gọi trong câu SELECT
                    String status = rs.getString("deliveryStatus");
                    int total = rs.getInt("total");

                    if (status == null) continue; // Chống lỗi nếu Database bị null

                    // Mapping theo đúng tên biến Android đang chờ
                    if ("pending".equalsIgnoreCase(status)) {
                        counts.put("pendingCount", total);
                    } else if ("shipping".equalsIgnoreCase(status)) {
                        counts.put("shippingCount", total);
                    } else if ("delivered".equalsIgnoreCase(status) || "completed".equalsIgnoreCase(status)) {
                        counts.put("completedCount", total);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return counts;
        }
        public boolean updateCartQuantity(int cartId, int quantity) {
            String sql = "UPDATE cart SET quantity = ?, updated_at = NOW() WHERE id = ?";
            try (Connection conn = DatabaseDA.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, quantity);
                ps.setInt(2, cartId);

                return ps.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        public UserEntity findById(int id) {
            String sql = "SELECT * FROM users WHERE id = ?";
            try (Connection conn = DatabaseDA.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    UserEntity user = new UserEntity();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setAvatar(rs.getString("avatar"));
                    return user;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }