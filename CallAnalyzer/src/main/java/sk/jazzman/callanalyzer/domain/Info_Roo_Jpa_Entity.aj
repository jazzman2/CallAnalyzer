// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package sk.jazzman.callanalyzer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import sk.jazzman.callanalyzer.domain.Info;

privileged aspect Info_Roo_Jpa_Entity {
    
    declare @type: Info: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_")
    private Long Info.id_;
    
    @Version
    @Column(name = "version")
    private Integer Info.version;
    
    public Long Info.getId_() {
        return this.id_;
    }
    
    public void Info.setId_(Long id) {
        this.id_ = id;
    }
    
    public Integer Info.getVersion() {
        return this.version;
    }
    
    public void Info.setVersion(Integer version) {
        this.version = version;
    }
    
}
