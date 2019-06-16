package core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import core.entities.CTMatchingEntity;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MatchingRepository extends JpaRepository<CTMatchingEntity, Long>, CrudRepository<CTMatchingEntity, Long> {

    @Transactional
    @Query(value = "SELECT * FROM ctmatching WHERE company_id = ?1 AND applicant_id = ?2", nativeQuery = true)
    public CTMatchingEntity getMatchRow(Long companyId, Long applicantId);
}