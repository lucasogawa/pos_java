package com.ogawalucas.cities.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder cipher() {
        return new BCryptPasswordEncoder();
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/").hasAnyAuthority("list", "admin")
            .antMatchers("/create").hasAuthority("admin")
            .antMatchers("/delete").hasAuthority("admin")
            .antMatchers("/model").hasAuthority("admin")
            .antMatchers("/edit").hasAuthority("admin")
            .antMatchers("/show-cookie-list").hasAnyAuthority("list", "admin")
            .anyRequest().denyAll()
            .and()
            .formLogin().loginPage("/login.html").permitAll()
            .and()
            .logout().permitAll();
    }
}
