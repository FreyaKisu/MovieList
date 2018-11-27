package fi.project.movielist.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonSetter;

@Component
public class OmdbService {

	private static final String DASH = "–";

	public List<Movie> getMoviesByTitle(String title, String apiKey) {

		RestTemplate restTemplate = new RestTemplate();

		// Get the movie info from OMdb REST api

		SearchResults sr = restTemplate.getForObject(
				"http://www.omdbapi.com/?i=tt3896198&apikey=" + apiKey + "&s=" + title, SearchResults.class);
		// Map the resulting JSON to Movie
		List<Movie> movies = new ArrayList<>();
		if (sr.getSearch() == null) {
			return movies;
		}

		for (MovieResult movieResult : sr.getSearch()) {
			Movie movie = new Movie();
			movie.setTitle(movieResult.getTitle());
			movie.setType(getType(movieResult.getType()));
			movie.setPoster(movieResult.getPoster());
			// The year field can contain either the production year or the year span in
			// format 1991–1998 (note, not hyphen, but dash).
			movie.setYear(Integer.parseInt(movieResult.getYear().split(DASH)[0]));
			movie.setGenre("(add genre)");
			// for debug
			System.out.println(movie);
			movies.add(movie);
		}
		return movies;
	}

	MovieType getType(String type) {
		if ("movie".equals(type)) {
			return MovieType.MOVIE;
		}
		if ("series".equals(type)) {
			return MovieType.SERIES;
		}
		if ("episode".equals(type)) {
			return MovieType.EPISODE;
		}
		return MovieType.MOVIE;
	}

	private static class SearchResults {
		List<MovieResult> Search;
		String totalResults, response;

		public List<MovieResult> getSearch() {
			return Search;
		}

		@JsonSetter("Search")
		public void setSearch(List<MovieResult> search) {
			this.Search = search;
		}

		public String getTotalResults() {
			return totalResults;
		}

		public void setTotalResults(String totalResults) {
			this.totalResults = totalResults;
		}

		public String getResponse() {
			return response;
		}

		@JsonSetter("Response")
		public void setResponse(String response) {
			this.response = response;
		}

	}

	private static class MovieResult {
		String title, imdbID, type, poster, year;

		public String getTitle() {
			return title;
		}

		@JsonSetter("Title")
		public void setTitle(String title) {
			this.title = title;
		}

		public String getImdbID() {
			return imdbID;
		}

		@JsonSetter("imdbID")
		public void setImdbID(String imdbID) {
			this.imdbID = imdbID;
		}

		public String getType() {
			return type;
		}

		@JsonSetter("Type")
		public void setType(String type) {
			this.type = type;
		}

		public String getPoster() {
			return poster;
		}

		@JsonSetter("Poster")
		public void setPoster(String poster) {
			this.poster = poster;
		}

		public String getYear() {
			return year;
		}

		@JsonSetter("Year")
		public void setYear(String year) {
			this.year = year;
		}

	}
}
