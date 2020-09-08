package com.github.syed.bugtracker.integration.config;

import com.github.syed.bugtracker.user.Name;
import com.github.syed.bugtracker.user.Role;
import com.github.syed.bugtracker.user.User;
import com.github.syed.bugtracker.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Profile("test")
public class NoAuthSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        User user = User.builder().username("test").password("password").role(Role.DEV).email("email").name(Name.builder().firstName("dasdfas").lastName("da").build()).build();
        userRepository.save(user);

        httpSecurity.csrf().disable()
            .authorizeRequests().anyRequest().permitAll()
            .and().anonymous().principal(user);
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