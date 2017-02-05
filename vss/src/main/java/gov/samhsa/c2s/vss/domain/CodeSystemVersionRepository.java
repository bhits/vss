package gov.samhsa.c2s.vss.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodeSystemVersionRepository extends JpaRepository<CodeSystemVersion, Long> {
    List<CodeSystemVersion> findAllByCodeSystemCodeSystemOidOrderByVersionOrderDesc(String codeSystemOid);
}