package sk.jazzman.callanalyzer.converters;

import org.hibernate.criterion.Restrictions;

import sk.jazzman.callanalyzer.domain.CallType;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * @author jkovalci
 * 
 */
public class CallTypeConverter implements Converter {

	@Override
	public boolean canConvert(Class type) {
		return type != null && type.equals(Integer.class);
	}

	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
		// CallType retVal = (CallType) source;
		// retVal.
		// FIXME:
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		String value = reader.getValue();
		String attribure = reader.getAttribute("type");

		System.out.println("value=" + value + " attr=" + attribure);

		return CallType.createCriteria().add(Restrictions.eq("id", Long.valueOf(1))).uniqueResult();
	}
}
