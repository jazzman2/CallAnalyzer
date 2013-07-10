// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package sk.jazzman.callanalyzer.domain;

import java.util.Iterator;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import sk.jazzman.callanalyzer.domain.Log;
import sk.jazzman.callanalyzer.domain.LogDataOnDemand;
import sk.jazzman.callanalyzer.domain.LogIntegrationTest;

privileged aspect LogIntegrationTest_Roo_IntegrationTest {
    
    declare @type: LogIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: LogIntegrationTest: @ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml");
    
    declare @type: LogIntegrationTest: @Transactional;
    
    @Autowired
    LogDataOnDemand LogIntegrationTest.dod;
    
    @Test
    public void LogIntegrationTest.testCountLogs() {
        Assert.assertNotNull("Data on demand for 'Log' failed to initialize correctly", dod.getRandomLog());
        long count = Log.countLogs();
        Assert.assertTrue("Counter for 'Log' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void LogIntegrationTest.testFindLog() {
        Log obj = dod.getRandomLog();
        Assert.assertNotNull("Data on demand for 'Log' failed to initialize correctly", obj);
        Long id = obj.getId_();
        Assert.assertNotNull("Data on demand for 'Log' failed to provide an identifier", id);
        obj = Log.findLog(id);
        Assert.assertNotNull("Find method for 'Log' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Log' returned the incorrect identifier", id, obj.getId_());
    }
    
    @Test
    public void LogIntegrationTest.testFindAllLogs() {
        Assert.assertNotNull("Data on demand for 'Log' failed to initialize correctly", dod.getRandomLog());
        long count = Log.countLogs();
        Assert.assertTrue("Too expensive to perform a find all test for 'Log', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Log> result = Log.findAllLogs();
        Assert.assertNotNull("Find all method for 'Log' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Log' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void LogIntegrationTest.testFindLogEntries() {
        Assert.assertNotNull("Data on demand for 'Log' failed to initialize correctly", dod.getRandomLog());
        long count = Log.countLogs();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Log> result = Log.findLogEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Log' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Log' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void LogIntegrationTest.testFlush() {
        Log obj = dod.getRandomLog();
        Assert.assertNotNull("Data on demand for 'Log' failed to initialize correctly", obj);
        Long id = obj.getId_();
        Assert.assertNotNull("Data on demand for 'Log' failed to provide an identifier", id);
        obj = Log.findLog(id);
        Assert.assertNotNull("Find method for 'Log' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyLog(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'Log' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void LogIntegrationTest.testMergeUpdate() {
        Log obj = dod.getRandomLog();
        Assert.assertNotNull("Data on demand for 'Log' failed to initialize correctly", obj);
        Long id = obj.getId_();
        Assert.assertNotNull("Data on demand for 'Log' failed to provide an identifier", id);
        obj = Log.findLog(id);
        boolean modified =  dod.modifyLog(obj);
        Integer currentVersion = obj.getVersion();
        Log merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId_(), id);
        Assert.assertTrue("Version for 'Log' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void LogIntegrationTest.testPersist() {
        Assert.assertNotNull("Data on demand for 'Log' failed to initialize correctly", dod.getRandomLog());
        Log obj = dod.getNewTransientLog(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Log' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Log' identifier to be null", obj.getId_());
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
        Assert.assertNotNull("Expected 'Log' identifier to no longer be null", obj.getId_());
    }
    
    @Test
    public void LogIntegrationTest.testRemove() {
        Log obj = dod.getRandomLog();
        Assert.assertNotNull("Data on demand for 'Log' failed to initialize correctly", obj);
        Long id = obj.getId_();
        Assert.assertNotNull("Data on demand for 'Log' failed to provide an identifier", id);
        obj = Log.findLog(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'Log' with identifier '" + id + "'", Log.findLog(id));
    }
    
}
