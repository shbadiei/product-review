package com.codechallenge.product.common.config;

import com.codechallenge.product.common.config.serializer.DateSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.Date;
import java.util.List;

@Configuration
public class MainConfig extends WebMvcConfigurationSupport {

    /**
     * for serialization of org.bson.types.ObjectId and java.util.Date
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializerByType(ObjectId.class, new ToStringSerializer());
        builder.serializerByType(Date.class, new DateSerializer());
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(builder.build());
        converters.add(converter);
    }


}

