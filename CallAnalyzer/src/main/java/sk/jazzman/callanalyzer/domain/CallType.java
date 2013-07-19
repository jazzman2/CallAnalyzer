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

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("type")
@RooJavaBean
@RooToString
@RooJpaActiveRecord
@Configurable
@SequenceGenerator(name = "call_type_seq", sequenceName = "call_type_seq_id", allocationSize = 1, initialValue = 1)
public class CallType {

	/**
     */
	@Id
	@NotNull
	@Column(unique = true)
	@Min(0L)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "call_type_seq")
	private Long id;

	/**
     */
	@NotNull
	private String name;

	public static Criteria createCriteria() {
		return entityManager().unwrap(Session.class).createCriteria(CallType.class);
	}
}
