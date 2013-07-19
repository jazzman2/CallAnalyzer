/**
 * 
 */
package sk.jazzman.callanalyzer.application;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.logging.Log;

import sk.jazzman.callanalyzer.converters.CallTypeConverter;
import sk.jazzman.callanalyzer.converters.LogConverter;
import sk.jazzman.callanalyzer.domain.CallType;
import sk.jazzman.callanalyzer.domain.Logs;

import com.thoughtworks.xstream.XStream;

/**
 * @author jkovalci
 * 
 */
public class XMLParser {
	private static XStream xstream;

	/**
	 * Constructr
	 */
	public XMLParser() {
		initParser();
	}

	public void initParser() {
		xstream = new XStream();
		// xstream.alias("calls", ArrayList.class);
		// xstream.alias("call", Log.class);
		// xstream.aliasAttribute("number", "callNumber");
		xstream.processAnnotations(Logs.class);
		xstream.processAnnotations(Log.class);
		xstream.processAnnotations(CallType.class);
		xstream.registerConverter(new CallTypeConverter());
		xstream.registerConverter(new LogConverter());
	}

	private XStream getXStream() {
		if (xstream == null) {
			throw new IllegalStateException();
		}
		return xstream;
	}

	public <T extends Object> T fromXML(File f) {
		if (f == null) {
			throw new IllegalArgumentException();
		}

		return (T) getXStream().fromXML(f);
	}

	public <T extends Object> T fromXML(InputStream input) {
		if (input == null) {
			throw new IllegalArgumentException();
		}

		return (T) getXStream().fromXML(input);
	}
}
