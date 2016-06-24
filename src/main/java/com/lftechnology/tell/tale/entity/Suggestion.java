package com.lftechnology.tell.tale.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

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
public class Suggestion extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = -515722889297668323L;

    @NotBlank(message = "Message cannot be blank.")
    @Size(max = 500)
    private String message;

    @ManyToOne
    @JoinColumn(name = "recepient_id", referencedColumnName = "id")
    @NotNull(message = "Recepient cannot be null")
    private User recepient;

    private boolean seen;

    private boolean starred;

    @Column(name = "created_at", columnDefinition = "uuid")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdAt;

}
