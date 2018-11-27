package fi.project.movielist;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import fi.project.movielist.domain.Movie;
import fi.project.movielist.domain.OmdbService;

/**
 * Note! This requires active internet connection as it makes the api call.
 */
public class OmdbServiceTest {
	@Test
	public void testThatItWorks() {
		List<Movie> movies = new OmdbService().getMoviesByTitle("Batman", "dc613e6e");
		assertThat(movies.size()).isGreaterThan(0);
	}
}
