/**
 * 
 */
package sk.jazzman.callanalyzer.converters;

import java.sql.Timestamp;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Configurable;

import sk.jazzman.callanalyzer.domain.CallType;
import sk.jazzman.callanalyzer.domain.Info;
import sk.jazzman.callanalyzer.domain.InfoType;
import sk.jazzman.callanalyzer.domain.Log;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * @author jkovalci
 * 
 */
@Configurable
public class LogConverter implements Converter {
	@SpringBean(name = "configuraion")
	private org.apache.commons.configuration.Configuration configuraion;

	@Override
	public boolean canConvert(Class type) {
		return type != null && Log.class.isAssignableFrom(type);
	}

	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		Log log = new Log();

		Object o = reader.getAttribute("number");
		if (o != null) {
			// Long id = configuraion.getLong("infoType/value[@name='call']/id",
			// null);

			InfoType it = InfoType.findInfoType(Long.valueOf(1));

			Info i = new Info();
			i.setType(it);
			i.setInfoValue((String) o);
			log.setCallNumber(i);
		}

		o = reader.getAttribute("duration");
		if (o != null) {
			Long d = Long.parseLong((String) o);
			log.setDuration(d);
		}

		o = reader.getAttribute("date");
		if (o != null) {
			Long t = Long.parseLong((String) o);
			java.sql.Timestamp time = new Timestamp(t);
			log.setCallDate(time);
		}

		o = reader.getAttribute("type");
		if (o != null) {
			Long t = Long.parseLong((String) o);

			CallType ct = (CallType) CallType.createCriteria().add(Restrictions.eq("id", t)).uniqueResult();
			log.setCallType(ct);
		}

		return log;
	}
}
