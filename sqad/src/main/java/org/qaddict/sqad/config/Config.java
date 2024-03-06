package org.qaddict.sqad.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import foundation.jpa.querydsl.EntityConverter;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;

@Configuration
public class Config {

    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }

    @Bean
    public EntityConverter entityConverter(ConversionService conversionService) {
        return conversionService::convert;
    }

}
