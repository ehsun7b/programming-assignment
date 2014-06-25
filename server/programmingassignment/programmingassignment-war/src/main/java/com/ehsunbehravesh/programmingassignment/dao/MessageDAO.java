package com.ehsunbehravesh.programmingassignment.dao;

import com.ehsunbehravesh.programmingassignment.entity.Assignment;
import com.ehsunbehravesh.programmingassignment.entity.Message;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

/**
 *
 * @author Ehsun Behravesh
 */
public class MessageDAO {

    private final DatastoreService ds;

    public MessageDAO(DatastoreService ds) {
        this.ds = ds;
    }
    
    public Entity insert(final Message message) {
        Key parentKey = KeyFactory.createKey(Assignment.KIND, message.getAssignment().getId());
        
        Entity result = new Entity(Message.KIND, parentKey);
        fillEntity(message, result);
        
        ds.put(result);
        return result;
    }

    private void fillEntity(final Message message, Entity entity) {
        entity.setProperty("timestamp", message.getTimestamp());
        entity.setProperty("content", message.getContent());
    }
}
