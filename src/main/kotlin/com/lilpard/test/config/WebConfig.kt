package com.lilpard.test.config

import org.springframework.boot.SpringBootConfiguration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry




@SpringBootConfiguration
class WebConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        //所有请求都允许跨域，使用这种配置方法就不能在 interceptor 中再配置 header 了
        registry.addMapping("/**")
            .allowCredentials(true)
            .allowedOrigins("http://localhost:8066")
            .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
            .allowedHeaders("*")
            .maxAge(3600)
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/file/codes/**").addResourceLocations("classpath:/static/codes/")
    }
}
