package com.mpsanchis.springoauth2resourceserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ResourceServerConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .antMatchers("/secret").hasAuthority("SCOPE_read:secret")
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oa2 ->
                oa2.jwt(Customizer.withDefaults())
            )
            .httpBasic().disable();

        return http.build();
    }
}
