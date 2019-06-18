package core.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import core.entities.CTCompanyEntity;
import core.entities.CTMatchingEntity;


/*
 * CompanyRepository implementation for JPA transaction in Company Entity
 * @author: Pravin Garad
 */

@Repository
public interface CompanyRepository extends JpaRepository<CTCompanyEntity, Long> {

	 @Transactional
	 @Query(value = "SELECT * from ctcompany where user_company_id = ?1", nativeQuery = true)  
	 public ArrayList<CTCompanyEntity> getListOfJobs(Long user_id);
	 
	 @Transactional
	 @Query(value = "SELECT * from ctcompany where job_id = ?1", nativeQuery = true)  
	 public CTCompanyEntity getJobById(int job_id);
	 
	 @Transactional
	 @Query(value = "SELECT * from ctcompany where company_id = ?1", nativeQuery = true)  
	 public CTCompanyEntity getCompanyById(Long company_id);
	 
	 @Transactional
	 @Query(value = "SELECT * from ctcompany where user_company_id = ?1", nativeQuery = true)
	 public List<CTCompanyEntity> getMatchesForCompany(Long companyId);
	 
	 
}
