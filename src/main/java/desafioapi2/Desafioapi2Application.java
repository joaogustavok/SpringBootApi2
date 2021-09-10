package desafioapi2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableSpringDataWebSupport
public class Desafioapi2Application {

	public static void main(String[] args) {
		SpringApplication.run(Desafioapi2Application.class, args);
		
		
	

}
}
