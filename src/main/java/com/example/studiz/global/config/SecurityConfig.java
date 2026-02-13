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
                    .csrf(AbstractHttpConfigurer::disable)


                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/user/auth", "/user/login").permitAll()
                            .requestMatchers("/problem/**", "/user/delete/**", "/user/info", "/user/update","/main/major").hasRole("USER")                           .anyRequest().authenticated()
                    )


                    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

            return http.build();
        }


        @Bean
        public static BCryptPasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }