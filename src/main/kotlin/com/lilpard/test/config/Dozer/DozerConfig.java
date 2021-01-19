package com.lilpard.test.config.Dozer;

import org.dozer.DozerBeanMapper;
import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DozerConfig {

//    @Bean(name = "org.dozer.Mapper")
//    public DozerBeanMapper dozer() {
//        List<String> mapingFiles = Arrays.asList("/dozer-mapper.xml");
//        DozerBeanMapper dozerBean = new DozerBeanMapper();
//        dozerBean.setMappingFiles(mapingFiles);
//        return dozerBean;
//    }

    @Bean
    public DozerBeanMapperFactoryBean dozerMapper(@Value("classpath:/dozer-mapper.xml") Resource[] resources) throws IOException {
        DozerBeanMapperFactoryBean dozerBeanMapperFactoryBean = new DozerBeanMapperFactoryBean();
        dozerBeanMapperFactoryBean.setMappingFiles(resources);
        return dozerBeanMapperFactoryBean;
    }
}
