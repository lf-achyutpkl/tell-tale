package com.lftechnology.tell.tale.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @author Prajjwal Raj Kandel<prajjwalkandel@lftechnology.com>
 *
 */
@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = -515722889297668323L;

    @NotBlank(message = "Name cannot be blank.")
    @Size(max = 255)
    private String name;
    
    @NotBlank(message = "Email cannot be blank.")
    @Size(max = 255)
    private String email;
    
    @NotBlank(message = "Password cannot be blank.")
    @Size(max = 255)
    private String password;

}
