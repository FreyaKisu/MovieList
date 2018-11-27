package fi.project.movielist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fi.project.movielist.domain.MovielistRepository;

@RestController
public class RatingController {
	@Autowired
	private MovielistRepository movieRepo;

	/**
	 * Sets the rating of a movie to a new value.
	 * 
	 * @param id The DB id of the movie.
	 * @param rating The new rating.
	 */
	@RequestMapping(value = "/rate/{id}/{rating}")
	@Transactional
	public void searchMovies(@PathVariable("id") long id, @PathVariable("rating") int rating) {
		movieRepo.findById(id).ifPresent(movie -> {
			movie.setRating(rating);
			movieRepo.save(movie);
		});
	}
}
