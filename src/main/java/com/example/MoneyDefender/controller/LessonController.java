package com.example.MoneyDefender.controller;

import com.example.MoneyDefender.dto.LessonDto;
import com.example.MoneyDefender.model.Lesson;
import com.example.MoneyDefender.service.LessonService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/lesson")
@AllArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @PostMapping
    public ResponseEntity<Void> createLesson(@RequestBody LessonDto lessonRequest) {
        lessonService.save(lessonRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LessonDto>> getAllPosts() {
        return status(HttpStatus.OK).body(lessonService.getAll());
    }

    @PutMapping("/{id}/questionary/{qId}")
    public ResponseEntity<?> updateLesson(@PathVariable Long id, @PathVariable Long qId) {
        return status(HttpStatus.OK).body(lessonService.update(id, qId));
    }

    // @GetMapping("by-subreddit/{id}")
    // public ResponseEntity<List<PostResponse>> getPostsBySubreddit(Long id) {
    //     return status(HttpStatus.OK).body(courseService.getPostsBySubreddit(id));
    // }

    // @GetMapping("by-user/{name}")
    // public ResponseEntity<List<PostResponse>> getPostsByUsername(String username) {
    //     return status(HttpStatus.OK).body(courseService.getPostsByUsername(username));
    // }
}
