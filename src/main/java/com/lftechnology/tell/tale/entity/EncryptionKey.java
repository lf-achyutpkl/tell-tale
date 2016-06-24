package com.lftechnology.tell.tale.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
public class EncryptionKey extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = -515722889297668323L;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @NotNull(message = "User cannot be null")
    private User user;

    @NotBlank
    private String value;

}
