package com.sanbosillok.sanbosillokserver.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanbosillok.sanbosillokserver.config.jwt.JwtAuthenticationFilter;
import com.sanbosillok.sanbosillokserver.config.jwt.JwtTokenProvider;
import com.sanbosillok.sanbosillokserver.config.jwt.LoginFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터 체인에 등록
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;

    private static void corsAllow(CorsConfigurer<HttpSecurity> corsCustomizer) {
        corsCustomizer.configurationSource(request -> {

            CorsConfiguration configuration = new CorsConfiguration();

            configuration.setAllowedMethods(Collections.singletonList("*"));
            configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "https://sanbosillok.vercel.app"));
            configuration.setAllowedHeaders(Collections.singletonList("*"));
            configuration.setAllowCredentials(true);
            configuration.setMaxAge(3600L);

            configuration.setExposedHeaders(Collections.singletonList("Authorization"));

            return configuration;
        });
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http

                // cors 설정
                .cors((SecurityConfig::corsAllow))

                //csrf disable
                .csrf(AbstractHttpConfigurer::disable)

                //From 로그인 방식 disable
                .formLogin(AbstractHttpConfigurer::disable)

                //http basic 인증 방식 disable
                .httpBasic(AbstractHttpConfigurer::disable)

                //경로별 인가 작업
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/signup", "/login", "/checkUserName/{username}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/post", "/post/{title}", "/post/random").hasAnyRole("GUEST", "ACTIVE", "ADMIN")
                        .requestMatchers("/post", "/post/{title}", "/post/upload").hasAnyRole("ACTIVE", "ADMIN")
                        .requestMatchers("/admin", "/admin/{id}").hasRole("ADMIN")
                        .anyRequest().authenticated())

                // 필터 등록
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtTokenProvider, objectMapper), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), LoginFilter.class)

                //세션 설정
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

}
