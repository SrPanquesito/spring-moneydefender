package com.example.MoneyDefender.service;

import com.example.MoneyDefender.dto.CourseResponse;
import com.example.MoneyDefender.exceptions.SpringException;
import com.example.MoneyDefender.model.Course;
import com.example.MoneyDefender.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import java.time.Instant;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final AuthService authService;

    // Entonces quieres salvar los datos que te envian (json) usando el repositorio pero tambien necesitas
    // enviar de vuelta los resultados de dicha creacion. Entonces mapeas la entidad con la que juegas (BDD Interactions)
    // y la conviertes en un DTO para enviar la info como respuesta a la llamda que hizo el cliente al API.
    @Transactional
    public Course save(Course courseRequest) {
        Course save = courseRepository.save(mapTransToDto(courseRequest));
        courseRequest.setCourseId(save.getCourseId());
        return courseRequest;
    }

    @Transactional(readOnly = true)
    public List<CourseResponse> getAll() {
        return courseRepository.findAll()
            .stream()
            .map(this::mapToDto)
            .collect(toList());
    }

    @Transactional(readOnly = true)
    public CourseResponse getCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new SpringException(id.toString()));
        return mapToDto(course);
    }

    private Course mapTransToDto(Course courseRequest) {
        return Course.builder()
            .courseName(courseRequest.getCourseName())
            .description(courseRequest.getDescription())
            .createdDate(Instant.now())
            .user(authService.getCurrentUser())
            .build();
    }
    private CourseResponse mapToDto(Course transaction) {
        return CourseResponse.builder()
            .courseId(transaction.getCourseId())
            .courseName(transaction.getCourseName())
            .description(transaction.getDescription())
            .createdDate(transaction.getCreatedDate())
            .userId(transaction.getUser().getUserId())
            .build();
    }
}