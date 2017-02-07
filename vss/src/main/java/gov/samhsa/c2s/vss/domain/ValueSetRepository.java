package gov.samhsa.c2s.vss.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ValueSetRepository extends JpaRepository<ValueSet, Long> {
    List<ValueSet> findAllByCodedConceptsId(Long id);
}