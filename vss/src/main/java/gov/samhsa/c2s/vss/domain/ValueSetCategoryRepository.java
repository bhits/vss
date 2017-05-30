package gov.samhsa.c2s.vss.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ValueSetCategoryRepository extends JpaRepository<ValueSetCategory, Long> {

    Optional<ValueSetCategory> findByCodeName_Code(String code);
    Optional<ValueSetCategory> findById(Long id);

}
