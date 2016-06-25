package com.lftechnology.tell.tale.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @author Prajjwal Raj Kandel<prajjwalkandel@lftechnology.com>
 *
 */
@Entity
@Table(name = "encryption_keys")
@Getter
@Setter
@NamedQueries({ @NamedQuery(name = EncryptionKey.GET_PUBLIC_KEY, query = "SELECT e FROM EncryptionKey e WHERE e.user = :user") })
public class EncryptionKey extends AbstractEntity implements Serializable {

    private static final String PREFIX = "tell.tale.encryption.key";
    public static final String GET_PUBLIC_KEY = EncryptionKey.PREFIX + "getUserEncryptionKey";
    private static final long serialVersionUID = -515722889297668323L;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @NotNull(message = "User cannot be null")
    private User user;

    @NotBlank
    @Column(name = "key")
    private String value;

}
