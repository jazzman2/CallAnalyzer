package sk.jazzman.callanalyzer.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("call")
@RooJavaBean
@RooToString
@RooJpaActiveRecord
@SequenceGenerator(name = "log_seq", sequenceName = "log_seq_id", allocationSize = 1, initialValue = 1)
public class Log extends CAEntity {

	/** Serial id */
	private static final long serialVersionUID = 1L;

	/**
     */
	@Min(0L)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "log_seq")
	@Id
	@Column(unique = true)
	@NotNull
	private Long id;

	/**
     */
	@NotNull
	@ManyToOne
	private Info callNumber;

	/**
     */
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
	private CallType callType;
}
