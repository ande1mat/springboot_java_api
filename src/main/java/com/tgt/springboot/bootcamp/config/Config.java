package com.tgt.springboot.bootcamp.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Created on 4/4/18.
 */
@Configuration
public class Config {

    @Bean
    @Primary
    public ObjectMapper mapper() {
        return new ObjectMapper();
    }

}
