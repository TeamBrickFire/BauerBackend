package eu.brickfire.bauerntinder.module;

import com.google.inject.PrivateModule;
import eu.brickfire.bauerntinder.service.PersonService;
import eu.brickfire.bauerntinder.service.PersonServiceImpl;

public class ServiceBindingModule extends PrivateModule {

    @Override
    protected void configure() {
        this.install(new MariaDBConfigModule());

        this.bind(PersonService.class).to(PersonServiceImpl.class);
        this.expose(PersonService.class);

    }

}