package gov.samhsa.c2s.vss.domain.handler;

import gov.samhsa.c2s.vss.domain.ValueSet;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(ValueSet.class)
public class ValueSetEventHandler {

}
