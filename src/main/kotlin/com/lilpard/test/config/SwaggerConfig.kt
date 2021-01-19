package com.lilpard.test.config

import com.sun.tools.javac.resources.version
import io.swagger.models.HttpMethod
import io.swagger.v3.oas.models.Operation
import io.swagger.v3.oas.models.PathItem
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors.regex
import springfox.documentation.oas.annotations.EnableOpenApi
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.builders.PathSelectors

import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact




@EnableOpenApi
@Configuration
class SwaggerConfig {
    @Bean
    fun creatRestApi():Docket {
        return Docket(DocumentationType.OAS_30)
            .apiInfo(apiInfo()).enable(true)
            .select()
            //apis： 添加swagger接口提取范围
            .apis(RequestHandlerSelectors.basePackage("com.lilpard.test"))
            //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
            .paths(PathSelectors.any())
            .build()
    }

    private fun apiInfo():ApiInfo{
        return ApiInfoBuilder()
            .title("Lilpard超市货物管理系统项目接口文档")
            .description("Lilpard超市货物管理系统是一个功能全面，简单易用的货物管理系统，但是它除了可以进行货物管理，还可以进行简单的账目情况汇报和工作人员信息的处理，并且集成vip客户功能。")
            .contact(Contact("Lilpard", "http://keker.cyou", "lilpardmk@gmail.com"))
            .build()
    }
}
