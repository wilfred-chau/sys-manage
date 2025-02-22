package com.example.sysmanage.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 创建消息转换器
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        // 创建 ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        // 设置 ObjectMapper
        converter.setObjectMapper(objectMapper);
        // 将转换器添加到转换器列表的第一个位置
        converters.add(0, converter);
    }
} 