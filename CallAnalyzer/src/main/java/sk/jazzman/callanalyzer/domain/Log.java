package sk.jazzman.callanalyzer.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@XStreamAlias("call")
public class Log {

	/**
     */
	@NotNull
	@Column(unique = true)
	private Long id;

	/**
     */
	@XStreamAlias("number")
	@XStreamAsAttribute
	@NotNull
	private String callNumber;

	/**
     */
	@XStreamAlias("duraion")
	@XStreamAsAttribute
	@NotNull
	@Min(0L)
	private Integer duration;

	/**
     */
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date startCall;

	/**
     */
	@XStreamAlias("type")
	@XStreamAsAttribute
	@NotNull
	@ManyToOne
	private CallType callType;

	/**
     */
	@NotNull
	@ManyToOne
	private CAUser owner;

	/**
     */
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date importTime;
}
