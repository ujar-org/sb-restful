package com.iqkv.boot.mvc.rest;

import com.iqkv.boot.info.BuildInfoConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({BuildInfoConfig.class,
    DefaultRestfulErrorHandler.class,
    DateTimeFormatConfiguration.class,
    JacksonConfiguration.class})
public class WebConfig {

}
