package com.lftechnology.tell.tale.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Prajjwal Raj Kandel<prajjwalkandel@lftechnology.com>
 *
 */
@Entity
@Table(name = "users")
@Setter
@Getter
@NamedQueries({
	@NamedQuery(name=User.GET_USER_FROM_EMAIL_AND_PASSWORD, query="SELECT u FROM User u WHERE u.email = :email AND u.password = :password")})
public class User extends AbstractEntity implements Serializable {
	
    private static final String PREFIX = "tell.tale.user";
    public static final String GET_USER_FROM_EMAIL_AND_PASSWORD = User.PREFIX + "getUserFromEmailAndPassword";

    private static final long serialVersionUID = -515722889297668323L;
    
    @NotBlank(message = "Name cannot be blank.")
    @Size(max = 255)
    private String name;

    @NotBlank(message = "Email cannot be blank.")
    @Size(max = 255)
    private String email;

    @NotBlank(message = "Password cannot be blank.")
    @Size(min = 6)
    private String password;

    @JsonIgnore
    public String getPassword() {
        return this.password;
    }
    
    @JsonProperty
    public void setPassword(String password) {
       this.password = password;
    }
}
