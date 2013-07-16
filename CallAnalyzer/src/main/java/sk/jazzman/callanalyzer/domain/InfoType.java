package sk.jazzman.callanalyzer.domain;

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
public class InfoType {

	/**
     */
	@NotNull
	private Long id;

	/**
     */
	@NotNull
	private String name;

	public Criteria criteria() {
		return entityManager.unwrap(Session.class).createCriteria(InfoType.class);
	}
}
