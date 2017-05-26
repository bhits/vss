package gov.samhsa.c2s.vss.service.dto;

import gov.samhsa.c2s.vss.domain.CodeSystem;
import gov.samhsa.c2s.vss.domain.CodedConcept;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeSystemVersionDto{

    private Long id;

    private String versionName;

    private int versionOrder;

    private String description;

    protected String codeSystemCodeNameCode;
}