package com.security.securitytestdemo.config;

import com.security.securitytestdemo.handler.MyAccessDeniedHandler;
import com.security.securitytestdemo.handler.MyAuthenticationFailureHandler;
import com.security.securitytestdemo.handler.MyAuthenticationSuccessHandler;
import com.security.securitytestdemo.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PersistentTokenRepository persistentTokenRepository;

    @Autowired
    private UserDetailServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //自定义跳转页面
        http.formLogin()
                //自定义登录页面
                .usernameParameter("username123")
                .passwordParameter("password123")
                .loginPage("/login.html")
                //必须和表单提交的接口一样，会去执行自定义登陆逻辑
                .loginProcessingUrl("/login")
                //登录成功跳转页面,只接受POST请求
                .successForwardUrl("/toMain")
//                .successHandler(new MyAuthenticationSuccessHandler("http://www.baidu.com"))
                //登录失败页面
                .failureForwardUrl("/toError");
//                .failureHandler(new MyAuthenticationFailureHandler("/error.html"));
        //授权
        http.authorizeRequests()
                .antMatchers("/login.html").permitAll()
                .antMatchers("/error.html").permitAll()
                //放行静态资源
//                .antMatchers("/css/**", "/js/**", "/images/**").permitAll()
                //放行后缀.png
//                .antMatchers("/**/*.png").permitAll()
                //放行后缀.png，正则表达式
//                .regexMatchers(".+[.]png").permitAll()
                //请求指定方法
//                .regexMatchers(HttpMethod.POST, "/demo").permitAll()
                //mvc匹配
//                .mvcMatchers("/demo").servletPath("/xxxx").permitAll()等价于
//                antMatchers("/xxxx/demo").permitAll()
                //所有必须认证

                //权限控制
//                .antMatchers("/main1.html").hasAnyAuthority("admin")
                //角色控制
//                .antMatchers("/main1.html").hasRole("owner")
                //ip控制
//                .antMatchers("/main1.html").hasIpAddress("127.0.0.1")
                //任何请求都可以
                .anyRequest().authenticated();
                //自定义access方法
//                .anyRequest().access("@myServiceImpl.hasPermission(request, authentication)");
        //权限不足的处理器
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);

        http.rememberMe()
                .tokenRepository(persistentTokenRepository)
//                .rememberMeParameter()
                //超时时间，默认两周
                .tokenValiditySeconds(60)
                .userDetailsService(userDetailsService);

        //关闭csrf
        http.csrf().disable();

        //退出
        http.logout().logoutSuccessUrl("/login.html");

    }

    @Bean
    public PasswordEncoder getPw() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        //设置数据源
        jdbcTokenRepository.setDataSource(dataSource);
        //自动建表,第一次启动时开启，第二次注释
//        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }
}
