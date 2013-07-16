/**
 * 
 */
package sk.jazzman.callanalyzer.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.configuration.Configuration;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.ajax.markup.html.form.upload.UploadProgressBar;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.lang.Bytes;

import sk.jazzman.callanalyzer.application.XMLParser;
import sk.jazzman.callanalyzer.domain.CallType;
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

			add(new Label("message", Model.of((Log) calltypes)));

			FileUploadField fuf;
			add(fuf = new FileUploadField("selectFile"));

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

			add(new AjaxFallbackLogDataTableBuilder("table").build());

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
						Logs<CallType> logs = (Logs<CallType>) parser.fromXML(u.getInputStream());
						List<Log> data = new ArrayList<Log>();

						Iterator<CallType> i = logs.iterator();
						// Log l;
						// while (i.hasNext()) {
						// l = i.next();
						// data.add(l);
						// }

						AjaxFallbackDefaultDataTable<?, ?> table = (AjaxFallbackDefaultDataTable<?, ?>) get("table");
						((SortableLogDataProvider) (table).getDataProvider()).setData(data);

						target.add(table);
					} catch (IOException e) {
						// FIXME:
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static abstract class AjaxFallbackDefaultDataTableBuilder<T, S> {

		public abstract String getId();

		public abstract List<IColumn<T, S>> getColumns();

		public abstract SortableDataProvider<T, S> getDataProvider();

		public abstract int getRowsPerPage();

		public AjaxFallbackDefaultDataTable<T, S> build() {
			return new AjaxFallbackDefaultDataTable<T, S>(getId(), getColumns(), getDataProvider(), getRowsPerPage());
		}
	}

	private class AjaxFallbackLogDataTableBuilder extends AjaxFallbackDefaultDataTableBuilder<Log, String> {
		private final String id;

		/**
		 * Constructor
		 * 
		 * @param id
		 */
		public AjaxFallbackLogDataTableBuilder(String id) {
			this.id = id;
		}

		@Override
		public String getId() {
			return id;
		}

		@Override
		public SortableDataProvider<Log, String> getDataProvider() {
			return new SortableLogDataProvider();
		}

		@Override
		public int getRowsPerPage() {
			Integer retVal = configuraion.getInteger("table/rowsPerPage/value", null);
			if (retVal == null) {
				throw new IllegalArgumentException(this.getClass().getSimpleName() + ".getRowsPerPage() : null param");
			}
			return retVal;
		}

		@Override
		public List<IColumn<Log, String>> getColumns() {
			List<IColumn<Log, String>> retVal = new ArrayList<IColumn<Log, String>>();

			retVal.add(new PropertyColumn<Log, String>(Model.of("CallNumber"), "callNumber", "callNumber"));

			return retVal;
		}

		@Override
		public AjaxFallbackDefaultDataTable<Log, String> build() {
			AjaxFallbackDefaultDataTable<Log, String> retVal = super.build();
			retVal.setOutputMarkupId(Boolean.TRUE);
			return retVal;
		}
	}

	private class SortableLogDataProvider extends SortableDataProvider<Log, String> {
		private List<Log> data;

		public List<Log> getData() {
			return data;
		}

		public void setData(List<Log> data) {
			this.data = data;
		}

		@Override
		public Iterator<? extends Log> iterator(long first, long count) {
			Iterator<? extends Log> retVal;
			if (getData() != null) {
				int from = new BigDecimal(first).intValue();
				int to = new BigDecimal(from + count).intValue();
				retVal = getData().subList(from, to).iterator();
			} else {
				retVal = null;
			}
			return retVal;
		}

		@Override
		public long size() {
			long retVal;

			if (getData() != null) {
				retVal = getData().size();
			} else {
				retVal = Long.valueOf(0);
			}

			return retVal;
		}

		@Override
		public IModel<Log> model(Log object) {
			IModel<Log> retVal;
			if (object != null) {
				// retVal = Model.of((Log) object);
				retVal = new Model<Log>(object);
			} else {
				retVal = null;
			}
			return retVal;
		}

	}
}
