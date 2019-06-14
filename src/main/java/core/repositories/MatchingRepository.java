package core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import core.entities.CTMatchingEntity;

@Repository
public interface MatchingRepository extends JpaRepository<CTMatchingEntity, Long>, CrudRepository<CTMatchingEntity, Long> {

}
