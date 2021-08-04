package edu.home.csvasdatasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication(exclude = {
		JdbcTemplateAutoConfiguration.class,
})
public class CsvasdatasourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsvasdatasourceApplication.class, args);
	}

}
