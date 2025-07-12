package com.learnwithsaif.educationHub.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.ignoringRequestMatchers("/saveMsg","/public/**","/api/**","/data-api/**","/eduhub/actuator/**"))
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/dashboard").authenticated()
                
                .requestMatchers("/displayProfile").authenticated()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/student/**").hasRole("STUDENT")
                .requestMatchers("/closeMsg/**").hasRole("ADMIN")
                .requestMatchers("/displayMessages/**").hasRole("ADMIN")
                .requestMatchers("/updateProfile").authenticated()
                .requestMatchers("/data-api/**").authenticated()
                .requestMatchers("/api/**").authenticated()
                .requestMatchers("/", "/home").permitAll()
                .requestMatchers("/holidays/**").permitAll()
                .requestMatchers("/eduhub/actuator/**").hasRole("ADMIN")
                .requestMatchers("/contact").permitAll()
                .requestMatchers("/saveMsg").permitAll()
                .requestMatchers("/courses").permitAll()
                .requestMatchers("/about").permitAll()
                .requestMatchers("/assets/**").permitAll()
                .requestMatchers("/logout").permitAll()
                .requestMatchers("/public/**").permitAll()
                .anyRequest().permitAll()
            )
            .formLogin(loginConfigurer -> loginConfigurer
            .loginPage("/login")                    // our custom login page
            .defaultSuccessUrl("/dashboard")       // where to go after successful login
            .failureUrl("/login?error=true")       // where to go on failed login
            .permitAll()
        )
        .logout(logoutConfigurer -> logoutConfigurer
            .logoutSuccessUrl("/login?logout=true")    // redirect after logout
            .invalidateHttpSession(true)              // clears session data
            .permitAll()
        )
            .httpBasic(Customizer.withDefaults());
        
        http.headers(headersConfigurer -> headersConfigurer
            .frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()));
    
        return http.build();
    }
   
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        
        return new BCryptPasswordEncoder();

    }
    
}