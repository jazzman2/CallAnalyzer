// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package sk.jazzman.callanalyzer.domain;

import sk.jazzman.callanalyzer.domain.CAUser;
import sk.jazzman.callanalyzer.domain.Info;
import sk.jazzman.callanalyzer.domain.InfoType;

privileged aspect Info_Roo_JavaBean {
    
    public Long Info.getId() {
        return this.id;
    }
    
    public void Info.setId(Long id) {
        this.id = id;
    }
    
    public String Info.getInfoValue() {
        return this.infoValue;
    }
    
    public void Info.setInfoValue(String infoValue) {
        this.infoValue = infoValue;
    }
    
    public CAUser Info.getOwner() {
        return this.owner;
    }
    
    public void Info.setOwner(CAUser owner) {
        this.owner = owner;
    }
    
    public InfoType Info.getType() {
        return this.type;
    }
    
    public void Info.setType(InfoType type) {
        this.type = type;
    }
    
}
