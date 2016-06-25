package com.lftechnology.tell.tale.service.impl;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import com.lftechnology.tell.tale.dao.SuggestionDao;
import com.lftechnology.tell.tale.entity.DecryptionKey;
import com.lftechnology.tell.tale.entity.EncryptionKey;
import com.lftechnology.tell.tale.entity.Suggestion;
import com.lftechnology.tell.tale.entity.User;
import com.lftechnology.tell.tale.exception.EncryptionException;
import com.lftechnology.tell.tale.pojo.SecurityRequestContext;
import com.lftechnology.tell.tale.service.DecryptionKeyService;
import com.lftechnology.tell.tale.service.EncryptionDecryptionService;
import com.lftechnology.tell.tale.service.EncryptionKeyService;
import com.lftechnology.tell.tale.service.SuggestionService;
import com.lftechnology.tell.tale.util.KeyPairUtil;

/**
 * 
 * @author Prajjwal Raj Kandel<prajjwalkandel@lftechnology.com>
 *
 */
public class SuggestionServiceImpl implements SuggestionService {

    @Inject
    private SuggestionDao suggestionDao;

    @Inject
    private EncryptionDecryptionService encryptionDecryptionService;

    @Inject
    private DecryptionKeyService decryptionKeyService;

    @Inject
    private EncryptionKeyService encryptionKeyService;

    @Override
    public Suggestion save(Suggestion suggestion) {
        User recepient = suggestion.getRecepient();
        EncryptionKey ekey = this.encryptionKeyService.getEncryptionKey(recepient);
        String message = "";
        try {
            PublicKey pubk = KeyPairUtil.convertStringToPublicKey(ekey.getValue());
            message = this.encryptMessage(suggestion.getMessage(), pubk);
        } catch (Exception e) {
            throw new EncryptionException();
        }
        suggestion.setMessage(message);
        return this.suggestionDao.save(suggestion);
    }

    @Override
    public Suggestion findOne(UUID id) {
        Suggestion suggestion = this.suggestionDao.findOne(id);
        User user = suggestion.getRecepient();
        DecryptionKey dkey = this.decryptionKeyService.getDecryptionKey(user);
        String message = "";
        try{
            String decryptedKey = this.encryptionDecryptionService.decrypt(dkey.getValue(), SecurityRequestContext.getRandomKey());
            PrivateKey privateKey = KeyPairUtil.convertStringToPrivateKey(decryptedKey);
            message = this.decryptMessage(suggestion.getMessage(), privateKey);
        }catch(Exception e){
            throw new EncryptionException();
        }
        suggestion.setMessage(message);
        return suggestion;
    }

    @Override
    public List<Suggestion> findAll() {
        List<Suggestion> suggestions = this.suggestionDao.findAll();
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
}
