package sk.jazzman.callanalyzer.domain;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class CAUser extends CAEntity {
	/** Serial id */
	private static final long serialVersionUID = 1L;

	/**
     */
	@NotNull
	@Column(unique = true)
	private Long id;

	/**
     */
	private String name;
}
