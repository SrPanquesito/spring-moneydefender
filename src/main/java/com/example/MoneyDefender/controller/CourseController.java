package com.example.MoneyDefender.controller;

import com.example.MoneyDefender.dto.CourseResponse;
import com.example.MoneyDefender.model.Course;
import com.example.MoneyDefender.service.CourseService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/course")
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;

    // private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @PostMapping
    public ResponseEntity<Void> createCourse(@RequestBody Course courseRequest) {
        courseService.save(courseRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAllPosts() {
        return status(HttpStatus.OK).body(courseService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getPost(@PathVariable Long id) {
        return status(HttpStatus.OK).body(courseService.getCourse(id));
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
