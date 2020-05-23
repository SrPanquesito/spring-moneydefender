package com.example.MoneyDefender.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
   @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Deshabilita sessiones y autentificacion basica.
        http.csrf().and().httpBasic().disable()
        // Permite requests a las rutas de autorización para registrate como usuario y para obtener tus credenciales.
            .authorizeRequests()
            .antMatchers("/api/auth/**").permitAll()
        // Para todas las demas rutas pide verificación.
            .anyRequest().authenticated();

        
        // http
        //     .cors()
        //     .and().authorizeRequests()
        //     // .antMatchers("/index.html", "/", "/home", "/login").permitAll()
        //     .anyRequest().authenticated()
        //     .and().sessionManagement()
        //     .sessionCreationPolicy(SessionCreationPolicy.NEVER);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}