package pl.coderslab.charity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.coderslab.charity.entity.User;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
	User findByName(String username);
	
	Optional<User> findByEmail(String email);
}
