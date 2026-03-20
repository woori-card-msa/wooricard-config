package com.wooricard.wooricard_config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer    // Config 서버 역할 활성화
@EnableDiscoveryClient // 유레카 클라이언트로 등록
public class WooricardConfigApplication {
	public static void main(String[] args) {
		SpringApplication.run(WooricardConfigApplication.class, args);
	}
}
