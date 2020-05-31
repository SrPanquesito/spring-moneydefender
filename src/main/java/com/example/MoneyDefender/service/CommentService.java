package com.example.MoneyDefender.service;

import com.example.MoneyDefender.dto.CommentsDto;
import com.example.MoneyDefender.exceptions.SpringException;
import com.example.MoneyDefender.model.Comment;
import com.example.MoneyDefender.model.User;
import com.example.MoneyDefender.model.Lesson;
import com.example.MoneyDefender.repository.CommentRepository;
import com.example.MoneyDefender.repository.LessonRepository;
import com.example.MoneyDefender.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.time.Instant;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CommentService {
    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final CommentRepository repository;

    @Transactional
    public CommentsDto save(CommentsDto commentsDto) {
        Lesson lesson = lessonRepository.findById(commentsDto.getLessonId())
                .orElseThrow(() -> new SpringException(commentsDto.getLessonId().toString()));

        Comment save = repository.save(mapDtoToTrans(commentsDto, lesson));
        commentsDto.setId(save.getId());
        commentsDto.setUserName(save.getUser().getUsername());
        return commentsDto;
    }

    @Transactional(readOnly = true)
    public List<CommentsDto> getAllCommentsForLesson(Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new SpringException(lessonId.toString()));
        return repository.findByLesson(lesson)
                .stream()
                .map(this::mapToDto)
                .collect(toList());
    }

    // public List<CommentsDto> getAllCommentsForUser(String userName) {
    //     User user = userRepository.findByUsername(userName)
    //             .orElseThrow(() -> new UsernameNotFoundException(userName));
    //     return repository.findAllByUser(user)
    //             .stream()
    //             .map(commentMapper::mapToDto)
    //             .collect(toList());
    // }

    private Comment mapDtoToTrans(CommentsDto request, Lesson lesson) {
        return Comment.builder()
            .text(request.getText())
            .createdDate(Instant.now())
            .lesson(lesson)
            .user(authService.getCurrentUser())
            .build();
    }

    private CommentsDto mapToDto(Comment transaction) {
        return CommentsDto.builder()
            .id(transaction.getId())
            .lessonId(transaction.getLesson().getLessonId())
            .text(transaction.getText())
            .createdDate(transaction.getCreatedDate())
            .userName(transaction.getUser().getUsername())
            .build();
    }
}
