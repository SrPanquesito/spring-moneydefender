package com.example.MoneyDefender.service;

import com.example.MoneyDefender.dto.LessonDto;
import com.example.MoneyDefender.exceptions.SpringException;
import com.example.MoneyDefender.model.Lesson;
import com.example.MoneyDefender.model.Questionary;
import com.example.MoneyDefender.repository.CourseRepository;
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
public class LessonService {

    private final LessonRepository repository;
    private final AuthService authService;
    private final CourseRepository courseRepository;
    private final QuestionaryRepository questionaryRepository;

    // Entonces quieres salvar los datos que te envian (json) usando el repositorio pero tambien necesitas
    // enviar de vuelta los resultados de dicha creacion. Entonces mapeas la entidad con la que juegas (BDD Interactions)
    // y la conviertes en un DTO para enviar la info como respuesta a la llamda que hizo el cliente al API.
    @Transactional
    public LessonDto save(LessonDto request) {
        Lesson save = repository.save(mapTransToDto(request));
        request.setLessonId(save.getLessonId());
        return request;
    }

    @Transactional(readOnly = true)
    public List<LessonDto> getAll() {
        return repository.findAll()
            .stream()
            .map(this::mapToDto)
            .collect(toList());
    }

    @Transactional
    public LessonDto update(Long id, Long qId) {
        Lesson lesson = repository.findById(id)
                .orElseThrow(() -> new SpringException(id.toString()));
        
        Questionary questionary = questionaryRepository.findById(qId)
                .orElseThrow(() -> new SpringException(id.toString()));
        
        lesson.setQuestionary(questionary);
        return mapToDtoUpdate(lesson);
    }

    private Lesson mapTransToDto(LessonDto request) {
        return Lesson.builder()
            .lessonName(request.getLessonName())
            .lessonSlug(request.getLessonSlug())
            .createdDate(Instant.now())
            .content(request.getContent())
            .course(courseRepository.findByCourseId(request.getCourseId()))
            .build();
    }
    private LessonDto mapToDto(Lesson transaction) {
        return LessonDto.builder()
            .lessonId(transaction.getLessonId())
            .lessonName(transaction.getLessonName())
            .createdDate(transaction.getCreatedDate())
            .content(transaction.getContent())
            .courseId(transaction.getCourse().getCourseId())
            .build();
    }
    private LessonDto mapToDtoUpdate(Lesson transaction) {
        return LessonDto.builder()
            .lessonId(transaction.getLessonId())
            .lessonName(transaction.getLessonName())
            .createdDate(transaction.getCreatedDate())
            .content(transaction.getContent())
            .courseId(transaction.getCourse().getCourseId())
            .questionaryId(transaction.getQuestionary().getQuestionaryId())
            .messageLog("questionaryId updated successfully!")
            .build();
    }
}