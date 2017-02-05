package gov.samhsa.c2s.vss.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodedConceptValueSetRepository extends JpaRepository<CodedConceptValueSet, CodedConceptValueSet.CodedConceptValueSetId> {
    List<CodedConceptValueSet> findAllByCodedConcept(Long id);
}