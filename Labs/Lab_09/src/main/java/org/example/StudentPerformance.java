package org.example;

import lombok.Data;

@Data
public class StudentPerformance {
    private int performanceId;
    private int studentId;
    private int subjectId;
    private int score;
}