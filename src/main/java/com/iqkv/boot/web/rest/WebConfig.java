package com.iqkv.boot.web.rest;

import com.iqkv.boot.build.BuildInfoConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({BuildInfoConfig.class,
    DefaultRestfulErrorHandler.class,
    DateTimeFormatConfiguration.class,
    JacksonConfiguration.class})
public class WebConfig {

}
