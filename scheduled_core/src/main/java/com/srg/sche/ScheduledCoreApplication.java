package com.srg.sche;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@Slf4j
@EnableAsync
public class ScheduledCoreApplication {
	public static void main(String[] args) {
		SpringApplication.run(ScheduledCoreApplication.class, args);
	}
}