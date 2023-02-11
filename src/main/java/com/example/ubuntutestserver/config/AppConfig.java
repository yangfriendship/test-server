package com.example.ubuntutestserver.config;

import com.example.ubuntutestserver.properties.ConfigMapProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(ConfigMapProperties.class)
public class AppConfig {

}
