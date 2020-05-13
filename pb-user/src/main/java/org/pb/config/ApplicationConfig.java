package org.pb.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/* This file will be moved to config library that will be created later */

@Slf4j
@Configuration
@EnableConfigurationProperties({ApplicationProperties.class})
public class ApplicationConfig {

}
