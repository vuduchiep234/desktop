/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.config;

import com.example.library.authentication.CustomAccessDeniedHandler;
import com.example.library.authentication.JWTAuthenticationTokenFilter;
import com.example.library.authentication.MyAuthenticationSuccessHandle;
import com.example.library.authentication.RestAuthenticationEntryPoint;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public JWTAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
        JWTAuthenticationTokenFilter jWTAuthenticationFilter = new JWTAuthenticationTokenFilter();
        jWTAuthenticationFilter.setAuthenticationManager(authenticationManager());
        return jWTAuthenticationFilter;
    }

    @Bean
    public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }
    
    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandle() {
        return new MyAuthenticationSuccessHandle();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    protected void configure(HttpSecurity http) throws Exception {
        
        http.csrf().ignoringAntMatchers("/**");
        
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/v1/register").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/v1/login").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/v1/sendSimpleEmail").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/v1/sendAttachmentEmail").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/v1/uploadFile").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/v1/downloadFile/**").permitAll();

        http.antMatcher("/**").httpBasic().authenticationEntryPoint(restAuthenticationEntryPoint()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers("/admin/**").permitAll()//hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/**").permitAll()//hasRole("ADMIN")//hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/api/v1/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/api/v1/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/v1/**").hasRole("ADMIN").and()
                
//                .formLogin()
//                .loginPage("/login")
//                
//                .successHandler(myAuthenticationSuccessHandle())
//                .defaultSuccessUrl("/")
//                .failureUrl("/login?error")
//                .and()
                
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
        
//        http.antMatcher("/admin/**").httpBasic().authenticationEntryPoint(restAuthenticationEntryPoint()).and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .and()
//                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
//                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());

    }

//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        return bCryptPasswordEncoder;
//    }
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("select username,password from user where username=?")
//                .authoritiesByUsernameQuery("select username, role from user where username=?");
//
//    }
}
