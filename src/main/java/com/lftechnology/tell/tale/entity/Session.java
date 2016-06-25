package com.lftechnology.tell.tale.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lftechnology.tell.tale.util.LocalDateTimeAttributeConverter;
import com.lftechnology.tell.tale.util.LocalDateTimeDeserializer;
import com.lftechnology.tell.tale.util.LocalDateTimeSerializer;
/**
 * 
 * @author Prajjwal Raj Kandel<prajjwalkandel@lftechnology.com>
 *
 */
@Entity
@Table(name = "sessions")
@Getter
@Setter
@NamedQueries({
	@NamedQuery(name=Session.LOGOUT, query="DELETE FROM Session s WHERE s.user = :user")})
public class Session extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = -515722889297668323L;
    private static final String PREFIX = "tell.tale.session";
    public static final String LOGOUT =PREFIX + "logout";

    
    public Session(User user,String encryptedPrivateKey, LocalDateTime expiresAt) {
    	this.user = user;
    	this.encryptedPrivateKey = encryptedPrivateKey;
    	this.expiresAt = expiresAt;
    }

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @NotNull(message = "User cannot be null")
    private User user;

    @Column(name="encrypted_private_key")
    private String encryptedPrivateKey;

    @Column(name = "expires_at", columnDefinition = "uuid")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime expiresAt;
    
}
