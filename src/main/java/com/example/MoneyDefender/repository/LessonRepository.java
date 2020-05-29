package com.example.MoneyDefender.repository;

import com.example.MoneyDefender.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    Lesson findByLessonId(Long lessonId);
    Optional<Lesson> findByLessonSlug(String lessonSlug);
    List<Lesson> findByCourse_CourseId(Long courseId);
}