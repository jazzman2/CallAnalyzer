// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package sk.jazzman.callanalyzer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Version;
import sk.jazzman.callanalyzer.domain.Log;

privileged aspect Log_Roo_Jpa_Entity {
    
    declare @type: Log: @Entity;
    
    @Version
    @Column(name = "version")
    private Integer Log.version;
    
    public Integer Log.getVersion() {
        return this.version;
    }
    
    public void Log.setVersion(Integer version) {
        this.version = version;
    }
    
}
