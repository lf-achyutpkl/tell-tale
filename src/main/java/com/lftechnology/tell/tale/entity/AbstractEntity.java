package com.lftechnology.tell.tale.entity;

import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Type;


/**
 * 
 * @author Prajjwal Raj Kandel<prajjwalkandel@lftechnology.com>
 *
 */
@MappedSuperclass
@Getter
@Setter
public abstract class AbstractEntity {

    @Id
    @Type(type = "pg-uuid")
    protected UUID id;

    @PrePersist
    public void prePersist() {
        this.setId(UUID.randomUUID());
    }
}
