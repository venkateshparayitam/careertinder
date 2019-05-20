package core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import core.entities.CTCompanyEntity;


/*
 * CompanyRepository implementation for JPA transaction in Company Entity
 * @author: Pravin Garad
 */

@Repository
public interface CompanyRepository extends JpaRepository<CTCompanyEntity, Long> {

	
}
