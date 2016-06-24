package com.lftechnology.tell.tale.dao.impl;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.lftechnology.tell.tale.dao.UserDao;
import com.lftechnology.tell.tale.entity.User;
import com.lftechnology.tell.tale.exception.DataAccessException;
import com.lftechnology.tell.tale.exception.ParameterFormatException;

/**
 * 
 * @author Prajjwal Raj Kandel<prajjwalkandel@lftechnology.com>
 *
 */

@Transactional
public class UserDaoImpl implements UserDao {

    @Inject
    EntityManager em;

    @Override
    public User save(User entity) {
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
    public User update(User entity) {
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
    public User findOne(UUID id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    @Override
    public List<User> find(String start, String offset) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u ", User.class);
        query.setFirstResult(toInteger(start));
        query.setMaxResults(toInteger(offset));
        return query.getResultList();
    }

    @Override
    public void remove(User user) {
        em.remove(user);

    }

    @Override
    public void removeById(UUID id) {
        User user = this.findOne(id);
        if (user != null) {
            em.remove(user);
        }
    }

    private Integer toInteger(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new ParameterFormatException("Pagination query accepts only number value.");
        }
    }
}
