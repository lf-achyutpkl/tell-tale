package com.lftechnology.tell.tale.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.lftechnology.tell.tale.entity.Suggestion;

/**
 * 
 * @author Prajjwal Raj Kandel<prajjwalkandel@lftechnology.com>
 *
 */
public interface SuggestionService {

    public Suggestion saveByEncrypting(Suggestion suggestion);
    
    public Suggestion save(Suggestion suggestion);

    public Suggestion findOneByDecrypting(UUID id);

    public Suggestion findOne(UUID id);
    
    public List<Suggestion> findAll();

    public Map<String, Object> find(String start, String offset);

    public void remove(Suggestion suggestion);

    public void removeById(UUID id);
    
}
