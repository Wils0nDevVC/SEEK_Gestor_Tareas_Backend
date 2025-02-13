package com.tareas.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
//@MappedSuperclass : nos indica que sera una clase madre
@MappedSuperclass
public abstract class BaseEntity {


    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private  long id;
    @Column(columnDefinition = "DATETIME", name = "createDate",updatable = false, nullable = false)
    private Date createDate;
    @Column(columnDefinition = "DATETIME", name = "updateDate",updatable = false, nullable = false)
    private Date updateDate;

    //@PrePersist : Antes de crear algo va a realizar algo
    @PrePersist
    protected  void onCreate() {
        updateDate = new Date();
        if(createDate == null){
            createDate = new Date();
        }
    }




}
