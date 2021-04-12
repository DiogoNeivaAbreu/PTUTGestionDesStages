package gestionStages;

import lombok.extern.slf4j.Slf4j;
import gestionStages.service.UserService;
import gestionStages.storageservice.StorageProperties;
import gestionStages.storageservice.StorageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableWebSecurity // Autorise les annotations de sécurité sur les contrôleurs
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableConfigurationProperties(StorageProperties.class)
@Slf4j
public class WebApplication {
    final
    UserService userService;

    public WebApplication(UserService userService) {
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @PostConstruct
    // Quand on lance l'application, on crée un administrateur (cf. application.properties)
    private void initialize() {
        userService.initializeRolesAndAdmin();
    }
    
    @Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}

}
