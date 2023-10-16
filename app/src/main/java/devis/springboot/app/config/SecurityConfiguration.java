package devis.springboot.app.config;

import devis.springboot.app.services.MemberInfoDetailsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    }





    @Bean
    public SecurityFilterChain securityFilterChainAdmin(HttpSecurity http) throws Exception {
        http.csrf(csrf->csrf.disable()).securityMatcher("/app/admin/**")
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().hasRole("ADMIN")
                ).formLogin(form->form.loginPage("/app/signin").loginProcessingUrl("/userLogin")
                        .defaultSuccessUrl("/app/admin/dashboard").permitAll());
        return http.build();
    }
    /*
    @Bean
    public SecurityFilterChain securityFilterChainUser(HttpSecurity http) throws Exception {
        http.csrf(csrf->csrf.disable()).securityMatcher("/app/user/**")
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().hasRole("USER")
                ).formLogin(form->form.loginPage("/app/signin").loginProcessingUrl("/login")
                        .defaultSuccessUrl("/app/user/dashboard").permitAll());
        return http.build();
    }*/

    @Bean
    public SecurityFilterChain securityFilterChainApp(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .securityMatcher("/app/**")
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll()
                );

        return http.build();
    }
@Bean
    public UserDetailsService userDetailsService(){

    return new MemberInfoDetailsService();
}
@Bean
    public AuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService());
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
}
}
