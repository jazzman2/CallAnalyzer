// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package sk.jazzman.callanalyzer.domain;

import java.util.Date;
import sk.jazzman.callanalyzer.domain.CAUser;
import sk.jazzman.callanalyzer.domain.CallType;
import sk.jazzman.callanalyzer.domain.Info;
import sk.jazzman.callanalyzer.domain.Log;

privileged aspect Log_Roo_JavaBean {
    
    public Long Log.getId() {
        return this.id;
    }
    
    public void Log.setId(Long id) {
        this.id = id;
    }
    
    public Info Log.getCallNumber() {
        return this.callNumber;
    }
    
    public void Log.setCallNumber(Info callNumber) {
        this.callNumber = callNumber;
    }
    
    public Long Log.getDuration() {
        return this.duration;
    }
    
    public void Log.setDuration(Long duration) {
        this.duration = duration;
    }
    
    public Date Log.getCallDate() {
        return this.callDate;
    }
    
    public void Log.setCallDate(Date callDate) {
        this.callDate = callDate;
    }
    
    public CAUser Log.getOwner() {
        return this.owner;
    }
    
    public void Log.setOwner(CAUser owner) {
        this.owner = owner;
    }
    
    public Date Log.getImportTime() {
        return this.importTime;
    }
    
    public void Log.setImportTime(Date importTime) {
        this.importTime = importTime;
    }
    
    public CallType Log.getCallType() {
        return this.callType;
    }
    
    public void Log.setCallType(CallType callType) {
        this.callType = callType;
    }
    
}
