package com.lftechnology.tell.tale.service.impl;

import java.util.List;
import java.util.UUID;

import javax.ejb.Stateless;

import com.lftechnology.tell.tale.entity.Suggestion;
import com.lftechnology.tell.tale.service.SuggestionService;

/**
 * @author Achyut Pokhrel <achyutpokhrel@lftechnology.com>
 * Jun 25, 2016
 * 
 */
@Stateless
public class SuggestionServiceImpl implements SuggestionService{

	@Override
	public Suggestion save(Suggestion suggestion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Suggestion findOne(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Suggestion> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Suggestion> find(String start, String offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Suggestion suggestion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeById(UUID id) {
		// TODO Auto-generated method stub
		
	}

}
