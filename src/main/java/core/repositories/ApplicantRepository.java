package core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import core.entities.CTApplicantEntity;;

@Repository
public interface ApplicantRepository extends JpaRepository<CTApplicantEntity, Long> {

}
