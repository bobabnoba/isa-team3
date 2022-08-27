package com.ftn.fishingbooker.security.config;

import com.ftn.fishingbooker.security.auth.RestAuthenticationEntryPoint;
import com.ftn.fishingbooker.security.auth.TokenAuthenticationFilter;
import com.ftn.fishingbooker.security.util.TokenUtils;
import com.ftn.fishingbooker.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private UserServiceImpl userService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userService) //Authentication manageru damo userService jer on implementira metodu loadUserByUsername
                .passwordEncoder(passwordEncoder);
    }

    //Rute dozvoljene svima i za koje ocekujemo da korisnici budu authenticated
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()

                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/**").permitAll()
                .antMatchers("/auth/register/**").permitAll()
                .antMatchers("/auth/register/client").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated().and()
                .cors().and()
                .addFilterBefore(new TokenAuthenticationFilter(tokenUtils, userService),
                        BasicAuthenticationFilter.class);
        http.csrf().disable();

    }

    //Za koje rute i fajlove Spring Security ignorise bilo kakve promjene
    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring().antMatchers(HttpMethod.POST, "/auth/**");//login
        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "swagger-ui/**", "/swagger-ui.html", "/webjars/**");
        web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "favicon.ico", "/**/*.html",
                "*.css", "/**/*.js");
    }
}
