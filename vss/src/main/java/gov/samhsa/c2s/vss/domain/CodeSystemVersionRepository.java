package gov.samhsa.c2s.vss.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CodeSystemVersionRepository extends JpaRepository<CodeSystemVersion, Long> {
    Optional<CodeSystemVersion> findTopByCodeSystemCodeSystemOidOrderByVersionOrderDesc(String codeSystemOid);
}