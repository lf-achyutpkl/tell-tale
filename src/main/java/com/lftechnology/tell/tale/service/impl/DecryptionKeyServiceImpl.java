package com.lftechnology.tell.tale.service.impl;

import java.util.UUID;

import javax.inject.Inject;

import com.lftechnology.tell.tale.dao.DecryptionKeyDao;
import com.lftechnology.tell.tale.entity.DecryptionKey;
import com.lftechnology.tell.tale.service.DecryptionKeyService;

/**
 * 
 * @author Prajjwal Raj Kandel<prajjwalkandel@lftechnology.com>
 *
 */
public class DecryptionKeyServiceImpl implements DecryptionKeyService {

    @Inject
    private DecryptionKeyDao decryptionKeyDao;

    @Override
    public DecryptionKey save(DecryptionKey decryptionKey) {
        return this.decryptionKeyDao.save(decryptionKey);
    }

    @Override
    public DecryptionKey findOne(UUID id) {
        return this.decryptionKeyDao.findOne(id);
    }

}
