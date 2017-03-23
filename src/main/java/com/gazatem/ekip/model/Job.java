package com.gazatem.ekip.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by Aeris on 22.3.2017.
 */
@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "job_id")
    private int id;
    @Column(name = "name")
    @NotEmpty(message = "*Please provide a job name")
    private String name;
}
