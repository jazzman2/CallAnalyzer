/**
 * 
 */
package sk.jazzman.callanalyzer.application;

import java.io.Serializable;

import org.apache.wicket.Page;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import sk.jazzman.callanalyzer.web.HomePage;
import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;

/**
 * @author jkovalci
 * 
 */
public class CallAnalyzerApplication extends WebApplication implements Serializable {

	/** Serial id */
	private static final long serialVersionUID = 1L;

	/**
	 * Init Application
	 */
	@Override
	protected void init() {
		super.init();

		getComponentInstantiationListeners().add(new SpringComponentInjector(this));

		Injector.get().inject(this);

		BootstrapSettings bootstrapSettins = new BootstrapSettings();

		Bootstrap.install(get(), bootstrapSettins);
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}

}
