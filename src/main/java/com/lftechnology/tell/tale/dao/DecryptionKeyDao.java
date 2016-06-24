package com.lftechnology.tell.tale.dao;

import java.util.List;
import java.util.UUID;

import com.lftechnology.tell.tale.entity.DecryptionKey;
import com.lftechnology.tell.tale.entity.User;

/**
 * 
 * @author Prajjwal Raj Kandel<prajjwalkandel@lftechnology.com>
 *
 */
public interface DecryptionKeyDao {
    public DecryptionKey save(DecryptionKey decryptionKey);

    public DecryptionKey update(DecryptionKey decryptionKey);

    public DecryptionKey findOne(UUID id);

    public List<DecryptionKey> findAll();

    public List<DecryptionKey> find(String start, String offset);

    public void remove(DecryptionKey decryptionKey);

    public void removeById(UUID id);
    
    public DecryptionKey getDecryptionKey(User user);
}
