package com.example.MoneyDefender.controller;

import com.example.MoneyDefender.dto.CommentsDto;
import com.example.MoneyDefender.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentsController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentsDto commentsDto) {
        commentService.save(commentsDto);
        return new ResponseEntity<>(CREATED);
    }

    @GetMapping("/by-lesson/{lessonId}")
    public ResponseEntity<List<CommentsDto>> getAllCommentsForLesson(@PathVariable Long lessonId) {
        return ResponseEntity.status(OK).body(commentService.getAllCommentsForLesson(lessonId));
    }

    // @GetMapping("/by-user/{userName}")
    // public ResponseEntity<List<CommentsDto>> getAllCommentsForUser(@PathVariable String userName){
    //     return ResponseEntity.status(OK)
    //             .body(commentService.getAllCommentsForUser(userName));
    // }

}
