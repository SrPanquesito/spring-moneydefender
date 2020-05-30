package com.example.MoneyDefender.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long lessonId;
    @NotBlank(message = "Lesson Name cannot be empty or Null")
    private String lessonName;
    @NotBlank(message = "Lesson Slug cannot be empty or Null")
    private String lessonSlug;
    private Instant createdDate;
    @NotBlank(message = "Content cannot be empty or Null")
    @Lob
    private String content;
    @Nullable
    @Lob
    private String imageUrl;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "courseId", referencedColumnName = "courseId")
    private Course course;
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "questionaryId", referencedColumnName = "questionaryId")
    private Questionary questionary;
}
