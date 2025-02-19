package com.example.spring_project1.config;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf->csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .cors(cors->cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .authorizeHttpRequests(authz->authz.requestMatchers("/","/loginPage","/logout","/noticeCheckPage","/registerPage","/menu/all")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST,"/login","/register").permitAll()
                        .requestMatchers("/resources/**","/WEB-INF/**").permitAll()
                        .requestMatchers("/noticeAddPage","/noticeModifyPage").hasAnyAuthority("ADMIN","MANAGER")
                        .requestMatchers(HttpMethod.POST,"/menu/add").hasAnyAuthority("ADMIN","MANAGER")
                        .requestMatchers(HttpMethod.POST,"menu/update").hasAnyAuthority("ADMIN","MANAGER")
                        .requestMatchers(HttpMethod.DELETE,"/menu/delete").hasAnyAuthority("ADMIN","MANAGER")
                        .anyRequest().authenticated() //이외 페이지는 로그인 해야 접근가능, 자동으로 로그인 페이지로 리다이렉팅.
                )
                .formLogin(
                login->login.loginPage("/loginPage")
                        .loginProcessingUrl("/login")
                        .failureUrl("/loginPage?error=true")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .successHandler(authenticationSuccessHandler())
                        .permitAll()
                )
                .logout(logout->logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/")//로그아웃성공후 이 url로 리다이렉팅
                        .invalidateHttpSession(true)//세션무효화
                        .deleteCookies("JSESSIONID")//쿠키삭제
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SimpleUrlAuthenticationSuccessHandler() {

            // 로그인 성공 시.(세션 권한 기능)
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                HttpSession session = request.getSession();
                boolean isManager = authentication.getAuthorities().stream()
                        .anyMatch(grantedAuthority ->
                                grantedAuthority.getAuthority().equals("ADMIN") ||
                                        grantedAuthority.getAuthority().equals("MANAGER")
                        );
                if(isManager) {
                    session.setAttribute("MANAGER", true);
                }
                session.setAttribute("username", authentication.getName()); // 세션에 로그인 아이디를 저장
                session.setAttribute("isAuthenticated", true); // 세션에 로그인 여부를 저장
                response.sendRedirect(request.getContextPath()+"/");
                super.onAuthenticationSuccess(request,response, authentication);
            }
        };
    }

    // 암호화를 위해 꼭 필요함.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080","https://localhost:8080"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization","Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }

}
