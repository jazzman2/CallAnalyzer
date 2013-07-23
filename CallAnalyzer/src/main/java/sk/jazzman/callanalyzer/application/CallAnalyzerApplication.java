/**
 * 
 */
package sk.jazzman.callanalyzer.application;

import java.io.Serializable;
import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.wicket.Page;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import sk.jazzman.callanalyzer.domain.CallType;
import sk.jazzman.callanalyzer.domain.InfoType;
import sk.jazzman.callanalyzer.web.HomePage;
import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;

/**
 * @author jkovalci
 * 
 */
@Component(value = "wicketApplication")
public class CallAnalyzerApplication extends WebApplication implements Serializable, ApplicationContextAware {

	/** Serial id */
	private static final long serialVersionUID = 1L;

	protected static Logger log = LoggerFactory.getLogger(CallAnalyzerApplication.class);

	@Autowired
	private ApplicationContext context;

	/**
	 * Return instance of call analyzer application
	 * 
	 * @return
	 */
	public static CallAnalyzerApplication get() {
		return (CallAnalyzerApplication) WebApplication.get();
	}

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

		// initConstants();

		checkConstants();
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}

	/**
	 * Init constants object
	 */
	private void initConstants() {
		CallType ct = new CallType();
		ct.setId(Long.valueOf(1));
		ct.setName("Incomming");
		ct.persist();

		ct = new CallType();
		ct.setId(Long.valueOf(2));
		ct.setName("Outgoing");
		ct.persist();

		ct = new CallType();
		ct.setId(Long.valueOf(1));
		ct.setName("Missing");
		ct.persist();

		InfoType it = new InfoType();
		it.setId(Long.valueOf(1));
		it.setName("Call");
		it.persist();
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
	}

	private void checkConstants() {
		// Collection<CallType> list = CallType.createCriteria().list();
		Collection<CallType> list = CallType.findAllCallTypes();

		if (CollectionUtils.isNotEmpty(list)) {
			for (CallType ct : list) {
				log.info("CallType: " + ct.toString());
			}
		}
	}
}
