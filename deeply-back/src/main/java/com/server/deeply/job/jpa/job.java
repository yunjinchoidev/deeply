package com.server.deeply.job.jpa;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity(name = "job")
@Getter
@Setter
public class job implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String customer;

    private String description;

    private String jobStatus;

    private String dueDate;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdTime;

    @UpdateTimestamp
    private LocalDateTime updatedTime;

    @Builder
    public job(String name, String customer, String description, String jobStatus, String dueDate) {
        this.name = name;
        this.customer = customer;
        this.description = description;
        this.jobStatus = jobStatus;
        this.dueDate = dueDate;
    }
}