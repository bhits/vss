package gov.samhsa.c2s.vss.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CodedConceptRepository extends JpaRepository<CodedConcept, Long> {
    Optional<CodedConcept> findByCodeSystemVersionIdAndCodeNameCode(Long id, String code);
    Optional<CodedConcept> findOneById(Long id);
    Optional<CodedConcept> findByCodeSystemVersion_IdAndCodeName_Code(Long codeSVId, String code);

}