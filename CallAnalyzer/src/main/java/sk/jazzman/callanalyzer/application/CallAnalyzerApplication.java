/**
 * 
 */
package sk.jazzman.callanalyzer.application;

import java.io.Serializable;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

import sk.jazzman.callanalyzer.web.HomePage;

/**
 * @author jkovalci
 * 
 */
public class CallAnalyzerApplication extends WebApplication implements
		Serializable {

	/** Serial id */
	private static final long serialVersionUID = 1L;

	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}

}
