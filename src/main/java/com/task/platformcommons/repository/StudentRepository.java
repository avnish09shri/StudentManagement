package com.task.platformcommons.repository;

import com.task.platformcommons.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByStudentCode(String studentCode);

    @Query(value = "SELECT student_code FROM student ORDER BY id DESC LIMIT 1", nativeQuery = true)
    String findLastStudentCode();

    List<Student> findByNameContainingIgnoreCase(String name);

    @Query("SELECT s FROM Student s JOIN s.courses c WHERE c.name = :courseName")
    List<Student> findStudentsByCourseName(String courseName);
}
