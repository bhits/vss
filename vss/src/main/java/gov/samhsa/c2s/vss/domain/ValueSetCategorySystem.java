package gov.samhsa.c2s.vss.domain;

import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "system"))
@Audited
public class ValueSetCategorySystem {
    @Id
    @GeneratedValue
    private Long id;

    private String system;
}
