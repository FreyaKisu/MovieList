package fi.project.movielist.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fi.project.movielist.domain.Movie;

@RepositoryRestResource
public interface MovielistRepository extends CrudRepository<Movie, Long> {

	List<Movie> findByTitle(@Param("title") String title);

}
