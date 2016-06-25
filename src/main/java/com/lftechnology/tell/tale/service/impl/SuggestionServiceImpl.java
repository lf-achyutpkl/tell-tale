package com.lftechnology.tell.tale.service.impl;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.lftechnology.tell.tale.dao.SuggestionDao;
import com.lftechnology.tell.tale.entity.EncryptionKey;
import com.lftechnology.tell.tale.entity.Session;
import com.lftechnology.tell.tale.entity.Suggestion;
import com.lftechnology.tell.tale.entity.User;
import com.lftechnology.tell.tale.exception.EncryptionException;
import com.lftechnology.tell.tale.exception.ObjectNotFoundException;
import com.lftechnology.tell.tale.pojo.SecurityRequestContext;
import com.lftechnology.tell.tale.service.EncryptionDecryptionService;
import com.lftechnology.tell.tale.service.EncryptionKeyService;
import com.lftechnology.tell.tale.service.SessionService;
import com.lftechnology.tell.tale.service.SuggestionService;
import com.lftechnology.tell.tale.util.KeyPairUtil;

/**
 * @author Achyut Pokhrel <achyutpokhrel@lftechnology.com> Jun 25, 2016
 * 
 */
@Stateless
public class SuggestionServiceImpl implements SuggestionService {

	@Inject
	private SuggestionDao suggestionDao;

	@Inject
	private EncryptionKeyService encryptionKeyService;
	
	@Inject
	private SessionService sessionService;
	
	@Inject
	private EncryptionDecryptionService encryptionDecryptionService;

	@Override
	public Suggestion saveByEncrypting(Suggestion suggestion) {
		User recepient = suggestion.getRecepient();
		EncryptionKey ekey = this.encryptionKeyService.getEncryptionKey(recepient);
		String message = "";
		try {
			PublicKey pubk = KeyPairUtil.convertStringToPublicKey(ekey.getValue());
			message = this.encryptMessage(suggestion.getMessage(), pubk);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EncryptionException();
		}
		suggestion.setMessage(message);
		return this.suggestionDao.save(suggestion);
	}

	@Override
	public Suggestion findOneByDecrypting(UUID id) {
		Suggestion suggestion =  this.suggestionDao.findOne(id);
		if(suggestion == null){
			throw new ObjectNotFoundException("No row found for given id");
		}
		
		User user = suggestion.getRecepient();
		Session session = this.sessionService.findSession(user);
		String dkey = session.getEncryptedPrivateKey();
		String message = "";
		try{
            String actualPrivateKey = this.encryptionDecryptionService.decrypt(dkey, SecurityRequestContext.getRandomKey());
            PrivateKey privateKey = KeyPairUtil.convertStringToPrivateKey(actualPrivateKey);
            message = this.decryptMessage(suggestion.getMessage(), privateKey);
        }catch(Exception e){
            e.printStackTrace();
            throw new EncryptionException();
        }
		suggestion.setMessage(message);
        return suggestion;
	}

	@Override
	public List<Suggestion> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String,Object> find(String start, String offset) {
		return new HashMap<String, Object>() {
            {
                put("count", suggestionDao.count());
                put("data", suggestionDao.find(start, offset));
            }
        };
	}

	@Override
	public void remove(Suggestion suggestion) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeById(UUID id) {
		// TODO Auto-generated method stub

	}

	private String encryptMessage(String message, PublicKey pubk) throws Exception {
		String xform = "RSA/ECB/PKCS1Padding";
		byte[] databytes = message.getBytes();
		byte[] encbytes = KeyPairUtil.encrypt(databytes, pubk, xform);
		return new String(encbytes);
	}

	private String decryptMessage(String message, PrivateKey privateKey) throws Exception {
		String xform = "RSA/ECB/PKCS1Padding";
		byte[] databytes = message.getBytes();
		byte[] decryptedBytes = KeyPairUtil.decrypt(databytes, privateKey, xform);
		return new String(decryptedBytes);
	}

	@Override
	public Suggestion save(Suggestion suggestion) {
		return this.suggestionDao.save(suggestion);
	}

	@Override
	public Suggestion findOne(UUID id) {
		return this.suggestionDao.findOne(id);
	}

}
