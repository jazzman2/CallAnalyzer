package sk.jazzman.callanalyzer.domain;

import java.util.Date;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("call")
@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Log extends CAEntity {
	/** Serial id */
	private static final long serialVersionUID = 1L;

	/**
     */
	@NotNull
	private Long id;

	/**
     */
	@NotNull
	@ManyToOne
	private Info callNumber;

	/**
     */
	@XStreamAlias("duration")
	@NotNull
	private Long duration;

	/**
     */
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date callDate;

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

	/**
     */
	@NotNull
	@ManyToOne
	@XStreamAsAttribute
	private CallType callType;
}
