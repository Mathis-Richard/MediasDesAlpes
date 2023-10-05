package fr.univtln.cnam.bdd.groupe5.mediasdesalpes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "fr.univtln.cnam.bdd.groupe5.mediasdesalpes", exclude={SecurityAutoConfiguration.class})
@ComponentScan(basePackages = "fr.univtln.cnam.bdd.groupe5.mediasdesalpes.*")
@EntityScan("fr.univtln.cnam.bdd.groupe5.mediasdesalpes.model")
@EnableJpaRepositories(basePackages = "fr.univtln.cnam.bdd.groupe5.mediasdesalpes.repository")
public class MediasDesAlpesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediasDesAlpesApplication.class, args);
	}

}
