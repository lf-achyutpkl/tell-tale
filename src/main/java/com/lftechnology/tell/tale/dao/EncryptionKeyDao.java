package com.lftechnology.tell.tale.dao;

import java.util.List;
import java.util.UUID;

import com.lftechnology.tell.tale.entity.EncryptionKey;
import com.lftechnology.tell.tale.entity.User;

/**
 * 
 * @author Prajjwal Raj Kandel<prajjwalkandel@lftechnology.com>
 *
 */
public interface EncryptionKeyDao {
    public EncryptionKey save(EncryptionKey encryptionKey);

    public EncryptionKey update(EncryptionKey encryptionKey);

    public EncryptionKey findOne(UUID id);

    public List<EncryptionKey> findAll();

    public List<EncryptionKey> find(String start, String offset);

    public void remove(EncryptionKey encryptionKey);

    public void removeById(UUID id);
    
    public EncryptionKey getEncryptionKey(User user);
}
