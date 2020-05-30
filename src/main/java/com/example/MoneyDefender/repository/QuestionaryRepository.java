package com.example.MoneyDefender.repository;

import com.example.MoneyDefender.model.Questionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionaryRepository extends JpaRepository<Questionary, Long> {
    Questionary findByQuestionaryId(Long questionaryId);
    Questionary findByLesson_LessonId(Long lessonId);
}