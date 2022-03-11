package com.qin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author 秦家乐
 * @date 2022/3/6 15:42
 */

/*
* 这个方式结局跨越是最麻烦的，一般不怎么用
* 留着当工具类使用，需要了可以拿出来
*
* */

//@Configuration
public class CorsConfig {

    
    private CorsConfiguration buildConfig(){

        CorsConfiguration configuration=new CorsConfiguration();
        //设置属性
        //允许跨域请求的地址,*表示所有
        configuration.addAllowedOriginPattern("*");
        //配置跨域的请求头
        configuration.addAllowedHeader("*");
        //配置跨域的请求的方法
        configuration.addAllowedMethod("*");
        //表示跨域请求的时候，是否使用的是同一个session
        //如果不配置的话，后面可能每一个请求都是一个新的session
        configuration.setAllowCredentials(true);
        return  configuration;

    }


    @Bean
    public CorsFilter corsFilter(){

        //小心导错包
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",buildConfig());
        return new CorsFilter(source);


    }
    
    
}
