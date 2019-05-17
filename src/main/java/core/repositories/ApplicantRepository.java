package core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import core.entities.CTApplicantEntity;;

/*
 * ApplicationRepository implementation for JPA transaction in Applicant Entity
 * @author: Pravin Garad
 */

@Repository
public interface ApplicantRepository extends JpaRepository<CTApplicantEntity, Long> {

}
