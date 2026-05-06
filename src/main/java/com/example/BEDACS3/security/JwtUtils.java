    package com.example.BEDACS3.security;

    import io.jsonwebtoken.*;
    import io.jsonwebtoken.security.Keys;
    import org.springframework.stereotype.Component;

    import java.security.Key;
    import java.util.Date;

    @Component
    public class JwtUtils {
        // Chuỗi bí mật dùng để mã hóa Token (Độ dài phải trên 256-bit)
        private static final String SECRET_KEY = "DayLaMotChuoiBiMatSieuDaiCuaPhucVanChoDuAnDACS3KhongTheBiHackDuoc";
        // Sửa Access Token thành 15 phút
        private static final long EXPIRE_DURATION = 15 * 60 * 1000;
        // Thêm hạn Refresh Token: 7 ngày
        private static final long REFRESH_EXPIRE_DURATION = 7L * 24 * 60 * 60 * 1000;

        private Key getSigningKey() {
            return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        }

        // 1. Tạo Token từ Email
        public String generateToken(String email) {
            return Jwts.builder()
                    .setSubject(email)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                    .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                    .compact();
        }

        // 2. Lấy Email từ Token
        public String getEmailFromToken(String token) {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }

        // 3. Kiểm tra Token có hợp lệ không
        public boolean validateToken(String token) {
            try {
                Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
                return true;
            } catch (JwtException | IllegalArgumentException e) {
                return false;
            }
        }
        public String generateRefreshToken(String email) {
            return Jwts.builder()
                    .setSubject(email)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + REFRESH_EXPIRE_DURATION))
                    .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                    .compact();
        }
    }