package com.example.MoneyDefender.repository;

import com.example.MoneyDefender.model.Comment;
import com.example.MoneyDefender.model.Lesson;
import com.example.MoneyDefender.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByLesson(Lesson lesson);

    List<Comment> findAllByUser(User user);
}
