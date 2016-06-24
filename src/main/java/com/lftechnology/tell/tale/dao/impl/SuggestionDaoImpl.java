package com.lftechnology.tell.tale.dao.impl;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.lftechnology.tell.tale.dao.SuggestionDao;
import com.lftechnology.tell.tale.entity.Suggestion;
import com.lftechnology.tell.tale.exception.DataAccessException;
import com.lftechnology.tell.tale.exception.ParameterFormatException;

/**
 * 
 * @author Prajjwal Raj Kandel<prajjwalkandel@lftechnology.com>
 *
 */

@Transactional
public class SuggestionDaoImpl implements SuggestionDao {

    @Inject
    EntityManager em;

    @Override
    public Suggestion save(Suggestion entity) {
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
    public Suggestion update(Suggestion entity) {
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
    public Suggestion findOne(UUID id) {
        return em.find(Suggestion.class, id);
    }

    @Override
    public List<Suggestion> findAll() {
        TypedQuery<Suggestion> query = em.createQuery("SELECT s FROM Suggestion s", Suggestion.class);
        return query.getResultList();
    }

    @Override
    public List<Suggestion> find(String start, String offset) {
        TypedQuery<Suggestion> query = em.createQuery("SELECT s FROM Suggestion s ORDER BY s.createdAt", Suggestion.class);
        query.setFirstResult(toInteger(start));
        query.setMaxResults(toInteger(offset));
        return query.getResultList();
    }

    @Override
    public void remove(Suggestion suggestion) {
        em.remove(suggestion);

    }

    @Override
    public void removeById(UUID id) {
        Suggestion suggestion = this.findOne(id);
        if (suggestion != null) {
            em.remove(suggestion);
        }
    }

    @Override
    public long count() {
        TypedQuery<Suggestion> query = em.createQuery("Select s from Suggestion u", Suggestion.class);
        List<Suggestion> suggestions = query.getResultList();
        return suggestions.size();
    }

    private Integer toInteger(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new ParameterFormatException("Pagination query accepts only number value.");
        }
    }
}
