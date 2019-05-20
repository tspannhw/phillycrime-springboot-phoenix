package com.dataflowdeveloper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.apache.phoenix.jdbc.PhoenixDriver;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class HBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(HBaseApplication.class, args);
	}

	@Configuration
	@Profile("default")
	static class LocalConfiguration {
		Logger logger = LoggerFactory.getLogger(LocalConfiguration.class);

		@Value("${purl}")
	    private String databaseUri;
	    	    
		@Bean
		public Connection connection() {
		        Connection con = null;
				try {
					logger.error(databaseUri);
					con = DriverManager.getConnection(databaseUri);
				} catch (Throwable e) {
					e.printStackTrace();
					logger.error("Connection fail: ", e);
				}

			logger.error("Initialized hbase");
			
			return con;
		}
	}
}
