package com.example.MoneyDefender.service;

import com.example.MoneyDefender.model.Course;
import com.example.MoneyDefender.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import java.time.Instant;

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
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    private Course mapTransToDto(Course courseRequest) {
        return Course.builder()
            .courseName(courseRequest.getCourseName())
            .description(courseRequest.getDescription())
            .createdDate(Instant.now())
            .user(authService.getCurrentUser())
            .build();
    }
    // private CourseResponse mapToDto(Course transaction) {
    //     return CourseResponse.builder().courseName(transaction.getCourseName()).id(transaction.getCourseId()).build();
    // }
}