package com.example.MoneyDefender.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionaryDto {
    private Long questionaryId;
    private String question;
    private String answers;
    private Integer correctAnswer;
    private Long lessonId;
}