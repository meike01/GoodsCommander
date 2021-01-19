package com.lilpard.test

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import springfox.documentation.oas.annotations.EnableOpenApi
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder







@SpringBootApplication
@EnableOpenApi
@EnableJpaRepositories("com.lilpard.test.DAO")
@EntityScan("com.lilpard.test.entity")
class TestApplication
{

//	@Bean
//	open fun bcryptPasswordEncoder(): BCryptPasswordEncoder? {
//		return BCryptPasswordEncoder()
//	}

}

fun main(args: Array<String>) {
	runApplication<TestApplication>(*args)
}


