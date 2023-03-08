package com.upcoming.events.demo.config;

import org.springframework.cglib.core.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.header.Header;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http 
    
                .cors()
                .and()
                .headers ( header -> header.frameOptions().disable())
                .csrf(csrf -> csrf.disable())
                .formLogin (form -> form.disable())
                .logout (out -> out
                    .logoutUrl ("api/logout")
                    .deleteCookies ("JSESIONID"))
                .authorizeHttpRequests((auth) -> auth
                    .antMatchers(HttpMethod.POST, "/api/register").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/register").permitAll());
                // .httpBasic(Customizer.withDefaults());
                
                return http.build();
    }

    // @Bean 
    // UserDetailsService userDetailsService(){
    //     return new InMemoryUserDetailsManager(
    //         User.withUsername("user")
    //             .password(passwordEncoder().encode("123"))
    //             .authorities("read", "write","ROLE_USER")
    //             .build(), 
    //             User.withUsername("admin")
    //             .password(passwordEncoder().encode("123"))
    //             .authorities("read", "write", "ROLE_ADMIN")
    //             .build()
    //     );
    // }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

