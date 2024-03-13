package JBDL63.JDBCTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class JdbcTemplateApplication {
//	public class JdbcTemplateApplication implements CommandLineRunner {
//	@Autowired
//	JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(JdbcTemplateApplication.class, args);
	}

//	@Override
//	public void run(String... args)throws Exception{
//		jdbcTemplate.update("CREATE TABLE student(" +
//				"id int PRIMARY KEY AUTO_INCREMENT," +
//				"name varchar(50)," +
//				"department varchar(50)," +
//				"marks double)"
//		);
//	}
}
