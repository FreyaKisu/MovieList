package fi.project.movielist;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.project.movielist.domain.Movie;
import fi.project.movielist.domain.MovieType;
import fi.project.movielist.domain.MovielistRepository;
import fi.project.movielist.domain.User;
import fi.project.movielist.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest

public class MovieRepositoryTest {

	@Autowired
	private MovielistRepository repository;
	@Autowired
	private UserRepository userRepository;

	@Test
	public void findByTitle_movieFoundInDatabase_correctMovieReturned() {
		saveMovie();
		List<Movie> movies = repository.findByTitle("star wars");

		assertThat(movies).hasSize(1);
		assertThat(movies.get(0).getTitle()).isEqualTo("star wars");
		assertThat(movies.get(0).getPoster()).isEqualTo("url to poster");
		assertThat(movies.get(0).getYear()).isEqualTo(1234);
		assertThat(movies.get(0).getType()).isEqualTo(MovieType.MOVIE);
		assertThat(movies.get(0).getPoster()).isEqualTo("url to poster");
		assertThat(movies.get(0).getRating()).isEqualTo(5);
		assertThat(movies.get(0).getGenre()).isEqualTo("sci-fi");
		assertThat(movies.get(0).getUser().getUsername()).isEqualTo("me");
		assertThat(movies.get(0).getUser().getPasswordHash()).isEqualTo("strong password");
		assertThat(movies.get(0).getUser().getRole()).isEqualTo("queen");
	}

	@Test
	public void createNewMovie() {
		Movie movie = saveMovie();
		assertThat(movie.getId()).isNotNull();
	}

	private Movie saveMovie() {
		Movie movie = new Movie();
		movie.setGenre("sci-fi");
		movie.setPoster("url to poster");
		movie.setRating(5);
		movie.setTitle("star wars");
		movie.setYear(1234);
		movie.setType(MovieType.MOVIE);
		User user = new User("me", "strong password", "queen");
		userRepository.save(user);
		movie.setUser(user);

		repository.save(movie);
		return movie;
	}

}
