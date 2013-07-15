/**
 * 
 */
package sk.jazzman.callanalyzer.web;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.configuration.Configuration;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.ajax.markup.html.form.upload.UploadProgressBar;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.lang.Bytes;

import sk.jazzman.callanalyzer.application.XMLParser;
import sk.jazzman.callanalyzer.domain.Log;
import sk.jazzman.callanalyzer.domain.Logs;

/**
 * @author jkovalci
 * 
 */
public class HomePage extends WebPage {

	@SpringBean(name = "configuration")
	protected Configuration configuraion;

	@SpringBean(name = "xmlParser")
	protected XMLParser parser;

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

			FileUploadField fuf;
			add(fuf = new FileUploadField("selectFile"));

			// add(new AjaxLink<Void>("upload") {
			// /** Serial id */
			// private static final long serialVersionUID = 1L;
			//
			// @Override
			// public void onClick(AjaxRequestTarget target) {
			// onUploadClicked(target);
			// }
			// });

			add(new AjaxSubmitLink("upload") {
				/** Serial id */
				private static final long serialVersionUID = 1L;

				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					super.onSubmit(target, form);

					onUploadClicked(target);
				}
			});
			add(new UploadProgressBar("uploadProgressBar", this, fuf));

			setMaxSize(Bytes.kilobytes(1000));
		}

		/**
		 * On Upload button clicked
		 * 
		 * @param target
		 */
		private void onUploadClicked(AjaxRequestTarget target) {
			System.out.println("HUHU");

			FileUploadField fuf = ((FileUploadField) get("selectFile"));

			final List<FileUpload> uploads = fuf.getFileUploads();

			if (CollectionUtils.isNotEmpty(uploads)) {
				for (FileUpload u : uploads) {
					try {
						Logs<Log> logs = (Logs<Log>) parser.fromXML(u.getInputStream());

						Iterator<Log> i = logs.iterator();
						Log l;
						while (i.hasNext()) {
							l = i.next();
							System.out.println(l.getCallNumber());
						}
					} catch (IOException e) {
						// FIXME:
						e.printStackTrace();
					}
				}
			}
		}
	}
}
