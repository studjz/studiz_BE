    package com.example.studiz.global.config;

    import com.example.studiz.global.jwt.JwtFilter;
    import lombok.RequiredArgsConstructor;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

    @Configuration
    @EnableWebSecurity
    @RequiredArgsConstructor
    public class SecurityConfig {

        private final JwtFilter jwtFilter;

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    // 1. CSRF 해제 (Lambda DSL 방식 권장)
                    .csrf(AbstractHttpConfigurer::disable)

                    // 2. HTTP 요청 권한 설정
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/user/auth", "/user/login").permitAll() // 로그인, 회원가입 허용
                            .requestMatchers("/problem/**", "/user/delete/**", "/user/info").authenticated() // 문제 생성은 인증 필요
                            .anyRequest().authenticated() // 그 외 모든 요청 인증 필요
                    )

                    // 3. JWT 필터 추가 (UsernamePasswordAuthenticationFilter 앞에서 실행)
                    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

            return http.build();
        }

        // static을 붙여야 순환 참조(Cycle) 에러를 방지할 수 있습니다.
        @Bean
        public static BCryptPasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }