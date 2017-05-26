package gov.samhsa.c2s.vss.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ValueSetCategorySystemRepository extends JpaRepository<ValueSetCategorySystem, Long> {

    Optional<ValueSetCategorySystem> findBySystem(String system);

}
