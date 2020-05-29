package com.example.MoneyDefender.controller;

import com.example.MoneyDefender.dto.QuestionaryDto;
import com.example.MoneyDefender.service.QuestionaryService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/questionary")
@AllArgsConstructor
public class QuestionaryController {

    private final QuestionaryService service;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody QuestionaryDto request) {
        service.save(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<QuestionaryDto>> getAll() {
        return status(HttpStatus.OK).body(service.getAll());
    }
}