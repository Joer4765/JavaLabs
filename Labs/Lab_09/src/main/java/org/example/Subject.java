package org.example;

import lombok.Data;

@Data
public class Subject {
    private int subjectId;
    private String subjectName;
    private int lectureHours;
    private Integer practicalHours; // Integer, якщо може бути null
    private Integer labHours; // Integer, якщо може бути null
    private String assessmentType;
    private int semester;
    private int teacherId;
}
