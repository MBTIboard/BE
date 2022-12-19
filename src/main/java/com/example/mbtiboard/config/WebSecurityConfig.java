package com.example.mbtiboard.config;



import com.example.mbtiboard.jwt.JwtAuthFilter;
import com.example.mbtiboard.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
//  스프링 시큐리티(Spring Seurity) 프레임워크에서 제공하는 클래스 중 하나로
// 비밀번호를 암호화하는 데 사용할 수 있는 메서드를 가진 클래스 https://kimvampa.tistory.com/129
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration는 설정파일을 만들기 위한 에노테이션 또는 Bean을 등록하기 위한 에노테이션이다.
// @Bean은 외부 라이브러리의 객체들을 빈으로 등록할 때 사용한다.
@Configuration
@RequiredArgsConstructor // final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
@EnableGlobalMethodSecurity(securedEnabled = true) // @Secured 어노테이션 활성화
public class WebSecurityConfig {
    // 예전 상속했던 WebSecurityConfigurerAdapter는 2.7 이상의 스프링부트에서 지원하지 않는다.

    // Jwt 생성자
    private final JwtUtil jwtUtil;

    @Bean
    public PasswordEncoder passwordEncoder() {

//  스프링 시큐리티(Spring Seurity) 프레임워크에서 제공하는 클래스 중 하나로
//  BCrypt라는 함수를 사용하여 비밀번호를 암호화하는 구현체이다.
//  Config 객체 내부에서 PasswordEncoder의 구현체로 BCryptPasswordEncoder를 지정해주었으니
//  이를 스프링 프레임워크에서 사용하도록 스프링 빈(Bean)으로 등록해주어야 한다.
//  사용은 UserService에서 사용했다.
        return new BCryptPasswordEncoder();

    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // h2-console 사용 및 resources 접근 허용 설정
        return (web) -> web.ignoring()
                .requestMatchers(PathRequest.toH2Console())
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        // 기본 설정인 Session 방식은 사용하지 않고 JWT 방식을 사용하기 위한 설정
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().antMatchers("/api/user/**").permitAll()
                .antMatchers("/api/post/**").permitAll()
                .anyRequest().authenticated()
                // JWT 인증/인가를 사용하기 위한 설정
                .and().addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}