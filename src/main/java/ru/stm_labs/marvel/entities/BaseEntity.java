package ru.stm_labs.marvel.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created")
    @CreationTimestamp
    private LocalDateTime created;

    @Column(name = "modified")
    @CreationTimestamp
    private LocalDateTime modified;

    @Column(name = "active")
    private Boolean active;

}