package com.ashish.pack.Config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Configuration
@Profile("dev")
@Slf4j
public class AppConfig {

    @PostConstruct
    public void constructed(){
        log.info("AppConfig constructed");
    }
}
