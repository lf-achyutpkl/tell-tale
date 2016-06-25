package com.lftechnology.tell.tale.dao.impl;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.lftechnology.tell.tale.dao.SessionDao;
import com.lftechnology.tell.tale.entity.Session;
import com.lftechnology.tell.tale.entity.User;
import com.lftechnology.tell.tale.exception.DataAccessException;
import com.lftechnology.tell.tale.exception.ParameterFormatException;

/**
 * 
 * @author Prajjwal Raj Kandel<prajjwalkandel@lftechnology.com>
 *
 */

@Transactional
public class SessionDaoImpl implements SessionDao {

    @Inject
    EntityManager em;

    @Override
    public Session save(Session entity) {
        try {
            em.persist(entity);
            em.flush();
            em.refresh(entity);
        } catch (PersistenceException persistenceException) {
            throw new DataAccessException("Unable to save");
        }
        return entity;
    }

    @Override
    public Session update(Session entity) {
        try {
            entity = em.merge(entity);
            em.flush();
            em.refresh(entity);
        } catch (PersistenceException persistenceException) {
            throw new DataAccessException("Unable to update");
        }
        return entity;
    }

    @Override
    public Session findOne(UUID id) {
        return em.find(Session.class, id);
    }

    @Override
    public List<Session> findAll() {
        TypedQuery<Session> query = em.createQuery("SELECT s FROM Session s", Session.class);
        return query.getResultList();
    }

    @Override
    public List<Session> find(String start, String offset) {
        TypedQuery<Session> query = em.createQuery("SELECT s FROM Session s", Session.class);
        query.setFirstResult(toInteger(start));
        query.setMaxResults(toInteger(offset));
        return query.getResultList();
    }

    @Override
    public void remove(Session session) {
        em.remove(session);

    }

    @Override
    public void removeById(UUID id) {
        Session session = this.findOne(id);
        if (session != null) {
            em.remove(session);
        }
    }

    private Integer toInteger(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new ParameterFormatException("Pagination query accepts only number value.");
        }
    }

	@Override
	public void logout(User user) {
		em.createNamedQuery(Session.LOGOUT).setParameter("user", user).executeUpdate();
	}
}
