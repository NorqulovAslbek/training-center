package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "groups")
public class GroupEntity extends BaseEntity {
    @Column(unique = true)
    private String name;
    @ManyToOne
    @JoinColumn(name = "teacher_id", updatable = false, insertable = false)
    private ProfileEntity teacher;
    @Column(name = "teacher_id")
    private Integer teacherId;
    private String description;
}
