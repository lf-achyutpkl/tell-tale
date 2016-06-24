package com.lftechnology.tell.tale.service;

import java.util.List;
import java.util.UUID;

import com.lftechnology.tell.tale.entity.Suggestion;

/**
 * 
 * @author Prajjwal Raj Kandel<prajjwalkandel@lftechnology.com>
 *
 */
public interface SuggestionService {

    public Suggestion save(Suggestion suggestion);

    public Suggestion findOne(UUID id);

    public List<Suggestion> findAll();

    public List<Suggestion> find(String start, String offset);

    public void remove(Suggestion suggestion);

    public void removeById(UUID id);
}
