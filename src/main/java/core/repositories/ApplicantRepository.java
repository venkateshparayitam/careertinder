package core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import core.entities.CTApplicantEntity;
import core.entities.CTUserEntity;;

/*
 * @author: Bora Bejleri
 */

@Repository
public interface ApplicantRepository extends JpaRepository<CTApplicantEntity, Long> {
	
	    @Transactional
	    @Query(value = "SELECT * FROM ctuser WHERE emailid = ?1", nativeQuery = true)
	    public CTApplicantEntity findByEmail(String email);

}
