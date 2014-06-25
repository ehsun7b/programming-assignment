package com.ehsunbehravesh.programmingassignment.dao;

import com.ehsunbehravesh.programmingassignment.entity.Assignment;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ehsun Behravesh
 */
public class AssignmentDAO {

    private final DatastoreService ds;
    
    public AssignmentDAO(DatastoreService ds) {
        this.ds = ds;
    }

    public Entity insert(final Assignment assignment) {
        Entity result = new Entity(Assignment.KIND);
        
        fillEntity(assignment, result);
        
        Key key = ds.put(result);
        
        return result;
    }
    
    public void delete(final Long id) {
        Key key = KeyFactory.createKey(Assignment.KIND, id);
        ds.delete(key);
    }
    
    public void update(final Assignment assignment) throws EntityNotFoundException {
        Key key = KeyFactory.createKey(Assignment.KIND, assignment.getId());
        Entity entity = ds.get(key);
        fillEntity(assignment, entity);
        ds.put(entity);
    }
    
    public Assignment read(final Long id) throws EntityNotFoundException {
        Key key = KeyFactory.createKey(Assignment.KIND, id);
        
        Entity entity = ds.get(key);        
        Assignment result = new Assignment();
        
        fillAssignment(entity, result);
        
        return result;
    }
    
    public List<Assignment> readtop(final int top) {        
        List<Assignment> result;
        
        Query q = new Query(Assignment.KIND);
        PreparedQuery pq = ds.prepare(q);
        List<Entity> entities = pq.asList(FetchOptions.Builder.withLimit(top));

        result = asAssignments(entities);
        
        return result;
    }

    private void fillEntity(final Assignment assignment, Entity entity) {
        entity.setProperty("description", assignment.getDescription());
        entity.setProperty("category", assignment.getCategory());
        entity.setProperty("city", assignment.getCity());
        entity.setProperty("country", assignment.getCountry());
        entity.setProperty("deadline", assignment.getDeadline());
        entity.setProperty("ip", assignment.getIP());
        entity.setProperty("password", assignment.getPassword());
        entity.setProperty("status", assignment.getStatus());
        entity.setProperty("time", assignment.getTime());
        entity.setProperty("userAgent", assignment.getUserAgent());
        entity.setProperty("timestamp", assignment.getTimestamp());
    }

    private void fillAssignment(final Entity entity, Assignment assignment) {
        assignment.setId(entity.getKey().getId());
        assignment.setCategory((String) entity.getProperty("category"));
        assignment.setCity((String) entity.getProperty("city"));
        assignment.setCountry((String) entity.getProperty("country"));
        assignment.setDeadline((String) entity.getProperty("deadline"));
        assignment.setDescription((String) entity.getProperty("description"));
        assignment.setIP((String) entity.getProperty("ip"));
        assignment.setPassword((String) entity.getProperty("password"));
        assignment.setStatus((String) entity.getProperty("status"));
        assignment.setTime((String) entity.getProperty("time"));
        assignment.setUserAgent((String) entity.getProperty("userAgent"));
        assignment.setTimestamp((Long) entity.getProperty("timestamp"));
    }

    private List<Assignment> asAssignments(final List<Entity> entities) {
        List<Assignment> result = new ArrayList<Assignment>(entities.size());
        
        for (Entity ent: entities) {
            Assignment assignment = new Assignment();
            
            fillAssignment(ent, assignment);
            
            result.add(assignment);
        }
        
        return result;
    }
    
    
}
