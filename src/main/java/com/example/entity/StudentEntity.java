package com.example.entity;

import com.example.entity.BaseEntity;
import com.example.entity.GroupEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "students")
public class StudentEntity extends BaseEntity {

    private String name;
    private String surname;
    private String phone;
    private String parentPhone;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false) // Nullable if every student must belong to a group
    private GroupEntity group;

    @Column(name = "group_id", insertable = false, updatable = false) // Ensure it's a read-only reflection of the relationship
    private Integer groupId;
}
