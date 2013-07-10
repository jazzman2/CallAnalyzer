// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package sk.jazzman.callanalyzer.domain;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import sk.jazzman.callanalyzer.domain.CAUser;

privileged aspect CAUser_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager CAUser.entityManager;
    
    public static final EntityManager CAUser.entityManager() {
        EntityManager em = new CAUser().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long CAUser.countCAUsers() {
        return entityManager().createQuery("SELECT COUNT(o) FROM CAUser o", Long.class).getSingleResult();
    }
    
    public static List<CAUser> CAUser.findAllCAUsers() {
        return entityManager().createQuery("SELECT o FROM CAUser o", CAUser.class).getResultList();
    }
    
    public static CAUser CAUser.findCAUser(Long id_) {
        if (id_ == null) return null;
        return entityManager().find(CAUser.class, id_);
    }
    
    public static List<CAUser> CAUser.findCAUserEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM CAUser o", CAUser.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void CAUser.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void CAUser.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            CAUser attached = CAUser.findCAUser(this.id_);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void CAUser.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void CAUser.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public CAUser CAUser.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        CAUser merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
