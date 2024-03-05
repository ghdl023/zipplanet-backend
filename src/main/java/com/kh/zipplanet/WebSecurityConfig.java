package com.kh.zipplanet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    //비밀번호 암호화
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // SpringSecurity가 기본적으로 csrf(서버간 위변조 공격)에 대한 보안을 자동으로 실행해주기 때문에 Get요청에 대해서는 정상적인 호출이 되나, Post요청에 대해서는 어떠한 이유로 요청을 거부하는 403 forbidden에러를 뱉는현상이있음. 비활성화시킴
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/home", "/login", "/login_post").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}