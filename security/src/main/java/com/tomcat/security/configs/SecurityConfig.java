package com.tomcat.security.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import security.jwt.AuthEntryPointJwt;
import security.jwt.AuthTokenFilter;
import security.jwt.JwtUtils;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    // DataSource bean to inject into the UserDetailsService
    @Autowired
    private DataSource dataSource;

    // JwtUtils bean to inject into the AuthTokenFilter
    @Bean
    public JwtUtils jwtUtils() {
        return new JwtUtils();
    }

    // UserDetailsService bean to inject into the AuthenticationManager
    @Bean
    public AuthTokenFilter authTokenFilter() {
        return new AuthTokenFilter();
    }

    // AuthEntryPointJwt bean to inject into the SecurityFilterChain
    @Bean
    public AuthEntryPointJwt authEntryPointJwt() {
        return new AuthEntryPointJwt();
    }

    // UserDetailsService to inject users before app starts using command line runner
    @Bean
    public UserDetailsService userDetailsService() {
        return new JdbcUserDetailsManager(dataSource);
    }


    // AuthenticationManager bean to inject into the AuthTokenFilter
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain customSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/signin").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
        // Configure exception handling when authentication fails
        http.exceptionHandling(exceptionHandling -> exceptionHandling
                .authenticationEntryPoint(authEntryPointJwt())
        );

        // Add the authentication token filter before the UsernamePasswordAuthenticationFilter
        http.addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails user = User.withUsername("user")
//                .password(passwordEncoder().encode("user"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.withUsername("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build();
//        System.out.println(user.getPassword());
//        System.out.println(admin.getPassword());
//
//
////        return new InMemoryUserDetailsManager(user, admin);
//
//        // Insert only if they don't exist
//
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        if (!jdbcUserDetailsManager.userExists(user.getUsername())) {
//            jdbcUserDetailsManager.createUser(user);
//        }
//        if (!jdbcUserDetailsManager.userExists(admin.getUsername())) {
//            jdbcUserDetailsManager.createUser(admin);
//        }
//        return jdbcUserDetailsManager;
//    }


    // PasswordEncoder to encode user passwords
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // CommandLineRunner bean to initialize the database with users
    @Bean
    public CommandLineRunner initDatabase(UserDetailsManager userDetailsManager) {
        return args -> {

            JdbcUserDetailsManager jdbcUserDetailsManager = (JdbcUserDetailsManager) userDetailsService();
            UserDetails user = User.withUsername("user")
                    .password(passwordEncoder().encode("user"))
                    .roles("USER")
                    .build();
            UserDetails admin = User.withUsername("admin")
                    .password(passwordEncoder().encode("admin"))
                    .roles("ADMIN")
                    .build();
            if (!jdbcUserDetailsManager.userExists(user.getUsername())) {
                jdbcUserDetailsManager.createUser(user);
            }
            if (!jdbcUserDetailsManager.userExists(admin.getUsername())) {
                jdbcUserDetailsManager.createUser(admin);
            }
        };
    }
}