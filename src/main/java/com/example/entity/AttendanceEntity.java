package com.example.entity;

import com.example.enums.AttendanceStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "attendance")
public class AttendanceEntity extends BaseEntity {

    @Enumerated(EnumType.STRING) // Enumni saqlash usulini belgilaydi
    @Column(name = "attendance_status", nullable = false)
    private AttendanceStatus attendanceStatus;

    @ManyToOne
    @JoinColumn(name = "student_", nullable = false) // Talaba bilan bog'liqlik
    private StudentEntity student;

    @Column(name = "profile_id", insertable = false, updatable = false)
    private Integer studentId;
}

