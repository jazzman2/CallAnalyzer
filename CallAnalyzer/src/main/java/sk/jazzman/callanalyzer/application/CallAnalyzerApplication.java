/**
 * 
 */
package sk.jazzman.callanalyzer.application;

import java.io.Serializable;

import org.apache.wicket.Page;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import sk.jazzman.callanalyzer.domain.CallType;
import sk.jazzman.callanalyzer.web.HomePage;
import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;

/**
 * @author jkovalci
 * 
 */
public class CallAnalyzerApplication extends WebApplication implements Serializable, ApplicationContextAware {

	/** Serial id */
	private static final long serialVersionUID = 1L;

	protected static Logger log = LoggerFactory.getLogger(CallAnalyzerApplication.class);

	private ApplicationContext context;

	/**
	 * Init Application
	 */
	@Override
	protected void init() {
		super.init();

		getComponentInstantiationListeners().add(new SpringComponentInjector(this, context));

		Injector.get().inject(this);

		BootstrapSettings bootstrapSettins = new BootstrapSettings();

		Bootstrap.install(get(), bootstrapSettins);

		getApplicationSettings().setUploadProgressUpdatesEnabled(true);

		initConfiguraion();
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}

	private void initConfiguraion() {
		CallType ct = new CallType();
		ct.setId(Long.valueOf(1));
		ct.setName("Incomming");
		ct.persist();
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
	}
}
