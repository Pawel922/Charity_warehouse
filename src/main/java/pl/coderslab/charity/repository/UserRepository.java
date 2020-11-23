package pl.coderslab.charity.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pl.coderslab.charity.entity.User;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
	User findByName(String username);
	
	User getByEmail(String email);
	
	Optional<User> findByEmail(String email);
	
	@Query("SELECT u FROM User u JOIN u.roles r WHERE r.name LIKE 'ROLE_USER'")
	List<User> getAllUsers();
	
	@Query("SELECT u FROM User u JOIN u.roles r WHERE r.name LIKE 'ROLE_ADMIN'")
	List<User> getAllAdmins();
}
