package com.lftechnology.tell.tale.dao;

import java.util.List;
import java.util.UUID;

import com.lftechnology.tell.tale.entity.Session;

/**
 * 
 * @author Prajjwal Raj Kandel<prajjwalkandel@lftechnology.com>
 *
 */
public interface SessionDao {
    public Session save(Session session);

    public Session update(Session session);

    public Session findOne(UUID id);

    public List<Session> findAll();

    public List<Session> find(String start, String offset);

    public void remove(Session session);

    public void removeById(UUID id);
}
