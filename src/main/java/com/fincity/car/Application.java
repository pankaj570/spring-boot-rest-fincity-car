package com.fincity.car;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner{
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    JdbcTemplate jdbcTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
    public void run(String... args) {
        log.info("StartApplication...");
        runJDBC();
    }
	
	void runJDBC() {

		log.info("Creating tables for testing...");

		jdbcTemplate.execute("DROP TABLE cars IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE cars("
				+ "id SERIAL, name VARCHAR(255), model VARCHAR(255), manufactureName VARCHAR(255), manufacturingYear VARCHAR(255), color VARCHAR(255))");
		
		jdbcTemplate.execute("DROP TABLE user IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE user("
				+ "id SERIAL, userName VARCHAR(255), uaserPassword VARCHAR(255), role VARCHAR(255))");
		
		
		jdbcTemplate.batchUpdate(
				"insert into user (userName, uaserPassword, role) VALUES ('pankaj@fincity.com', 'kumar', 'USER')",
				"insert into user (userName, uaserPassword, role) VALUES ('manish@fincity.com', 'kumar', 'USER')",
				"insert into user (userName, uaserPassword, role) VALUES ('raju@fincity.com', 'kumar', 'USER')"
				);
	}
}
