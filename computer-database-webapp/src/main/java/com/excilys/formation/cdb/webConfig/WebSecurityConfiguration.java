package com.excilys.formation.cdb.webConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.excilys.formation.cdb.service.UserService;

@Configuration
@EnableWebMvc
@EnableWebSecurity
@ComponentScan("com.excilys.formation.cdb")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    private UserDetailsService userDetailsService;
    
    public WebSecurityConfiguration(UserService userService) {
        this.userDetailsService = userService;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }
     
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }
    

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/computer/edit", "/computer/add", "/computer/delete").hasRole("ADMIN")
                .antMatchers("/computer/dashboard").hasAnyRole("USER", "ADMIN")
                .and()
            .formLogin()
                .loginPage("/login/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/computer/dashboard")
                .and()       
            .logout()
                .logoutUrl("/login/logout")
                .logoutSuccessUrl("/login/login")
                .invalidateHttpSession(true)
                .and()
            .exceptionHandling()
                .accessDeniedPage("login/403");


    }
}