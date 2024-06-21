package com.crio.readrent.config;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.crio.readrent.services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(csrf -> csrf.disable());

        httpSecurity.authenticationProvider(authenticationProvider());

        httpSecurity.authorizeHttpRequests(configurer -> configurer
                .requestMatchers("user/login", "user/register")
                .permitAll()
                .anyRequest()
                .authenticated());

        // Explicitly tell Spring Security that we are using Basic Auth
        httpSecurity.httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }


    @Autowired
    UserService userService;
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     http
    //         .csrf(AbstractHttpConfigurer::disable)
    //         .authorizeHttpRequests(authorize -> authorize
    //             .requestMatchers("/user/register", "/user/login").permitAll()
    //             .requestMatchers("/books/**").hasRole("ADMIN")
    //             .anyRequest().authenticated()
    //         )
    //         // .sessionManagement(session -> session
    //         //     .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    //         // )
    //         .httpBasic(Customizer.withDefaults());          //explicitly tell that you are using Basic Auth

    //     return http.build();
    // }
}
