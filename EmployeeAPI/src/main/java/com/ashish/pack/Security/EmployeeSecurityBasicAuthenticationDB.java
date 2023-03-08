package com.ashish.pack.Security;

import com.ashish.pack.Filter.JwtAuthFilter;
import com.ashish.pack.Service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableMethodSecurity
public class EmployeeSecurityBasicAuthenticationDB {

    @Autowired
    JwtAuthFilter authFilter;

    @Bean
    public PasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new MyUserDetailsService();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                //.antMatchers("/save/**")
                //.antMatchers(HttpMethod.POST)
                .antMatchers("/authenticate")
                .permitAll()
                .antMatchers("/addUser")
                .permitAll()
                .antMatchers(HttpMethod.POST,"/employee/**")
                .hasRole("ADMIN")
                .antMatchers(HttpMethod.GET)
                .hasAnyRole("USER", "ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authenticationProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
