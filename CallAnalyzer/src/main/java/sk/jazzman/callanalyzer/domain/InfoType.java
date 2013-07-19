package sk.jazzman.callanalyzer.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@Configurable
@SequenceGenerator(name = "info_type_seq", sequenceName = "info_type_seq_id", allocationSize = 1, initialValue = 1)
public class InfoType {

	/**
     */
	@Min(0L)
	@Column(unique = true)
	@NotNull
	@Id
	@GeneratedValue(generator = "info_type_seq", strategy = GenerationType.SEQUENCE)
	private Long id;

	/**
     */
	@NotNull
	private String name;

	public Criteria criteria() {
		return entityManager.unwrap(Session.class).createCriteria(InfoType.class);
	}
}
