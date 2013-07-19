package sk.jazzman.callanalyzer.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@SequenceGenerator(name = "causer_seq", sequenceName = "causer_seq_id", allocationSize = 1, initialValue = 1)
public class CAUser extends CAEntity {
	/** Serial id */
	private static final long serialVersionUID = 1L;

	/**
     */
	@Id
	@Min(0L)
	@NotNull
	@Column(unique = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "causer_seq")
	private Long id;

	/**
     */
	private String name;
}
