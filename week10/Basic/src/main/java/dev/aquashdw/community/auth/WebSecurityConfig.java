package dev.aquashdw.community.auth;

import dev.aquashdw.community.auth.model.CommunityUserDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    public WebSecurityConfig(CommunityUserDetailsService communityUserDetailsService) {
        this.userDetailsService = communityUserDetailsService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**")
                .permitAll()
                .mvcMatchers("/area/**")
                .permitAll()
                .anyRequest()
                .authenticated()
            .and().formLogin().loginPage("/user/login").defaultSuccessUrl("/home").permitAll() // 우선 순위 추가
            .and().logout().logoutUrl("/user/logout").logoutSuccessUrl("/home")
                .deleteCookies("JSEESIONID") // 지울 쿠키 이름
                .invalidateHttpSession(true).permitAll()
            .and().csrf().disable(); // -> 요거 쓰면 POST 쓸 수 있음
    }

    // 이 친구가 없으면 default security password가 생성 됨
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService);
    }
}
