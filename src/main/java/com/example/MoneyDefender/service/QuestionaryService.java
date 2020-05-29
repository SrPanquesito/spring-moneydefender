package com.example.MoneyDefender.service;

import com.example.MoneyDefender.dto.QuestionaryDto;
import com.example.MoneyDefender.model.Questionary;
import com.example.MoneyDefender.repository.LessonRepository;
import com.example.MoneyDefender.repository.QuestionaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import java.time.Instant;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class QuestionaryService {

    private final QuestionaryRepository repository;
    private final LessonRepository lessonRepository;

    // Entonces quieres salvar los datos que te envian (json) usando el repositorio pero tambien necesitas
    // enviar de vuelta los resultados de dicha creacion. Entonces mapeas la entidad con la que juegas (BDD Interactions)
    // y la conviertes en un DTO para enviar la info como respuesta a la llamda que hizo el cliente al API.
    @Transactional
    public QuestionaryDto save(QuestionaryDto request) {
        Questionary save = repository.save(mapTransToDto(request));
        request.setQuestionaryId(save.getQuestionaryId());
        return request;
    }

    @Transactional(readOnly = true)
    public List<QuestionaryDto> getAll() {
        return repository.findAll()
            .stream()
            .map(this::mapToDto)
            .collect(toList());
    }

    private Questionary mapTransToDto(QuestionaryDto request) {
        return Questionary.builder()
            .question(request.getQuestion())
            .answers(request.getAnswers())
            .correctAnswer(request.getCorrectAnswer())
            .lesson(lessonRepository.findByLessonId(request.getLessonId()))
            .build();
    }
    private QuestionaryDto mapToDto(Questionary transaction) {
        return QuestionaryDto.builder()
            .question(transaction.getQuestion())
            .answers(transaction.getAnswers())
            .correctAnswer(transaction.getCorrectAnswer())
            .lessonId(transaction.getLesson().getLessonId())
            .build();
    }
}