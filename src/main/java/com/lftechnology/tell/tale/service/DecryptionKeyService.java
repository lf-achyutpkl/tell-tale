package com.lftechnology.tell.tale.service;

import java.util.UUID;

import com.lftechnology.tell.tale.entity.DecryptionKey;

/**
 * 
 * @author Prajjwal Raj Kandel<prajjwalkandel@lftechnology.com>
 *
 */
public interface DecryptionKeyService {

    public DecryptionKey save(DecryptionKey decryptionKey);

    public DecryptionKey findOne(UUID id);

}
