package com.github.syed.bugtracker.integration.config;

import com.github.syed.bugtracker.user.Name;
import com.github.syed.bugtracker.user.Role;
import com.github.syed.bugtracker.user.User;
import com.github.syed.bugtracker.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class NoAuthSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        User user = User.builder().username("test").password("password").role(Role.DEV).email("email").name(Name.builder().firstName("dasdfas").lastName("da").build()).build();
        userRepository.save(user);

        http.csrf().disable()
            .authorizeRequests().antMatchers("/register", "/login", "/h2-console/**").permitAll()
            .and().anonymous().principal(user);

        http.headers().frameOptions().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}