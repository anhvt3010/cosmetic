package com.anhvt.cosmetic.Service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
    @EnableWebSecurity
    public class AuthService extends WebSecurityConfigurerAdapter{
        @Bean
        public UserDetailsService userDetailsService(){
            return new UserDetailService();
        }
        @Bean
        public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }
        @Bean
        public DaoAuthenticationProvider authenticationProvider(){
            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
            authProvider.setUserDetailsService(userDetailsService());
            authProvider.setPasswordEncoder(passwordEncoder());
            return authProvider;
        }
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception{
            auth.authenticationProvider(authenticationProvider());
        }
        @Override
        protected void configure(HttpSecurity httpSec) throws  Exception{
            httpSec.authorizeRequests((req)->req
//                                 ,"/api/v1/**"
                                .antMatchers("/resources/**","/admin/login").permitAll()
                                .anyRequest().authenticated()
                            )
                    .formLogin((frm)->frm.loginPage("/admin/login")
                            .usernameParameter("username")
                            .passwordParameter("password")
                            .successForwardUrl("/admin/redirect")
                            .permitAll()
                    )
                    .logout((logout)->logout.clearAuthentication(true).permitAll())
                    .csrf().disable();
        }
    }






















//        List<Role> roles = roleRepository.findAll();
//        for (Role role : roles) {
//            List<Page> pages = pageRepository.findPagesPerRole(role.getId());
//            List<String> pageslist = new ArrayList<>();
//            for (Page page : pages) {
//                pageslist.add(page.getUrl());
//            }
//            String[] authorities = pageslist.toArray(new String[0]);
//            http.authorizeRequests().antMatchers(authorities).hasAuthority(role.getAuthority().toString());
//        }


//.successHandler(new AuthenticationSuccessHandler() {
//@Override
//public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//        Authentication authentication) throws IOException, ServletException {
//        // run custom logics upon successful login
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        String username = userDetails.getUsername();
//        System.out.println("The user " + username + " has logged in.");
//        response.sendRedirect(request.getContextPath());
//        }
//        }
