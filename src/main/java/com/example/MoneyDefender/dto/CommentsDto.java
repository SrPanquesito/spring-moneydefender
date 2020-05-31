package com.example.MoneyDefender.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentsDto {
    private Long id;
    private Long lessonId;
    private Instant createdDate;
    private String text;
    private String userName;
}
