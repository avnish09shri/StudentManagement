package com.task.platformcommons.repository;

import com.task.platformcommons.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByNameContainingIgnoreCase(String courseName);

    @Query("SELECT c FROM Course c WHERE :topic MEMBER OF c.topics")
    List<Course> findCoursesByTopic(@Param("topic") String topic);
}
