package org.apache.ctakes.rest.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <b>@Configuration</b> java configuration file
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * {@code @MapperScan("com.tap4fun.api.mapper")}
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage("org.apache.ctakes.rest.dao");
        return scannerConfigurer;
    }


    /**
     * tool to divide pages
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }


}
