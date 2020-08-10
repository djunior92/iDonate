package br.com.idonate.iDonate;

import br.com.idonate.iDonate.config.property.IDonateProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableConfigurationProperties(IDonateProperty.class)
@EnableAsync
public class IDonateApplication {

	public static void main(String[] args) {
		SpringApplication.run(IDonateApplication.class, args);
		//System.out.println(new BCryptPasswordEncoder().encode("12345678"));
	}

	@Bean(name = "fileExecutor")
	public Executor asyncExecutor() {
		final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(1);
		executor.setMaxPoolSize(3);
		executor.initialize();
		return executor;
	}

}
