package com.example.service;

import com.example.dto.CreateStudentDTO;
import com.example.dto.ResponseStudent;
import com.example.entity.StudentEntity;
import com.example.exp.AppBadException;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;


    public ResponseStudent add(CreateStudentDTO create) {
        if (studentRepository.existsByPhone(create.getPhone())) {
            throw new AppBadException("bunaqa nomerli student mavjud!");
        }
        StudentEntity student = studentRepository.save(toEntity(create));
        return toDto(student);
    }


    private StudentEntity toEntity(CreateStudentDTO create) {
        StudentEntity student = new StudentEntity();
        student.setName(create.getName());
        student.setSurname(create.getSurname());
        student.setPhone(create.getPhone());
        student.setParentPhone(create.getParentPhone());
        student.setGroupId(create.getGroupId());
        return student;
    }

    private ResponseStudent toDto(StudentEntity entity) {
        return ResponseStudent.builder()
                .parentPhone(entity.getParentPhone())
                .phone(entity.getPhone())
                .surname(entity.getSurname())
                .name(entity.getName())
                .id(entity.getId())
                .build();
    }

    public ResponseStudent update(CreateStudentDTO update, Integer id) {
        Optional<StudentEntity> optional = studentRepository.findById(id);
        if (optional.isEmpty()) throw new AppBadException("bunaqa user topilmadi.");
        StudentEntity student = optional.get();
        StudentEntity entity = toEntity(update);
        entity.setUpdatedDate(LocalDateTime.now());
        entity.setId(student.getId());
        StudentEntity save = studentRepository.save(entity);
        return toDto(save);
    }

    public Boolean delete(Integer id) {
        if (studentRepository.findById(id).isEmpty()) throw new AppBadException("bunaqa id lik user mavjud emas!");
        studentRepository.deleteById(id);
        return true;
    }

    public List<ResponseStudent> getAll() {
        List<ResponseStudent> students = new LinkedList<>();
        studentRepository.findAll().forEach(student -> students.add(toDto(student)));
        return students;
    }
}
