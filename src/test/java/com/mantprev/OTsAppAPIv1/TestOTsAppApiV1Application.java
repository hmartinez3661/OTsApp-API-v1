package com.mantprev.OTsAppAPIv1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

import com.mantprev.OTsAppApiV1Application;

@TestConfiguration(proxyBeanMethods = false)
public class TestOTsAppApiV1Application {

	@Bean
	@ServiceConnection
	MySQLContainer<?> mysqlContainer() {
		return new MySQLContainer<>(DockerImageName.parse("mysql:latest"));
	}

	public static void main(String[] args) {
		SpringApplication.from(OTsAppApiV1Application::main).with(TestOTsAppApiV1Application.class).run(args);
	}

}
