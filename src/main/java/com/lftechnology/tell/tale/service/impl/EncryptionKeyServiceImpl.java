package com.lftechnology.tell.tale.service.impl;

import java.util.UUID;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import com.lftechnology.tell.tale.dao.EncryptionKeyDao;
import com.lftechnology.tell.tale.entity.EncryptionKey;
import com.lftechnology.tell.tale.entity.User;
import com.lftechnology.tell.tale.service.EncryptionKeyService;

/**
 * 
 * @author Prajjwal Raj Kandel<prajjwalkandel@lftechnology.com>
 *
 */
@Stateless
public class EncryptionKeyServiceImpl implements EncryptionKeyService {

    @Default
    private EncryptionKeyDao encryptionKeyDao;

    @Override
    public EncryptionKey save(EncryptionKey encryptionKey) {
        return this.encryptionKeyDao.save(encryptionKey);
    }

    @Override
    public EncryptionKey findOne(UUID id) {
        return this.encryptionKeyDao.findOne(id);
    }

    @Override
    public EncryptionKey getEncryptionKey(User user) {
        return this.encryptionKeyDao.getEncryptionKey(user);
    }
}
