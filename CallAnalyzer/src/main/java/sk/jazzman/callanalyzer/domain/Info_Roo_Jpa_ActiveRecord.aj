// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package sk.jazzman.callanalyzer.domain;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import sk.jazzman.callanalyzer.domain.Info;

privileged aspect Info_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Info.entityManager;
    
    public static final EntityManager Info.entityManager() {
        EntityManager em = new Info().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Info.countInfoes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Info o", Long.class).getSingleResult();
    }
    
    public static List<Info> Info.findAllInfoes() {
        return entityManager().createQuery("SELECT o FROM Info o", Info.class).getResultList();
    }
    
    public static Info Info.findInfo(Long id) {
        if (id == null) return null;
        return entityManager().find(Info.class, id);
    }
    
    public static List<Info> Info.findInfoEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Info o", Info.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Info.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Info.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Info attached = Info.findInfo(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Info.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Info.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Info Info.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Info merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
