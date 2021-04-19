package gestionStages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.multipart.support.MultipartFilter;

import javax.servlet.ServletContext;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;

    public WebSecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    private Http403ForbiddenEntryPoint forbiddenEntryPoint;

    @Bean
    public Http403ForbiddenEntryPoint forbiddenEntryPoint() {
        return new Http403ForbiddenEntryPoint();
    }



    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Configuration de la page de login
        http.formLogin().loginPage("/login").permitAll().and().logout().permitAll();
        // La console h2 est réservée à l'administrateur
        http.authorizeRequests().antMatchers("/h2-console/**").hasRole("ADMIN").and().csrf()
                .ignoringAntMatchers("/h2-console/**").and().headers().frameOptions().sameOrigin();
        /*http.antMatcher("/login")
                .authorizeRequests()
                .anyRequest().fullyAuthenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint(forbiddenEntryPoint)
                .and()
                .csrf().disable();*/
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}