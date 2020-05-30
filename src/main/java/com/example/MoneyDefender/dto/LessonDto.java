package com.example.MoneyDefender.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonDto {
    private Long lessonId;
    private String lessonName;
    private String lessonSlug;
    private String content;
    private String imageUrl;
    private Instant createdDate;
    private Long courseId;
    private Long questionaryId;
    private String messageLog;
}
