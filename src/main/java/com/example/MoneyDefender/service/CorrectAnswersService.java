package com.example.MoneyDefender.service;

import com.example.MoneyDefender.dto.CorrectAnswersDto;
import com.example.MoneyDefender.exceptions.SpringException;
import com.example.MoneyDefender.model.CorrectAnswers;
import com.example.MoneyDefender.model.Questionary;
import com.example.MoneyDefender.model.User;
import com.example.MoneyDefender.repository.CorrectAnswersRepository;
import com.example.MoneyDefender.repository.QuestionaryRepository;
import com.example.MoneyDefender.repository.UserRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CorrectAnswersService {

    private final CorrectAnswersRepository repository;
    private final QuestionaryRepository questionaryRepository;
    private final UserRepository userRepository;
    private final AuthService authService;

    // Entonces quieres salvar los datos que te envian (json) usando el repositorio pero tambien necesitas
    // enviar de vuelta los resultados de dicha creacion. Entonces mapeas la entidad con la que juegas (BDD Interactions)
    // y la conviertes en un DTO para enviar la info como respuesta a la llamda que hizo el cliente al API.
    @Transactional
    public CorrectAnswers save(CorrectAnswersDto request) {
        Questionary questionary = questionaryRepository.findById(request.getQuestionaryId())
                .orElseThrow(() -> new SpringException(request.getQuestionaryId().toString()));
        
        CorrectAnswers save = repository.save(mapDtoToTrans(request, questionary));
        return save;
    }

    @Transactional(readOnly = true)
    public List<CorrectAnswersDto> getAll() {
        return repository.findAll()
            .stream()
            .map(this::mapToDto)
            .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<CorrectAnswersDto> getAllByUserId(Long id) {
        return repository.findByUserUserId(id)
            .stream()
            .map(this::mapToDto)
            .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<CorrectAnswersDto> getAllByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new SpringException(username));

        return repository.findByUserUserId(user.getUserId())
            .stream()
            .map(this::mapToDto)
            .collect(toList());
    }


    private CorrectAnswers mapDtoToTrans(CorrectAnswersDto request, Questionary questionary) {
        return CorrectAnswers.builder()
            .user(authService.getCurrentUser())
            .questionary(questionary)
            .build();
    }
    private CorrectAnswersDto mapToDto(CorrectAnswers transaction) {
        return CorrectAnswersDto.builder()
            .id(transaction.getId())
            .userId(transaction.getUser().getUserId())
            .questionaryId(transaction.getQuestionary().getQuestionaryId())
            .build();
    }
}