package fi.project.movielist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.project.movielist.domain.User;
import fi.project.movielist.domain.UserRepository;
import fi.project.movielist.domain.Movie;
import fi.project.movielist.domain.MovielistRepository;

@SpringBootApplication
public class MovielistApplication {

	private static final Logger log = LoggerFactory.getLogger(MovielistApplication.class);

	public static void main(String[] args) {
			SpringApplication.run(MovielistApplication.class, args);
	}

	@Bean
	public CommandLineRunner createMovieData(MovielistRepository movieRepository, UserRepository userRepository) {
		return args -> {
			System.out.println("saving movies");

			// creating sample user "user" pwd "user" and "admin" pwd "admin"
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);

			log.info("fetch all movies");
			for (Movie movie : movieRepository.findAll()) {
				log.info(movie.toString());
			}
		};
	}

}
