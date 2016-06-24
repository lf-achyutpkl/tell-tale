package com.lftechnology.tell.tale.service.impl;

import java.util.UUID;

import javax.inject.Inject;

import com.lftechnology.tell.tale.dao.EncryptionKeyDao;
import com.lftechnology.tell.tale.entity.EncryptionKey;
import com.lftechnology.tell.tale.service.EncryptionKeyService;

/**
 * 
 * @author Prajjwal Raj Kandel<prajjwalkandel@lftechnology.com>
 *
 */
public class EncryptionKeyServiceImpl implements EncryptionKeyService {

    @Inject
    private EncryptionKeyDao encryptionKeyDao;
    
    @Override
    public EncryptionKey save(EncryptionKey encryptionKey) {
        return this.encryptionKeyDao.save(encryptionKey);
    }

    @Override
    public EncryptionKey findOne(UUID id) {
        return this.encryptionKeyDao.findOne(id);
    }

}
