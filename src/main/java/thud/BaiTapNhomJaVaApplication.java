package thud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import thud.entity.Days.Type;
import thud.entity.Days;
import thud.repository.DaysRepository;



@SpringBootApplication
@EnableAutoConfiguration(exclude = {
	    org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
	})
public class BaiTapNhomJaVaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaiTapNhomJaVaApplication.class, args);
	}
	
	  @Bean
	  public CommandLineRunner dataLoader(DaysRepository repo) {
	    return new CommandLineRunner() {
	      @Override
	      public void run(String... args) throws Exception {
	        repo.save(new Days("FLTO", "Flour Tortilla", Type.Sunday));
	        repo.save(new Days("COTO", "Corn Tortilla", Type.Monday));
	        repo.save(new Days("GRBF", "Ground Beef", Type.Wednesday));
	        repo.save(new Days("CARN", "Carnitas", Type.Tuesday));
	        repo.save(new Days("TMTO", "Diced Tomatoes", Type.Saturday));
	        repo.save(new Days("LETC", "Lettuce", Type.Friday));
	        repo.save(new Days("CHED", "Cheddar", Type.Thursday));
	        repo.save(new Days("JACK", "Monterrey Jack", Type.Thursday));
	        repo.save(new Days("SLSA", "Salsa", Type.Tuesday));
	        repo.save(new Days("SRCR", "Sour Cream", Type.Saturday));
	      }
	    };
	  }

}
