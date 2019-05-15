package core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import core.entities.CTUserEntity;

@Repository
public interface UserRepository extends JpaRepository<CTUserEntity, Long> {

}
