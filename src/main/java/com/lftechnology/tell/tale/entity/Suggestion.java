package com.lftechnology.tell.tale.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;

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
@Table(name = "suggestions")
@Getter
@Setter
public class Suggestion implements Serializable {

    private static final long serialVersionUID = -515722889297668323L;
    
    @Id
    @Type(type = "pg-uuid")
    protected UUID id;

    @NotBlank(message = "Message cannot be blank.")
    @Size(max = 500)
    private String message;

    @ManyToOne
    @JoinColumn(name = "recepient_id", referencedColumnName = "id")
    private User recepient;

    @Column(name="is_seen")
    private boolean seen;

    @Column(name="is_starred")
    private boolean starred;

    @Column(name = "created_at", columnDefinition = "uuid")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdAt;

    @PrePersist
    public void persists(){
    	this.setCreatedAt(LocalDateTime.now());
    	this.setSeen(false);
    	this.setStarred(false);
    	this.setId(UUID.randomUUID());
    }
    
}
