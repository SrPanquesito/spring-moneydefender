package com.example.MoneyDefender.controller;

import com.example.MoneyDefender.dto.CorrectAnswersDto;
import com.example.MoneyDefender.model.CorrectAnswers;
import com.example.MoneyDefender.service.CorrectAnswersService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/correct-answers")
@AllArgsConstructor
public class CorrectAnswersController {

    private final CorrectAnswersService service;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CorrectAnswersDto request) {
        service.save(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CorrectAnswersDto>> getAll() {
        return status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/by-user/{id}")
    public ResponseEntity<List<CorrectAnswersDto>> getAllByUser(@PathVariable Long id) {
        return status(HttpStatus.OK).body(service.getAllByUserId(id));
    }
}
