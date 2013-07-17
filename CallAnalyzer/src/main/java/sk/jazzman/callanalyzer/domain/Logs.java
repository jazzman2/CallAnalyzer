/**
 * 
 */
package sk.jazzman.callanalyzer.domain;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * @author jkovalci
 * 
 */
@XStreamAlias("calls")
public class Logs<T extends Log> {
	@XStreamImplicit(itemFieldName = "call")
	private final List<T> logs = new Vector<T>();

	public Iterator<T> iterator() {
		return logs.iterator();
	}
}
