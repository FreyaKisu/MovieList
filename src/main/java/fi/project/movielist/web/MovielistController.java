package fi.project.movielist.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.project.movielist.domain.Movie;
import fi.project.movielist.domain.MovielistRepository;
import fi.project.movielist.domain.OmdbService;

@Controller
public class MovielistController {
	@Autowired
	private MovielistRepository movieRepo;

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@GetMapping("/movielist")
	public String index(Model model) {
		model.addAttribute("movies", movieRepo.findAll());
		return "movielist";
	}

	@RequestMapping(value = "/add")
	public String addMovie(Model model) {
		model.addAttribute("movie", new Movie());
		return "addmovie";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Movie movie) {
		movieRepo.save(movie);
		return "redirect:movielist";
	}

	/**
	 * Thymeleaf endpoint for deleting a movie.
	 * 
	 * @param movieId
	 *            The DB id for the movie to delete.
	 * @param model
	 *            The thymeleaf model.
	 * @return The template name.
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteMovie(@PathVariable("id") Long movieId, Model model) {
		movieRepo.deleteById(movieId);
		return "redirect:../movielist";
	}

	@RequestMapping(value = "/edit/{id}")
	public String editMovie(@PathVariable("id") Long movieId, Model model) {
		model.addAttribute("movie", movieRepo.findById(movieId));
		return "editmovie";
	}

	// RESTful service to get all movies
	@RequestMapping(value = "/movies", method = RequestMethod.GET)
	public @ResponseBody List<Movie> movieListRest() {
		return (List<Movie>) movieRepo.findAll();
	}

	// RESTful service to get movie by id
	@RequestMapping(value = "/movie/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Movie> findMovieRest(@PathVariable("id") Long movieId) {
		return movieRepo.findById(movieId);
	}
}
