package gov.samhsa.c2s.vss.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ValueSetRepository extends JpaRepository<ValueSet, Long> {
    List<ValueSet> findAllByCodedConceptsId(Long id);
}