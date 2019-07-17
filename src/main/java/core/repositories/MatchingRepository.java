package core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import core.entities.CTMatchingEntity;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Career Tinder
 *
 */

@Repository
public interface MatchingRepository extends JpaRepository<CTMatchingEntity, Long>, CrudRepository<CTMatchingEntity, Long> {

    @Transactional
    @Query(value = "SELECT * FROM ctmatching WHERE company_id = ?1 AND applicant_id = ?2", nativeQuery = true)
    public CTMatchingEntity getMatchRow(Long companyId, Long applicantId);
    
    @Transactional
    @Query(value = "SELECT * from ctmatching where applicant_id = ?1", nativeQuery = true)
    public List<CTMatchingEntity> getRecordsForApplicant(Long applicantId);
    
    @Transactional
    @Query(value = "SELECT * from ctmatching where company_id = ?1", nativeQuery = true)
    public List<CTMatchingEntity> getRecordsForCompany(Long companyId);
    
    @Transactional
    @Query(value = "SELECT COUNT(id) FROM ctmatching WHERE applicant_id = ?1 AND company_id = ?2", nativeQuery = true)
    public int exists(Long applicant_id, Long company_id);
    
    @Transactional
    @Query(value = "select count(*) from ctmatching where company_id = ?1  and company_swipe = 1;", nativeQuery = true)
    public int swipeMatchesExists(Long company_id);
    
    @Transactional
    @Modifying
	@Query(value="DELETE from ctmatching where company_id = ?1", nativeQuery = true)
	public void deleteMatchProfile(Long companyid);
    
    
}