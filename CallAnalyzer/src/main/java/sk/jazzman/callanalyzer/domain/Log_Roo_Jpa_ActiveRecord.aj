// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package sk.jazzman.callanalyzer.domain;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import sk.jazzman.callanalyzer.domain.Log;

privileged aspect Log_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Log.entityManager;
    
    public static final EntityManager Log.entityManager() {
        EntityManager em = new Log().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Log.countLogs() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Log o", Long.class).getSingleResult();
    }
    
    public static List<Log> Log.findAllLogs() {
        return entityManager().createQuery("SELECT o FROM Log o", Log.class).getResultList();
    }
    
    public static Log Log.findLog(Long id_) {
        if (id_ == null) return null;
        return entityManager().find(Log.class, id_);
    }
    
    public static List<Log> Log.findLogEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Log o", Log.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Log.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Log.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Log attached = Log.findLog(this.id_);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Log.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Log.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Log Log.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Log merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
