package pl.coderslab.charity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pl.coderslab.charity.entity.Donation;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

	List<Donation> findAllByUserId(long id);
	
	@Query("SELECT d FROM Donation d JOIN FETCH d.institution i WHERE i.id = ?1")
	List<Donation> findAllByInstitutionId(long id);
	
	@Query("SELECT d FROM Donation d WHERE d.user.id = ?1 AND d.status = ?2")
	List<Donation> findAllByUserIdSortByStatus(long id, long status);
	
	@Query("SELECT d FROM Donation d WHERE d.user.id = ?1 ORDER BY d.pickUpDate")
	List<Donation> findAllByUserIdSortByPickUpDate(long id);
	
	@Query("SELECT d FROM Donation d WHERE d.user.id = ?1 ORDER BY d.receiveDate")
	List<Donation> findAllByUserIdSortByReceiveDate(long id);
}
