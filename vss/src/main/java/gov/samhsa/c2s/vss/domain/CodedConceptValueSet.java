package gov.samhsa.c2s.vss.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class CodedConceptValueSet {

    @EmbeddedId
    CodedConceptValueSetId id;

    @ManyToOne
    @JoinColumn(name = "fk_coded_concept", insertable = false, updatable = false)
    private CodedConcept codedConcept;

    @ManyToOne
    @JoinColumn(name = "fk_value_set", insertable = false, updatable = false)
    private ValueSet valueSet;

    @Embeddable
    @Data
    public static class CodedConceptValueSetId implements Serializable {

        @Column(name = "fk_coded_concept")
        protected Long codedConceptId;

        @Column(name = "fk_value_set")
        protected Long valueSetId;
    }
}