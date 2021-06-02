package com.example.loginpanel.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/firstMethod").permitAll()
                .antMatchers(HttpMethod.GET, "/secondMethod").hasAnyAuthority()
                .antMatchers(HttpMethod.GET, "/thirdMethod").hasAuthority(UserPermission.WRITE.getPermisision())
                .antMatchers(HttpMethod.GET, "/fourthMethod").hasRole(UserRole.ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }


    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("user123"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        UserDetails moderator = User.builder()
                .username("moderator")
                .password(passwordEncoder.encode("moderator123"))
                .roles("MODERATOR")
                .build();

        return new InMemoryUserDetailsManager(
                user,
                admin,
                moderator
        );
    }
}
