package com.lftechnology.tell.tale.service;

import java.util.UUID;

import com.lftechnology.tell.tale.entity.EncryptionKey;

/**
 * 
 * @author Prajjwal Raj Kandel<prajjwalkandel@lftechnology.com>
 *
 */
public interface EncryptionKeyService {

    public EncryptionKey save(EncryptionKey encryptionKey);

    public EncryptionKey findOne(UUID id);

}
