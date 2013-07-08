// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package sk.jazzman.callanalyzer.domain;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.stereotype.Component;
import sk.jazzman.callanalyzer.domain.Log;
import sk.jazzman.callanalyzer.domain.LogDataOnDemand;

privileged aspect LogDataOnDemand_Roo_DataOnDemand {
    
    declare @type: LogDataOnDemand: @Component;
    
    private Random LogDataOnDemand.rnd = new SecureRandom();
    
    private List<Log> LogDataOnDemand.data;
    
    public Log LogDataOnDemand.getNewTransientLog(int index) {
        Log obj = new Log();
        return obj;
    }
    
    public Log LogDataOnDemand.getSpecificLog(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Log obj = data.get(index);
        Long id = obj.getId();
        return Log.findLog(id);
    }
    
    public Log LogDataOnDemand.getRandomLog() {
        init();
        Log obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return Log.findLog(id);
    }
    
    public boolean LogDataOnDemand.modifyLog(Log obj) {
        return false;
    }
    
    public void LogDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = Log.findLogEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Log' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Log>();
        for (int i = 0; i < 10; i++) {
            Log obj = getNewTransientLog(i);
            try {
                obj.persist();
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            obj.flush();
            data.add(obj);
        }
    }
    
}
