package com.example.lab7.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class ClassRoom {
    @NotEmpty(message = "Class room id should not be empty !")
    @Size(max = 7)
    private String classRoom;

    @NotEmpty(message = "Course should be not empty !")
    @Size(max = 20)
    private String course;

    @NotEmpty(message = "Instructor name should not be empty ! ")
    @Size(max = 20)
    @Pattern(regexp = "^[a-zA-Z '.-]*$",message = "Name must contain only 'characters' and '-' !")
    private String instructorName;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime time;

    @NotNull(message = "message should not be empty !")
    @Max(60)
    @Positive
    private int capacity;


}
