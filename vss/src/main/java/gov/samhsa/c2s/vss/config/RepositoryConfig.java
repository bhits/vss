package gov.samhsa.c2s.vss.config;

import gov.samhsa.c2s.vss.domain.CodeSystem;
import gov.samhsa.c2s.vss.domain.CodeSystemVersion;
import gov.samhsa.c2s.vss.domain.CodedConcept;
import gov.samhsa.c2s.vss.domain.ValueSet;
import gov.samhsa.c2s.vss.domain.ValueSetCategory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;


@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(CodeSystem.class);
        config.exposeIdsFor(CodeSystemVersion.class);
        config.exposeIdsFor(CodedConcept.class);
        config.exposeIdsFor(ValueSetCategory.class);
        config.exposeIdsFor(ValueSet.class);
    }
}

