package gov.samhsa.c2s.vss.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CodedConceptRepository extends JpaRepository<CodedConcept, Long> {
    CodedConcept findByCodeNameCodeAndCodeSystemVersionId(String code, Long id);
}