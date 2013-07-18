package sk.jazzman.callanalyzer.domain;

import javax.persistence.Column;
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
public class CallType {

	/**
     */
	@NotNull
	@Column(unique = true)
	@Min(0L)
	private Long id;

	/**
     */
	@NotNull
	private String name;

	public static Criteria createCriteria() {
		return entityManager().unwrap(Session.class).createCriteria(CallType.class);
	}
}
