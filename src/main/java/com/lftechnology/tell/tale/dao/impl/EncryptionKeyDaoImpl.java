package com.lftechnology.tell.tale.dao.impl;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.lftechnology.tell.tale.dao.EncryptionKeyDao;
import com.lftechnology.tell.tale.entity.EncryptionKey;
import com.lftechnology.tell.tale.entity.User;
import com.lftechnology.tell.tale.exception.DataAccessException;
import com.lftechnology.tell.tale.exception.ParameterFormatException;

/**
 * 
 * @author Prajjwal Raj Kandel<prajjwalkandel@lftechnology.com>
 *
 */

@Transactional
public class EncryptionKeyDaoImpl implements EncryptionKeyDao {

    @Inject
    EntityManager em;

    @Override
    public EncryptionKey save(EncryptionKey entity) {
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
    public EncryptionKey update(EncryptionKey entity) {
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
    public EncryptionKey findOne(UUID id) {
        return em.find(EncryptionKey.class, id);
    }

    @Override
    public List<EncryptionKey> findAll() {
        TypedQuery<EncryptionKey> query = em.createQuery("SELECT ek FROM EncryptionKey ek", EncryptionKey.class);
        return query.getResultList();
    }

    @Override
    public List<EncryptionKey> find(String start, String offset) {
        TypedQuery<EncryptionKey> query = em.createQuery("SELECT ek FROM EncryptionKey ek", EncryptionKey.class);
        query.setFirstResult(toInteger(start));
        query.setMaxResults(toInteger(offset));
        return query.getResultList();
    }

    @Override
    public void remove(EncryptionKey encryptionKey) {
        em.remove(encryptionKey);

    }

    @Override
    public void removeById(UUID id) {
        EncryptionKey encryptionKey = this.findOne(id);
        if (encryptionKey != null) {
            em.remove(encryptionKey);
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
    public EncryptionKey getEncryptionKey(User user) {
        return em.createNamedQuery(EncryptionKey.GET_PUBLIC_KEY, EncryptionKey.class).setParameter("user", user).getResultList().get(0);
    }
}
