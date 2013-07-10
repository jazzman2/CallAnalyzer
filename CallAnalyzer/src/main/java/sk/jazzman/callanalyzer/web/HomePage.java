/**
 * 
 */
package sk.jazzman.callanalyzer.web;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * @author jkovalci
 * 
 */
public class HomePage extends WebPage {

	@SpringBean(name = "configuration")
	protected Configuration configuraion;

	/** Serial id */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 */
	public HomePage() {
		constructPage();
	}

	/**
	 * Construct page
	 */
	protected void constructPage() {
		add(new HomeForm("form"));
	}

	/**
	 * HomeForm
	 * 
	 * @author jkovalci
	 * 
	 */
	private class HomeForm extends Form<Void> {
		/** Default serial id */
		private static final long serialVersionUID = 1L;

		/**
		 * Constructr
		 * 
		 * @param id
		 */
		public HomeForm(String id) {
			super(id);

			construct();
		}

		protected void construct() {
			Iterator<String> i = configuraion.getKeys();
			Long l = configuraion.getLong("callType/value[@name='incomming']/id", null);
			List<Object> calltypes = configuraion.getList("callType/value/@name");

			add(new Label("message", Model.of((Serializable) calltypes)));
		}
	}
}
