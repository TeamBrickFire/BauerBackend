package eu.brickfire.bauerntinder.module;

import com.google.inject.AbstractModule;

public class BauernTinderModule extends AbstractModule {

	@Override
	public void configure() {
		this.install(new ServiceBindingModule());
	}

}
