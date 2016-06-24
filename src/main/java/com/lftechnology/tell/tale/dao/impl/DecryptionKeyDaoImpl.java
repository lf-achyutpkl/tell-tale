package com.lftechnology.tell.tale.dao.impl;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.lftechnology.tell.tale.dao.DecryptionKeyDao;
import com.lftechnology.tell.tale.entity.DecryptionKey;
import com.lftechnology.tell.tale.entity.User;
import com.lftechnology.tell.tale.exception.DataAccessException;
import com.lftechnology.tell.tale.exception.ParameterFormatException;

/**
 * 
 * @author Prajjwal Raj Kandel<prajjwalkandel@lftechnology.com>
 *
 */

@Transactional
public class DecryptionKeyDaoImpl implements DecryptionKeyDao {

    @Inject
    EntityManager em;

    @Override
    public DecryptionKey save(DecryptionKey entity) {
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
    public DecryptionKey update(DecryptionKey entity) {
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
    public DecryptionKey findOne(UUID id) {
        return em.find(DecryptionKey.class, id);
    }

    @Override
    public List<DecryptionKey> findAll() {
        TypedQuery<DecryptionKey> query = em.createQuery("SELECT dk FROM DecryptionKey dk", DecryptionKey.class);
        return query.getResultList();
    }

    @Override
    public List<DecryptionKey> find(String start, String offset) {
        TypedQuery<DecryptionKey> query = em.createQuery("SELECT dk FROM DecryptionKey dk", DecryptionKey.class);
        query.setFirstResult(toInteger(start));
        query.setMaxResults(toInteger(offset));
        return query.getResultList();
    }

    @Override
    public void remove(DecryptionKey decryptionKey) {
        em.remove(decryptionKey);

    }

    @Override
    public void removeById(UUID id) {
        DecryptionKey decryptionKey = this.findOne(id);
        if (decryptionKey != null) {
            em.remove(decryptionKey);
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
	public DecryptionKey getDecryptionKey(User user) {
		return em.createNamedQuery(DecryptionKey.GET_PRIVATE_KEY,DecryptionKey.class).setParameter("user",user).getResultList().get(0);
	}
}
