package sk.jazzman.callanalyzer.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@SequenceGenerator(name = "info_seq", sequenceName = "info_seq_id", allocationSize = 1, initialValue = 1)
public class Info {

	/**
     */
	@Id
	@Column(unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "info_seq")
	@NotNull
	@Min(0L)
	private Long id;

	/**
     */
	@NotNull
	private String infoValue;

	/**
     */
	@NotNull
	@ManyToOne
	private CAUser owner;

	/**
     */
	@NotNull
	@ManyToOne
	private InfoType type;
}
