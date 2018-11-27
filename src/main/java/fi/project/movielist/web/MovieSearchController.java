package fi.project.movielist.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fi.project.movielist.domain.Movie;
import fi.project.movielist.domain.OmdbService;

@RestController
public class MovieSearchController {

	@Autowired
	private OmdbService omdbService;

	/**
	 * REST endpoint to get movies by title from Open movie database.
	 * 
	 * @param title The title (or part of it) to search for
	 * @return The list of movies found in OMDB.
	 */
	@RequestMapping(value = "/search/{title}")
	public List<Movie> searchMovies(@PathVariable("title") String title) {
		String apiKey = "dc613e6e";
		return omdbService.getMoviesByTitle(title, apiKey);
	}
}
