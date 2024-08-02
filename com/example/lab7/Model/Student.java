package com.example.lab7.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {

    @NotNull(message = "ID should not be empty !")
    //@Size(max = 5,message = "id max '5' digits !")
    @Positive(message = "id should be positive !")
    private int id;

    @NotEmpty(message = "name should not be empty !")
    @Size(max = 25, message = "name max '25' characters !")
    private String name;

    @NotEmpty(message = "collage should not be empty !")
    @Size(max = 25, message = "Collage max '25' characters !")
    private String collage;

    @NotNull(message = "Semester should not be empty !")
    @Max(10)
    private int semester;

    @NotEmpty(message = "Attendance should not be empty !")
    @Pattern(regexp = "^(present|absent)$")
    private String attendance;

    @NotNull(message = "Absents should not be empty !")
    @Max(7)
    private int absents;

    @AssertFalse(message = "DN should be false !")
    private boolean dn;

    @NotNull(message = "GPA should not be empty !")
    @Positive(message = "GPA should be positive number !")
    @Max(5)
    private double gpa;

}
