package core.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import core.entities.CTApplicantEntity;
import core.entities.CTCompanyEntity;

/*
 * @author: Bora Bejleri
 */

@Repository
public interface ApplicantRepository extends JpaRepository<CTApplicantEntity, Long>, PagingAndSortingRepository<CTApplicantEntity, Long> {
	
	    @Transactional
	    @Query(value = "SELECT * FROM ctapplicant WHERE emailid = ?1", nativeQuery = true)
	    public CTApplicantEntity findByEmail(String email);
	    
	    @Transactional
		@Query(value = "SELECT * from ctapplicant where applicant_id = ?1", nativeQuery = true)  
		public CTApplicantEntity getApplicantById(Long applicant_id);
	   
}
