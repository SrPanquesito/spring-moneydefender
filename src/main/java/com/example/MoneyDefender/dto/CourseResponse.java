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
public class CourseResponse {
    private Long courseId;
    private String courseName;
    private String description;
    private Integer userId;
    private Instant createdAt;
}
