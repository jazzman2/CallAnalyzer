/**
 * 
 */
package sk.jazzman.callanalyzer.web;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.configuration.Configuration;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.lang.Bytes;

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
			setMultiPart(Boolean.TRUE);

			Iterator<String> i = configuraion.getKeys();
			Long l = configuraion.getLong("callType/value[@name='incomming']/id", null);
			List<Object> calltypes = configuraion.getList("callType/value/@name");

			add(new Label("message", Model.of((Serializable) calltypes)));

			add(new FileUploadField("selectFile"));

			add(new AjaxLink<Void>("upload") {
				/** Serial id */
				private static final long serialVersionUID = 1L;

				@Override
				public void onClick(AjaxRequestTarget target) {
					onUploadClicked(target);
				}
			});

			setMaxSize(Bytes.kilobytes(1000));
		}

		/**
		 * On Upload button clicked
		 * 
		 * @param target
		 */
		private void onUploadClicked(AjaxRequestTarget target) {
			System.out.println("HUHU");

			final List<FileUpload> uploads = ((FileUploadField) get("selectFile")).getFileUploads();

			if (CollectionUtils.isNotEmpty(uploads)) {
				for (FileUpload u : uploads) {
					// File f = new File()
				}
			}
		}
	}
}
