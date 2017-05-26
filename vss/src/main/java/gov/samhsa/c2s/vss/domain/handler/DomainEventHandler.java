package gov.samhsa.c2s.vss.domain.handler;

import gov.samhsa.c2s.vss.domain.CodeSystem;
import gov.samhsa.c2s.vss.domain.CodeSystemVersion;
import gov.samhsa.c2s.vss.domain.ValueSet;
import gov.samhsa.c2s.vss.domain.ValueSetCategory;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler({CodeSystem.class, CodeSystemVersion.class, ValueSetCategory.class, ValueSet.class})
public class DomainEventHandler {

}
