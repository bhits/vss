package gov.samhsa.c2s.vss.domain;

import gov.samhsa.c2s.vss.domain.valueobject.CodeName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodedConceptRepository extends JpaRepository<CodedConcept, Long> {
    CodedConcept findByCodeNameAndCodeSystemVersionId(CodeName code, Long id);
}