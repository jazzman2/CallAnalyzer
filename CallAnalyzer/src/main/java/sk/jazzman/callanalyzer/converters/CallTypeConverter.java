package sk.jazzman.callanalyzer.converters;

import sk.jazzman.callanalyzer.domain.CallType;
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
public class CallTypeConverter implements Converter {

	@Override
	public boolean canConvert(Class type) {
		return type != null && type.equals(Log.class);
	}

	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
		CallType callType = (CallType) source;

		writer.setValue(callType.getId().toString());
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		// String value = reader.getValue();
		// String attribure = reader.getAttribute("type");
		//
		// System.out.println("value=" + value + " attr=" + attribure);
		//
		// return CallType.createCriteria().add(Restrictions.eq("id",
		// Long.valueOf(1))).uniqueResult();

		CallType retVal = new CallType();
		retVal.setId(Long.parseLong(reader.getAttribute("type")));
		return retVal;
	}
}
