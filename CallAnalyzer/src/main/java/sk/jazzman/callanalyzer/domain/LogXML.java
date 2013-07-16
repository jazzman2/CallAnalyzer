/**
 * 
 */
package sk.jazzman.callanalyzer.domain;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author jkovalci
 * 
 */
@XStreamAlias("call")
@RooJavaBean
@RooToString
public class LogXML {

	@XStreamAlias("number")
	@XStreamAsAttribute
	private String number;

	@XStreamAlias("duration")
	@XStreamAsAttribute
	private Long duration;

	@XStreamAlias("date")
	@XStreamAsAttribute
	private Long date;

	@XStreamAlias("type")
	@XStreamAsAttribute
	private Integer type;

	@XStreamAlias("contactName")
	@XStreamAsAttribute
	private String contactName;
}
