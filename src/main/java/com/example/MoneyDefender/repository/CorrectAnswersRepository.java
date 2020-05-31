package com.example.MoneyDefender.repository;

import com.example.MoneyDefender.model.CorrectAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CorrectAnswersRepository extends JpaRepository<CorrectAnswers, Long> {
    Optional<CorrectAnswers> findByUserUserId(Long userId);
}
