package fi.project.movielist.domain;

import org.springframework.data.repository.CrudRepository;

import fi.project.movielist.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}