    package com.example.BEDACS3.security;

    import com.example.BEDACS3.Service.UserDetailsService;
    import jakarta.servlet.FilterChain;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
    import org.springframework.stereotype.Component;
    import org.springframework.web.filter.OncePerRequestFilter;

    import java.io.IOException;

    @Component
    public class JwtAuthFilter extends OncePerRequestFilter {

        @Autowired
        private JwtUtils jwtUtils;

        @Autowired
        private UserDetailsService userDetailsService;

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                throws ServletException, IOException {

            String authHeader = request.getHeader("Authorization");
            String token = null;
            String email = null;

            // ĐƯA TẤT CẢ VÀO TRY-CATCH ĐỂ BẢO VỆ SERVER KHÔNG BỊ CRASH
            try {
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    token = authHeader.substring(7);

                    // MẸO: Xóa dấu ngoặc kép thừa (nếu Android lỡ gửi kèm)
                    token = token.replace("\"", "");

                    email = jwtUtils.getEmailFromToken(token);
                }

                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                    if (jwtUtils.validateToken(token)) {
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
            } catch (Exception e) {
                // NẾU TOKEN SAI HOẶC LỖI, IN RA CONSOLE CHO DỄ TÌM, NHƯNG KHÔNG LÀM SẬP SERVER
                System.out.println(" LỖI XÁC THỰC TOKEN: " + e.getMessage());
            }

            filterChain.doFilter(request, response);
        }
    }